/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.util.PropertyValues;
import mz.co.mozview.frameworks.core.util.StringNormalizer;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.dao.FormDAO;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.formquestion.dao.FormQuestionDAO;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.formquestion.service.FormQuestionService;
import mz.org.fgh.mentoring.core.question.model.Question;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(FormService.NAME)
public class FormServiceImpl extends AbstractService implements FormService {

	@Inject
	private FormDAO formDAO;

	@Inject
	private PropertyValues propertyValues;

	@Inject
	private FormQuestionService formQuestionService;

	@Inject
	private FormQuestionDAO formQuestionDao;

	@Override
	public Form createForm(final UserContext userContext, final Form form, final Set<FormQuestion> formQuestions)
	        throws BusinessException {

		final String code = this.formDAO.generateCode("MT", 8, "0");
		form.setCode(code);

		form.setName(StringNormalizer.normalizeAndUppCase(form.getName()));

		if (formQuestions.isEmpty()) {
			throw new BusinessException(this.propertyValues.getPropValues("cannot.create.form.without.questions"));
		}

		this.formDAO.create(userContext.getUuid(), form);

		for (final FormQuestion formQuestion : formQuestions) {
			formQuestion.setForm(form);
			this.formQuestionService.createFormQuestion(userContext, formQuestion);
		}

		return form;
	}

	private List<FormQuestion> inactivatedAllFormQuestion(final Long formId) {

		final List<FormQuestion> formQuestions = this.formQuestionDao.findAllByFormId(formId);

		for (final FormQuestion formQuestionInterator : formQuestions) {
			formQuestionInterator.setLifeCycleStatus(LifeCycleStatus.INACTIVE);
		}

		return formQuestions;
	}

	public void getFormQuestionByList(final UserContext userContext, final Set<Question> questions, final Form form,
	        final List<FormQuestion> formQuestions) throws BusinessException {

		final Map<Long, FormQuestion> mapQuestions = new HashMap<>();

		for (final FormQuestion formQuestion : formQuestions) {
			mapQuestions.put(formQuestion.getQuestion().getId(), formQuestion);
		}

		for (final Question question : questions) {
			if (mapQuestions.containsKey(question.getId())) {
				mapQuestions.get(question.getId()).setLifeCycleStatus(LifeCycleStatus.ACTIVE);
				this.formQuestionService.updateFormQuestion(userContext, mapQuestions.get(question.getId()));
			}
			if (!mapQuestions.containsKey(question.getId())) {
				final FormQuestion created = new FormQuestion();
				created.setForm(form);
				created.setQuestion(question);
				this.formQuestionService.createFormQuestion(userContext, created);
			}
		}
	}

	@Override
	public Form updateForm(final UserContext userContext, final Form form, final Set<FormQuestion> formQuestions)
	        throws BusinessException {

		if (formQuestions.isEmpty()) {
			throw new BusinessException(this.propertyValues.getPropValues("cannot.update.form.without.questions"));
		}

		form.setName(StringNormalizer.normalizeAndUppCase(form.getName()));
		this.formDAO.update(userContext.getUuid(), form);

		final List<FormQuestion> inactivatedFormQuestions = this.inactivatedAllFormQuestion(form.getId());

		formQuestions.forEach(formQuestion -> {

			final Optional<FormQuestion> optional = this.findInactiveFormQuestion(inactivatedFormQuestions,
			        formQuestion);

			if (optional.isPresent()) {
				this.updateFoundFormQuestion(userContext, formQuestion, optional);
			}
			else {
				this.createNewFormQuestion(userContext, form, formQuestion);
			}
		});

		return form;
	}

	private void createNewFormQuestion(final UserContext userContext, final Form form,
	        final FormQuestion formQuestion) {
		try {
			formQuestion.setForm(form);
			this.formQuestionService.createFormQuestion(userContext, formQuestion);
		}
		catch (final BusinessException e) {
			e.printStackTrace();
		}
	}

	private void updateFoundFormQuestion(final UserContext userContext, final FormQuestion formQuestion,
	        final Optional<FormQuestion> optional) {
		try {
			final FormQuestion value = optional.get();
			value.setLifeCycleStatus(LifeCycleStatus.ACTIVE);
			value.setSequence(formQuestion.getSequence());
			value.setApplicable(formQuestion.isApplicable());
			this.formQuestionService.updateFormQuestion(userContext, value);
		}
		catch (final BusinessException e) {
			e.printStackTrace();
		}
	}

	private Optional<FormQuestion> findInactiveFormQuestion(final List<FormQuestion> formQuestions,
	        final FormQuestion formQuestion) {
		final Optional<FormQuestion> optional = formQuestions.stream()
		        .filter(inactiveFormQuestion -> inactiveFormQuestion.getQuestion().getUuid()
		                .equals(formQuestion.getQuestion().getUuid())
		                && inactiveFormQuestion.getForm().getUuid().equals(formQuestion.getForm().getUuid()))
		        .findAny();
		return optional;
	}
}
