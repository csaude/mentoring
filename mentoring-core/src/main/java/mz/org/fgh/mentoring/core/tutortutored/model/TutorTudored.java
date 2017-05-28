package mz.org.fgh.mentoring.core.tutortutored.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Entity
@Table(name = "TUTOR_TUTORED")
public class TutorTudored extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TUTOR_ID", nullable = false)
	private Tutor tutor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TUTORED_ID", nullable = false)
	private Tutored tutored;

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public Tutored getTutored() {
		return tutored;
	}

	public void setTutored(Tutored tutored) {
		this.tutored = tutored;
	}

	public TutorTudored() {
		super();
	}
}
