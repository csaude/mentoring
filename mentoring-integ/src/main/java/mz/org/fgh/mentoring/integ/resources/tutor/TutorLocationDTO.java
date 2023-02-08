/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.tutor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.model.TutorLocation;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TutorLocationDTO {

	private Tutor tutor;

	private HealthFacility location;

	public TutorLocationDTO() {
	}

	public TutorLocationDTO(final TutorLocation tutorLocation) {
		this.tutor = tutorLocation.getTutor();
		this.location = tutorLocation.getLocation();
	}

	public Tutor getTutor() {
		return this.tutor;
	}

	public HealthFacility getLocation() {
		return this.location;
	}
}
