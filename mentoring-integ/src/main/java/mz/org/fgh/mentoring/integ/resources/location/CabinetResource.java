/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.location;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.location.model.Cabinet;

/**
 * @author Stélio Moiane
 *
 */
public interface CabinetResource {

	@GET
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, })
	public JResponse<List<Cabinet>> getCabinets() throws BusinessException;

}
