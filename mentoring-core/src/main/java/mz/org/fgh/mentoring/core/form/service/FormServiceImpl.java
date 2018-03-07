/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.service;

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
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public Form createForm(final UserContext userContext, final Form form, final Set<Question> questions)
			throws BusinessException {

		// TODO generate code just a sample
		final String code = this.formDAO.generateCode("MT", 8, "0");
		form.setCode(code);

		form.setName(StringNormalizer.normalizeAndUppCase(form.getName()));

		if (questions.isEmpty()) {
			throw new BusinessException(this.propertyValues.getPropValues("cannot.create.form.without.questions"));
		}

		this.formDAO.create(userContext.getUuid(), form);

		for (final Question question : questions) {

			final FormQuestion formQuestion = new FormQuestion();
			formQuestion.setForm(form);
			formQuestion.setQuestion(question);

			this.formQuestionService.createFormQuestion(userContext, formQuestion);
		}

		return form;
	}

	@Override
	public Form createForm(UserContext userContext, Form form, Map<Integer, Question> questions) throws BusinessException {
		// TODO generate code just a sample
		final String code = this.formDAO.generateCode("MT", 8, "0");
		form.setCode(code);

		form.setName(StringNormalizer.normalizeAndUppCase(form.getName()));

		if (questions.isEmpty()) {
			throw new BusinessException(this.propertyValues.getPropValues("cannot.create.form.without.questions"));
		}

		this.formDAO.create(userContext.getUuid(), form);

		for(final Map.Entry<Integer, Question> entry: questions.entrySet()) {
			final FormQuestion formQuestion = new FormQuestion();
			formQuestion.setForm(form);
			formQuestion.setQuestion(entry.getValue());
			formQuestion.setSequence(entry.getKey());
			this.formQuestionService.createFormQuestion(userContext, formQuestion);
		}
		return form;
	}

	private List<FormQuestion> inactivetedAllFormQuestion(final Long formId) {

		final List<FormQuestion> formQuestions = this.formQuestionDao.findByFormId(formId);

		for (final FormQuestion formQuestionInterator : formQuestions) {
			formQuestionInterator.setLifeCycleStatus(LifeCycleStatus.INACTIVE);
		}

		return formQuestions;
	}

	public void getFormQuestionByList(final UserContext userContext, final Set<Question> questions, final Form form,
			final List<FormQuestion> formQuestions) throws BusinessException {

		if (questions.isEmpty()) {
			throw new BusinessException(this.propertyValues.getPropValues("cannot.update.form.without.questions"));
		}

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
	public Form updateForm(final UserContext userContext, final Form form, final Set<Question> questions)
			throws BusinessException {

		form.setName(StringNormalizer.normalizeAndUppCase(form.getName()));
		final Form updatedForm = this.formDAO.update(userContext.getUuid(), form);

		this.inactivetedAllFormQuestion(updatedForm.getId());
		this.getFormQuestionByList(userContext, questions, updatedForm,
				this.inactivetedAllFormQuestion(updatedForm.getId()));

		return updatedForm;
	}

	@Override
	public Form updateForm(UserContext userContext, Form form, Map<Integer, Question> questions) throws BusinessException {
		form.setName(StringNormalizer.normalizeAndUppCase(form.getName()));
		final Form updatedForm = formDAO.update(userContext.getUuid(), form);

		Set<FormQuestion> associatedFormQuestions = new HashSet<>(formQuestionDao.findByFormId(updatedForm.getId()));
		deactivateRemovedQuestions(associatedFormQuestions, new HashSet<Question>(questions.values()));

		// Update sequence information

		List<FormQuestion> newFormQuestions = new ArrayList<>();
		questions.forEach((sequence, question) -> {
			FormQuestion foundFormQuestion = findFormQuestionWithQuestion(question, associatedFormQuestions);
			if(foundFormQuestion != null) {
				foundFormQuestion.setSequence(sequence);
				if(foundFormQuestion.getLifeCycleStatus().equals(LifeCycleStatus.INACTIVE)) {
					foundFormQuestion.setLifeCycleStatus(LifeCycleStatus.ACTIVE);
				}
			}
			else {
				final FormQuestion formQuestion = new FormQuestion();
				formQuestion.setForm(updatedForm);
				formQuestion.setQuestion(question);
				formQuestion.setSequence(sequence);
				formQuestion.setLifeCycleStatus(LifeCycleStatus.ACTIVE);
				newFormQuestions.add(formQuestion);
			}
		});

		// Update existing & create new ones.
		for(FormQuestion existing: associatedFormQuestions) {
			formQuestionService.updateFormQuestion(userContext, existing);
		}

		for(FormQuestion newFormQuestion: newFormQuestions) {
			formQuestionService.createFormQuestion(userContext, newFormQuestion);
		}

		return updatedForm;
	}

	private void deactivateRemovedQuestions(Set<FormQuestion> formQuestions, Set<Question> newQuestions) {
		formQuestions.forEach(formQuestion -> {
			if(!newQuestions.stream().anyMatch(question -> formQuestion.getQuestion().getUuid().equals(question.getUuid()))) {
				formQuestion.setLifeCycleStatus(LifeCycleStatus.INACTIVE);
			}
		});
	}

	private FormQuestion findFormQuestionWithQuestion(Question question, Set<FormQuestion> formQuestions) {
		return formQuestions.stream()
				.filter(formQuestion -> formQuestion.getQuestion().getUuid().equals(question.getUuid()))
				.findFirst()
				.orElse(null);
	}
}
