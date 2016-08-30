/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.service;

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

	@Override
	public Form updateForm(final UserContext userContext, final Form form, Set<Question> questions)
			throws BusinessException {
		
		if (questions.isEmpty()) {
			throw new BusinessException(propertyValues.getPropValues("cannot.update.form.without.questions"));
		}

		Form updatedForm = this.formDAO.update(userContext.getId(), form);

		List<FormQuestion> formQuestions = formQuestionDao.findByFormId(updatedForm.getId());

		for (FormQuestion formQuestion : formQuestions) {

			formQuestion.setLifeCycleStatus(LifeCycleStatus.INACTIVE);
			formQuestionDao.update(userContext.getId(), formQuestion);

			for (Question question : questions) {

				List<FormQuestion> createdFormQuestions = formQuestionDao.findByFormIdAndQuestionId(updatedForm.getId(),
						question.getId());

				if (question.getCode().equals(formQuestion.getQuestion().getCode())) {
					formQuestion.setLifeCycleStatus(LifeCycleStatus.ACTIVE);
					formQuestionDao.update(userContext.getId(), formQuestion);
				}

				if (!createdFormQuestions.isEmpty()) {
					for (FormQuestion fq : createdFormQuestions) {

						if (fq.getForm().getId() == question.getId()
								&& fq.getQuestion().getId() == updatedForm.getId()) {
							fq.setLifeCycleStatus(LifeCycleStatus.ACTIVE);
							formQuestionDao.update(userContext.getId(), fq);
						}
					}
				} else {
					FormQuestion created = new FormQuestion();
					created.setForm(updatedForm);
					created.setQuestion(question);
					formQuestionService.createFormQuestion(userContext, created);
				}
			}
		}

		return updatedForm;
	}
}
