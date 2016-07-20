/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutored;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

import com.sun.jersey.api.JResponse;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface TutoredResource {

	String NAME = "mz.org.fgh.mentoring.integ.resources.tutored.TutoredResource";

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<Tutored> createTutored(final TutoredBeanResource tutoredBeanResource) throws BusinessException;
}
