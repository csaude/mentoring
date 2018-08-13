/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.career.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.career.dao.CareerDAO;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({ @NamedQuery(name = CareerDAO.QUERY_NAME.findByCarrerType, query = CareerDAO.QUERY.findByCarrerType),
        @NamedQuery(name = CareerDAO.QUERY_NAME.findAll, query = CareerDAO.QUERY.findAll),
        @NamedQuery(name = CareerDAO.QUERY_NAME.findByTypeAndPosition, query = CareerDAO.QUERY.findByTypeAndPosition) })
@Entity
@Table(name = "CARRERS")
public class Career extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "CARRER_TYPE", nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private CareerType careerType;

	@NotEmpty
	@Column(name = "POSITION", nullable = false, length = 80)
	private String position;

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "career")
	private final Set<Tutor> tutors = new HashSet<>();

	public CareerType getCareerType() {
		return this.careerType;
	}

	public void setCareerType(final CareerType careerType) {
		this.careerType = careerType;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(final String position) {
		this.position = position;
	}

	public Set<Tutor> getTutors() {
		return this.tutors;
	}

	@Override
	public boolean equals(final Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "tutors");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "tutors");
	}
}
