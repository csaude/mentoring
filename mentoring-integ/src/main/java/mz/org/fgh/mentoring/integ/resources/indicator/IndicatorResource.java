/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.indicator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;

/**
 * @author Stélio Moiane
 *
 */
public interface IndicatorResource {

	String NAME = "mz.org.fgh.mentoring.integ.resources.indicator.IndicatorResource";

	@POST
	@Path("sync")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<IndicatorBeanResource> synchronizeIndicators(final IndicatorBeanResource indicatorBeanResource)
	        throws BusinessException;
}
