package mz.org.fgh.mentoring.core.setting.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.setting.model.Setting;

/***
 * 
 * @author Jose Julai Ritsure
 *
 */
public interface SettingService {

	String NAME = "mz.org.fgh.mentoring.core.setting.service.SettingService";

	public abstract Setting findSettingByDesignation(final UserContext userContext, String designation) throws BusinessException;
	
	public abstract List<Setting> findSettingByTutor(final UserContext userContext) throws BusinessException;

}
