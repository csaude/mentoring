/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.session.model;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SubmitedSessions {

	private String district;

	private String programmaticArea;

	private Long totalSubmited;

	private Calendar lastUpdate;

	public SubmitedSessions(final String district, final String programmaticArea, final Long totalSubmited,
	        final Calendar lastUpdate) {
		this.district = district;
		this.programmaticArea = programmaticArea;
		this.totalSubmited = totalSubmited;
		this.lastUpdate = lastUpdate;
	}

	public SubmitedSessions() {
	}

	public String getDistrict() {
		return this.district;
	}

	public String getProgrammaticArea() {
		return this.programmaticArea;
	}

	public Long getTotalSubmited() {
		return this.totalSubmited;
	}

	public Calendar getLastUpdate() {
		return this.lastUpdate;
	}
}
