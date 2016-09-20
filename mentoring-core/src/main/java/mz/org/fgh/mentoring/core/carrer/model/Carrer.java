/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.carrer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.carrer.dao.CarrerDAO;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({ @NamedQuery(name = CarrerDAO.QUERY_NAME.findByCarrerType, query = CarrerDAO.QUERY.findByCarrerType) })
@Entity
@Table(name = "CARRERS", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }))
public class Carrer extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "CODE", length = 50)
	private String code;

	@NotNull
	@Column(name = "CARRER_TYPE", nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private CarrerType carrerType;

	@NotEmpty
	@Column(name = "POSITION", nullable = false, length = 80)
	private String position;

	public CarrerType getCarrerType() {
		return this.carrerType;
	}

	public void setCarrerType(final CarrerType carrerType) {
		this.carrerType = carrerType;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(final String position) {
		this.position = position;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
