/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
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

	public TutorBeanResource() {
	}

	public UserContext getUserContext() {
		return this.userContext;
	}

	public Tutor getTutor() {
		return this.tutor;
	}
}
