/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.NotNull;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.tutor.dao.TutorDAO;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries(@NamedQuery(name = TutorDAO.QUERY_NAME.fetchByUuid, query = TutorDAO.QUERY.fetchByUuid))
@Entity
@Table(name = "TUTORS", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }))
public class Tutor extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "CODE", nullable = false, length = 50)
	private String code;

	@NotEmpty
	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	@NotEmpty
	@Column(name = "SURNAME", nullable = false, length = 50)
	private String surname;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARRER_ID", nullable = false)
	private Career career;

	@Column(name = "PHONE_NUMBER", nullable = false, length = 50)
	private String phoneNumber;

	public Tutor() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(final Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "carrer");
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Career getCareer() {
		return this.career;
	}

	public void setCareer(final Career career) {
		this.career = career;
	}
}
