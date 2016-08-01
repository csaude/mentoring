/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.programmaticarea;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProgrammaticAreaBeanResource {

	private UserContext userContext;
	private ProgrammaticArea programmaticArea;

	public ProgrammaticAreaBeanResource() {
	}

	public UserContext getUserContext() {
		return this.userContext;
	}

	public ProgrammaticArea getProgrammaticArea() {
		return this.programmaticArea;
	}
}
