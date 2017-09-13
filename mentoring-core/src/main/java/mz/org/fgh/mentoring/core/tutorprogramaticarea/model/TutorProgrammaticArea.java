package mz.org.fgh.mentoring.core.tutorprogramaticarea.model;

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

import com.sun.istack.NotNull;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.dao.TutorProgrammaticAreaDAO;

/**
 * @author Eusebio Jose Maposse
 *
 */
@NamedQueries(@NamedQuery(name = TutorProgrammaticAreaDAO.QUERY_NAME.findByTutorAndProgrammaticArea, query = TutorProgrammaticAreaDAO.QUERY.findByTutorAndProgrammaticArea))
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "TUTOR_PROGRAMMATIC_AREA", uniqueConstraints = @UniqueConstraint(columnNames = { "TUTOR_ID",
        "PROGRAMMATIC_AREA_ID" }))
public class TutorProgrammaticArea extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TUTOR_ID", nullable = false)
	private Tutor tutor;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROGRAMMATIC_AREA_ID", nullable = false)
	private ProgrammaticArea programmaticArea;

	public Tutor getTutor() {
		return this.tutor;
	}

	public void setTutor(final Tutor tutor) {
		this.tutor = tutor;
	}

	public ProgrammaticArea getProgrammaticArea() {
		return this.programmaticArea;
	}

	public void setProgrammaticArea(final ProgrammaticArea programmaticArea) {
		this.programmaticArea = programmaticArea;
	}
}
