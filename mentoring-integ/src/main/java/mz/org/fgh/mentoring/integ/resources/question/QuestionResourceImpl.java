/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.question;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.model.QuestionType;
import mz.org.fgh.mentoring.core.question.service.QuestionQueryService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.util.QuestionCategory;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Eusebio Jose Maposse
 * @author Stélio Moiane
 */
@Service(QuestionResource.NAME)
@Path("questions")
public class QuestionResourceImpl extends AbstractResource implements QuestionResource {

	@Inject
	private QuestionService questionService;

	@Inject
	private QuestionQueryService questionQueryService;

	@Override
	public JResponse<Question> createQuetion(final QuestionBeanResource questionBeanResource) throws BusinessException {

		final Question question = this.questionService.createQuestion(questionBeanResource.getUserContext(),
				questionBeanResource.getQuestion());

		return JResponse.ok(question).build();
	}

	@Override
	public JResponse<List<Question>> findQuestions(final String code, final String question,
			final QuestionType questionType, QuestionCategory questionCategory) throws BusinessException {

		final List<Question> questions = this.questionQueryService.findQuestionsBySelectedFilter(this.getUserContetx(),
				code, question, questionType, questionCategory);

		return JResponse.ok(questions).build();
	}

	@Override
	public JResponse<Question> updateQuestion(final QuestionBeanResource questionBeanResource)
			throws BusinessException {

		final Question question = this.questionService.updateQuestion(questionBeanResource.getUserContext(),
				questionBeanResource.getQuestion());

		return JResponse.ok(question).build();
	}

	@Override
	public JResponse<List<Question>> findQuestionsByForm(final String code) throws BusinessException {
		
		final List<Question> questions = this.questionQueryService.findByFormCode(code);
		
		return JResponse.ok(questions).build();
	}
}
