package mz.org.fgh.mentoring.core.mentorship.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Entity
@Table(name = "MENTORSHIPS")
public class Mentorship extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INITIAL_DATE", nullable = false)
	private Date initialDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE", nullable = false)
	private Date endDate;

	@NotEmpty
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TUTOR_ID", nullable = false)
	private Tutor tutor;

	@NotEmpty
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TUTORANDO_ID", nullable = false)
	private Tutored tutorando;

	public Mentorship() {
		super();
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public Tutored getTutorando() {
		return tutorando;
	}

	public void setTutorando(Tutored tutorando) {
		this.tutorando = tutorando;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(initialDate);
		hcb.append(endDate);
		hcb.append(tutor);
		hcb.append(tutorando);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Mentorship)) {
			return false;
		}
		Mentorship that = (Mentorship) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(initialDate, that.initialDate);
		eb.append(endDate, that.endDate);
		eb.append(tutor, that.tutor);
		eb.append(tutorando, that.tutorando);
		return eb.isEquals();
	}
}
