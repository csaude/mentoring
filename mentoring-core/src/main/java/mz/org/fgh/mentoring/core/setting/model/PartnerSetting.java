package mz.org.fgh.mentoring.core.setting.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.partner.model.Partner;
import mz.org.fgh.mentoring.core.setting.dao.PartnerSettingDAO;

/***
 * 
 * @author Jose Julai Ritsure
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = PartnerSettingDAO.QUERY_NAME.findByDesignation, query = PartnerSettingDAO.QUERY.findByDesignation),
		@NamedQuery(name = PartnerSettingDAO.QUERY_NAME.findByPartnerId, query = PartnerSettingDAO.QUERY.findByPartnerId),})
@Entity
@Table(name = "PARTNER_SETTINGS")
public class PartnerSetting extends GenericEntity {

	private static final long serialVersionUID = -2343259152197497509L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARTNER_ID", nullable = false)
	private Partner partner;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SETTING_ID", nullable = false)
	private Setting setting;

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	@Override
	public int hashCode() {
		return Objects.hash(partner, setting);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartnerSetting other = (PartnerSetting) obj;
		return Objects.equals(partner, other.partner) && Objects.equals(setting, other.setting);
	}
}
