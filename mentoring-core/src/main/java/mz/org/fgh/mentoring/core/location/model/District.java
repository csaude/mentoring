/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import mz.co.mozview.frameworks.core.model.GenericEntity;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "DISTRICTS")
public class District extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "PROVINCE", nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private Province province;

	@NotEmpty
	@Column(name = "DISTRICT", nullable = false, length = 50)
	private String district;

	public Province getProvince() {
		return this.province;
	}

	public void setProvince(final Province province) {
		this.province = province;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(final String district) {
		this.district = district;
	}
}
