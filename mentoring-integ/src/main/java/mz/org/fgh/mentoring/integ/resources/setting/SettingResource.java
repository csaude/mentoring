package mz.org.fgh.mentoring.integ.resources.setting;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.setting.model.Setting;

/***
 * 
 * @author Jose Julai Ritsure
 *
 */
public interface SettingResource {

	String NAME = "mz.org.fgh.mentoring.integ.resources.setting.SettingResource";

	@GET
	@Path("{designation}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<Setting> findSettingByDesignation(final String designation)
			throws BusinessException;

	@Path("tutor/{uuid}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<Setting>> findSettingsByUser(@PathParam("uuid") final String uuid)
			throws BusinessException;
}
