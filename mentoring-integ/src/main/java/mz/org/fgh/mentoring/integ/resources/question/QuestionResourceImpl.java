/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.question;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionService;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(QuestionResource.NAME)
@Path("questions")
public class QuestionResourceImpl implements QuestionResource {

	@Inject
	private QuestionService questionService;

	@Override
	public JResponse<Question> createQuetion(final QuestionBeanResource quetionBeanResource) throws BusinessException {

		final Question question = this.questionService.createQuestion(quetionBeanResource.getUserContext(),
				quetionBeanResource.getQuestion());

		return JResponse.ok(question).build();
	}

	@Override
	public Response listQuetion() throws BusinessException {

		return null;
	}
}
