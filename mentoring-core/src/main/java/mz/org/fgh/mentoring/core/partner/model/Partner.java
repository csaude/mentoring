/**
 *
 */
package mz.org.fgh.mentoring.core.partner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.partner.dao.PartnerDAO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({    @NamedQuery(name = PartnerDAO.QUERY_NAME.findAll, query = PartnerDAO.QUERY.findAll) })
@Entity
@Table(name = "PARTNERS")
public class Partner extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	public Partner() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
}
