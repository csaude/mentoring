package mz.org.fgh.mentoring.integ.resources.setting;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.setting.model.Setting;
import mz.org.fgh.mentoring.core.setting.service.SettingService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

@Service(SettingResource.NAME)
@Path("settings")
public class SettingResourceImpl extends AbstractResource implements SettingResource {

	@Inject
	private SettingService settingService;

	@Override
	public JResponse<Setting> findSettingByDesignation(@PathParam("designation") String designation) throws BusinessException {

		final Setting setting = this.settingService.findSettingByDesignation(this.getUserContetx(), designation);

		return JResponse.ok(setting).build();
	}

	@Override
	public JResponse<List<Setting>> findSettingsByUser(String uuid) throws BusinessException {
		UserContext userContext = new UserContext();
		userContext.setUuid(uuid);
		final List<Setting> settings = this.settingService.findSettingByTutor(userContext);
		return JResponse.ok(settings).build();
	}

}
