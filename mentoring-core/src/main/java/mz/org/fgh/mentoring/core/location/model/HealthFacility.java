/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import mz.org.fgh.mentoring.core.tutor.model.TutorLocation;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgrammaticArea;
import org.hibernate.validator.constraints.NotEmpty;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.location.dao.HealthFacilityDAO;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Stélio Moiane
 *
 */
@NamedQueries({
        @NamedQuery(name = HealthFacilityDAO.QUERY_NAME.findByDistrict, query = HealthFacilityDAO.QUERY.findByDistrict),
        @NamedQuery(name = HealthFacilityDAO.QUERY_NAME.fetchAll, query = HealthFacilityDAO.QUERY.fetchAll),
		@NamedQuery(name = HealthFacilityDAO.QUERY_NAME.fetchByTutor, query = HealthFacilityDAO.QUERY.fetchByTutor),
        @NamedQuery(name = HealthFacilityDAO.QUERY_NAME.findByDistrictAndName, query = HealthFacilityDAO.QUERY.findByDistrictAndName) })
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "HEALTH_FACILITIES")
public class HealthFacility extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISTRICT_ID", nullable = false)
	private District district;

	@NotEmpty
	@Column(name = "HEALTH_FACILITY", nullable = false, length = 80)
	private String healthFacility;

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
	private Set<TutorLocation> tutorLocations = new HashSet<>();
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

	public Set<TutorLocation> getTutorLocations() {
		return tutorLocations;
	}

	public void setTutorLocations(Set<TutorLocation> tutorLocations) {
		this.tutorLocations = tutorLocations;
	}
}
