/**
 *
 */
package mz.org.fgh.mentoring.core.form.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.form.dao.FormTargetDAO;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({ @NamedQuery(name = FormTargetDAO.QUERY_NAME.findByTutor, query = FormTargetDAO.QUERY.findByTutor) })
@Entity
@Table(name = "FORM_TARGETS", uniqueConstraints = @UniqueConstraint(columnNames = { "FORM_ID", "CAREER_ID" }))
public class FormTarget extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORM_ID", nullable = false)
	private Form form;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAREER_ID", nullable = false)
	private Career career;

	@NotNull
	@Column(name = "TARGET", nullable = false)
	private Integer target;

	public FormTarget() {
	}

	public Form getForm() {
		return this.form;
	}

	public void setForm(final Form form) {
		this.form = form;
	}

	public Career getCareer() {
		return this.career;
	}

	public void setCareer(final Career career) {
		this.career = career;
	}

	public Integer getTarget() {
		return this.target;
	}

	public void setTarget(final Integer target) {
		this.target = target;
	}

	@Override
	public boolean equals(final Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "form", "career");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "form", "career");
	}
}
