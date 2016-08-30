/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.mentorship;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Stélio Moiane
 *
 */
public interface MentorshipResource {

	String NAME = "mz.org.fgh.mentoring.integ.mentorship.MentorshipResource";

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public JResponse<Mentorship> createMentorshipProcess(final MentorshipBeanResource mentorshipBeanResource)
			throws BusinessException;
}
