/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.question;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.question.model.QuestionsCategory;

/**
 * @author Stélio Moiane
 *
 */
public interface QuestionCategoryResource {

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	JResponse<QuestionsCategory> createQuestionCategory(final QuestionCategoryBeanResource questionCategoryBeanResource)
	        throws BusinessException;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	JResponse<List<QuestionsCategory>> findAllQuestionCategories() throws BusinessException;
}
