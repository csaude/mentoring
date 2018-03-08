/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.answer;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;

/**
 * @author Stélio Moiane
 *
 */
public interface AnswerResource {

	String NAME = "mz.org.fgh.mentoring.integ.resources.answer.AnswerResource";

	@GET
	@Path("{mentorshipUuid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	JResponse<List<AnswerBeanResource>> fetchAnswersByMentorshipUuid(
	        @PathParam("mentorshipUuid") final String mentorshipUuid) throws BusinessException;
}
