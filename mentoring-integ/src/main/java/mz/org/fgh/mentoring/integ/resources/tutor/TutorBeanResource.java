/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutor;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TutorBeanResource {

	private UserContext userContext;

	private Tutor tutor;

	private List<HealthFacility> locations;

	public TutorBeanResource() {
	}

	public TutorBeanResource(Tutor tutor, List<HealthFacility> locations) {
		this.tutor = tutor;
		this.locations = locations;
	}

	public UserContext getUserContext() {
		return this.userContext;
	}

	public Tutor getTutor() {
		return this.tutor;
	}

	public List<HealthFacility> getLocations() {
		return Collections.unmodifiableList(this.locations);
	}
}
