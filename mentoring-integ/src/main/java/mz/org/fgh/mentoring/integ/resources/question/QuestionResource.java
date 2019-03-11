/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.question;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.model.QuestionCategory;
import mz.org.fgh.mentoring.core.question.model.QuestionType;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface QuestionResource {

	String NAME = "mz.org.fgh.mentoring.integ.resources.question.QuestionResource";

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<Question> createQuestion(final QuestionBeanResource questionBeanResource) throws BusinessException;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<Question>> findQuestions(@QueryParam("code") final String code,
			@QueryParam("question") final String question, @QueryParam("questionType") final QuestionType questionType,
			@QueryParam("questionCategory") final QuestionCategory questionCategory) throws BusinessException;

	@GET
	@Path("{formCode}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<Question>> findQuestionsByForm(@PathParam("formCode") final String formCode)
			throws BusinessException;

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<Question> updateQuestion(final QuestionBeanResource questionBeanResource) throws BusinessException;
}
