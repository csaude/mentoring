/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.mentorship.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.integ.resources.mentorship.MentorshipHelper;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SessionDTO {

	private Session session;

	private List<MentorshipHelper> mentorships;

	public Session getSession() {
		return this.session;
	}

	public List<MentorshipHelper> getMentorships() {
		return Collections.unmodifiableList(this.mentorships);
	}

	public void setSession(final Session session) {
		this.session = session;
	}

	public void addMentorships(final MentorshipHelper mentorshipHelper) {

		if (this.mentorships == null) {
			this.mentorships = new ArrayList<>();
		}

		this.mentorships.add(mentorshipHelper);
	}
}
