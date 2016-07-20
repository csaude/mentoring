/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutor;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

import com.sun.jersey.api.JResponse;

/**
 * @author Stélio Moiane
 *
 */
public interface TutorResource {

	String NAME = "mz.org.fgh.mentoring.integ.resources.tuto.TutorResource";

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<Tutor> createTutor(final TutorBeanResource tutorBeanResource) throws BusinessException;
}
