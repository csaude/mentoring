/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.service;

import java.util.ArrayList;
import java.util.List;
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
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.formquestion.service.FormQuestionService;
import mz.org.fgh.mentoring.core.question.dao.QuestionDAO;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionService;

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
	private QuestionService questionService;

	@Inject
	private QuestionDAO questionDAO;

	List<Question> importSetListQuestions = new ArrayList<>();

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

	@Override
	public Form updateForm(final UserContext userContext, final Form form, Set<Question> questions)
			throws BusinessException {

		Form formUpdate = this.formDAO.update(userContext.getId(), form);

		List<Question> questionForms = questionDAO.findByFormCodeNotLifeCycle(form.getCode());

		for (Question question : questionForms) {
			question.setLifeCycleStatus(LifeCycleStatus.INACTIVE);
			questionService.updateQuestion(userContext, question);

			for (Question questionUpdate : questions) {

				if (questionUpdate.getCode().equals(question.getCode())) {

					question.setLifeCycleStatus(LifeCycleStatus.ACTIVE);
					questionService.updateQuestion(userContext, question);

				} else {

					if (question.getId() == null) {

						FormQuestion formQuestion = new FormQuestion();
						formQuestion.setForm(formUpdate);
						formQuestion.setQuestion(question);

						formQuestionService.createFormQuestion(userContext, formQuestion);
					}

				}
			}
		}

		return formUpdate;
	}
}
