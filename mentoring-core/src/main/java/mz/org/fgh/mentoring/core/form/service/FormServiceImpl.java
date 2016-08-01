/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.service;

import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.util.PropertyValues;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.dao.FormDAO;
import mz.org.fgh.mentoring.core.form.model.Form;
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
	public Form updateForm(final UserContext userContext, final Form form) throws BusinessException {

		return this.formDAO.update(userContext.getId(), form);
	}
}
