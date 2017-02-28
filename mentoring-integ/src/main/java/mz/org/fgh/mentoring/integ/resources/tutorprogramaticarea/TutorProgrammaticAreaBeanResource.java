/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutorprogramaticarea;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgramaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TutorProgrammaticAreaBeanResource {

	private UserContext userContext;

	private TutorProgramaticArea tutorProgramaticArea;
	
	public TutorProgrammaticAreaBeanResource() {
	}

	public UserContext getUserContext() {
		return this.userContext;
	}

	public TutorProgramaticArea getTutorProgramaticArea() {
		return tutorProgramaticArea;
	}

	public void setTutorProgramaticArea(TutorProgramaticArea tutorProgramaticArea) {
		this.tutorProgramaticArea = tutorProgramaticArea;
	}


}
