/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.indicator;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.indicator.model.AnalysisTable;
import mz.org.fgh.mentoring.core.indicator.model.SampleIndicator;

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
	JResponse<IndicatorBeanResource> synchronizeIndicators(final IndicatorBeanResource indicatorBeanResource)
	        throws BusinessException;

	@GET
	@Path("sample-indicators")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	JResponse<List<SampleIndicator>> findSampleIndicators(@QueryParam("districtUuid") String districtUuid,
	        @QueryParam("healthFacilityUuid") String healthFacilityUuid, @QueryParam("formUuid") String formUuid,
	        @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) throws BusinessException;

	@GET
	@Path("analysis-tables")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	JResponse<List<AnalysisTable>> findAnalysisTable(@QueryParam("districtUuid") String districtUuid,
	        @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) throws BusinessException;
}
