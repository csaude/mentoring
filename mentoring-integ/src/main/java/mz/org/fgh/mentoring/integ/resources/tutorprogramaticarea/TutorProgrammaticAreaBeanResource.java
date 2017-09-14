/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutorprogramaticarea;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgrammaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TutorProgrammaticAreaBeanResource {

	private UserContext userContext;

	private TutorProgrammaticArea tutorProgramaticArea;

	public TutorProgrammaticAreaBeanResource() {
	}

	public UserContext getUserContext() {
		return this.userContext;
	}

	public TutorProgrammaticArea getTutorProgramaticArea() {
		return tutorProgramaticArea;
	}

	public void setTutorProgramaticArea(TutorProgrammaticArea tutorProgramaticArea) {
		this.tutorProgramaticArea = tutorProgramaticArea;
	}

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}

}
