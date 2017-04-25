/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.mentorship;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.util.LocalDateTimeAdapter;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MentorshipHelper {

	private Mentorship mentorship;
	
	private String uuid;

	private String startDate;

	private String endDate;

	private List<AnswerHelper> answers;

	public MentorshipHelper() {
	}

	public MentorshipHelper(final Mentorship mentorship, final String startDate, final String endDate,
			final List<AnswerHelper> answers) {
		this.mentorship = mentorship;
		this.startDate = startDate;
		this.endDate = endDate;
		this.answers = answers;
	}

	public Mentorship getMentorship() {
		final LocalDateTimeAdapter localDateTimeAdapter = new LocalDateTimeAdapter();
		this.mentorship.setId(null);
		this.mentorship.setStartDate(localDateTimeAdapter.unmarshal(this.startDate));
		this.mentorship.setEndDate(localDateTimeAdapter.unmarshal(this.endDate));
		return this.mentorship;
	}

	public List<AnswerHelper> getAnswers() {
		return Collections.unmodifiableList(this.answers);
	}

	public String getEndDate() {
		return this.endDate;
	}

	public String getStartDate() {
		return this.startDate;
	}
	
	public String getUuid() {
		return this.uuid;
	}
}
