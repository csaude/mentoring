/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.util.PropertyValues;
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
	public Form createForm(final UserContext userContext, final Form form, final Set<Question> questions)
			throws BusinessException {

		// TODO generate code just a sample
		final String code = this.formDAO.generateCode("MT", 8, "0");
		form.setCode(code);

		if (questions.isEmpty()) {
			throw new BusinessException(propertyValues.getPropValues("cannot.create.form.without.questions"));
		}

		this.formDAO.create(userContext.getId(), form);

		for (Question question : questions) {

			FormQuestion formQuestion = new FormQuestion();
			formQuestion.setForm(form);
			formQuestion.setQuestion(question);

			formQuestionService.createFormQuestion(userContext, formQuestion);
		}

		return form;
	}

	private List<FormQuestion> inactivetedAllFormQuestion(Long formId) {

		List<FormQuestion> formQuestions = formQuestionDao.findByFormId(formId);

		for (FormQuestion formQuestionInterator : formQuestions) {
			formQuestionInterator.setLifeCycleStatus(LifeCycleStatus.INACTIVE);
		}

		return formQuestions;
	}

	public void getFormQuestionByList(final UserContext userContext, Set<Question> questions, Form form,
			List<FormQuestion> formQuestions) throws BusinessException {

		if (questions.isEmpty()) {
			throw new BusinessException(propertyValues.getPropValues("cannot.update.form.without.questions"));
		}

		Map<Long, FormQuestion> mapQuestions = new HashMap<>();

		for (FormQuestion formQuestion : formQuestions) {
			mapQuestions.put(formQuestion.getQuestion().getId(), formQuestion);
		}
		for (Question question : questions) {
			if (mapQuestions.containsKey(question.getId())) {
				mapQuestions.get(question.getId()).setLifeCycleStatus(LifeCycleStatus.ACTIVE);
				formQuestionService.updateFormQuestion(userContext, mapQuestions.get(question.getId()));
			}
			if (!mapQuestions.containsKey(question.getId())) {
				FormQuestion created = new FormQuestion();
				created.setForm(form);
				created.setQuestion(question);
				formQuestionService.createFormQuestion(userContext, created);
			}
		}
	}

	@Override
	public Form updateForm(final UserContext userContext, final Form form, Set<Question> questions)
			throws BusinessException {

		Form updatedForm = this.formDAO.update(userContext.getId(), form);

		inactivetedAllFormQuestion(updatedForm.getId());
		getFormQuestionByList(userContext, questions, updatedForm, inactivetedAllFormQuestion(updatedForm.getId()));

		return updatedForm;
	}
}
