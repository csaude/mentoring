/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutored;

import java.util.List;
import java.util.stream.Collectors;

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

	private List<Tutored> tutoreds;

	private List<String> tutoredUuids;

	public TutoredBeanResource() {
	}

	public UserContext getUserContext() {
		return this.userContext;
	}

	public void setUserContext(final UserContext userContext) {
		this.userContext = userContext;
	}

	public Tutored getTutored() {
		return this.tutored;
	}

	public void setTutored(final Tutored tutored) {
		this.tutored = tutored;
	}

	public void setTutoreds(final List<Tutored> tutoreds) {
		this.tutoreds = tutoreds;
	}

	public List<Tutored> getTutoreds() {
		return this.tutoreds;
	}

	public void prepareResponse() {
		this.tutoredUuids = this.tutoreds.stream().map(tutor -> tutor.getUuid()).collect(Collectors.toList());
		this.tutored = null;
		this.tutoreds = null;
	}

	public List<String> getTutoredUuids() {
		return this.tutoredUuids;
	}
}
