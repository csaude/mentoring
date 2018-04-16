/**
 *
 */
package mz.org.fgh.mentoring.core.location.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.location.dao.CabinetDAO;

/**
 * @author Stélio Moiane
 *
 */
@NamedQueries({ @NamedQuery(name = CabinetDAO.QUERY_NAME.findByName, query = CabinetDAO.QUERY.findByName),
        @NamedQuery(name = CabinetDAO.QUERY_NAME.findAll, query = CabinetDAO.QUERY.findAll) })
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "CABINETS")
public class Cabinet extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	public Cabinet() {
	}

	public Cabinet(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public boolean equals(final Object that) {
		return EqualsBuilder.reflectionEquals(this, that);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
