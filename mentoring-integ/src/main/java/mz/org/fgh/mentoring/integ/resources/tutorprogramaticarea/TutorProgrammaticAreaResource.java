/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutorprogramaticarea;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgramaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface TutorProgrammaticAreaResource {

	String NAME = "mz.org.fgh.mentoring.integ.resources.tutorprogramaticarea.TutorProgrammaticAreaResource";

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<TutorProgramaticArea> createTutorProgrammaticArea(final TutorProgrammaticAreaBeanResource tutorProgrammaticAreaBeanResource)
			throws BusinessException;

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<TutorProgramaticArea> updateTutorProgrammaticArea(final TutorProgrammaticAreaBeanResource tutorProgrammaticAreaBeanResource)
			throws BusinessException;
}
