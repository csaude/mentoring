package mz.org.fgh.mentoring.core.tutored.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import mz.co.mozview.frameworks.core.model.GenericEntity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Entity
@Table(name = "TUTORANDOS", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }))
public class Tutored extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "CODE", nullable = false, length = 50)
	private String code;

	@NotEmpty
	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	@NotEmpty
	@Column(name = "SUR_NAME", nullable = false, length = 50)
	private String surname;

	@NotEmpty
	@Column(name = "PHONE_NUMBER", nullable = false, length = 50)
	private String phoneNumber;

	public Tutored() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(code);
		hcb.append(name);
		hcb.append(surname);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Tutored)) {
			return false;
		}
		Tutored that = (Tutored) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(code, that.code);
		eb.append(name, that.name);
		eb.append(surname, that.surname);
		return eb.isEquals();
	}

}
