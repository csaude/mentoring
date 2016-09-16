/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.model;

import javax.persistence.Column;
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

import org.hibernate.validator.constraints.NotEmpty;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.location.dao.HealthFacilityDAO;

/**
 * @author Stélio Moiane
 *
 */
@NamedQueries({
		@NamedQuery(name = HealthFacilityDAO.QUERY_NAME.findByDistrict, query = HealthFacilityDAO.QUERY.findByDistrict) })
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "HEALTH_FACILITIES")
@NamedQueries({ @NamedQuery(name = HealthFacilityDAO.QUERY_NAME.findByDistrict, query = HealthFacilityDAO.QUERY.findByDistrict) })
public class HealthFacility extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISTRICT_ID", nullable = false)
	private District district;

	@NotEmpty
	@Column(name = "HEALTH_FACILITY", nullable = false, length = 80)
	private String healthFacility;

	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(final District district) {
		this.district = district;
	}

	public String getHealthFacility() {
		return this.healthFacility;
	}

	public void setHealthFacility(final String healthFacility) {
		this.healthFacility = healthFacility;
	}
}
