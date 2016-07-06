package mz.org.fgh.mentoring.core.tutorando.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Entity
@Table(name = "TUTORANDOS", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }))
@SequenceGenerator(name = "TUTORANDOS_SEQUENCE", initialValue = 100, allocationSize = 100)
public class Tutorando {

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

	public Tutorando() {
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

}
