/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Eusebio Jose Maposse
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TutoredBeanResource {

	private UserContext userContext;
	private Tutored tutored;

	public TutoredBeanResource() {
	}

	public UserContext getUserContext() {
		return this.userContext;
	}

	public Tutored getTutored() {
		return tutored;
	}

	public void setTutored(Tutored tutored) {
		this.tutored = tutored;
	}


}
