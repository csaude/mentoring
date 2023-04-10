package mz.org.fgh.mentoring.integ.resources.setting;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.setting.model.Setting;

/***
 * 
 * @author Jose Julai Ritsure
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SettingBeanResource {

	private UserContext userContext;

	private Setting setting;

	private List<Setting> settings;

	private List<String> settingUuids;

	public SettingBeanResource() {
	}

	public UserContext getUserContext() {
		return userContext;
	}

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public List<Setting> getSettings() {
		return settings;
	}

	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}

	public List<String> getSettingUuids() {
		return settingUuids;
	}

	public void setSettingUuids(List<String> settingUuids) {
		this.settingUuids = settingUuids;
	}
	
	public void prepareResponse() {
		this.settingUuids = this.settings.stream().map(setting -> setting.getUuid()).collect(Collectors.toList());
		this.setting = null;
		this.settings = null;
	}

	
}
