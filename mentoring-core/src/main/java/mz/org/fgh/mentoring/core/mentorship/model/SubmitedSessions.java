/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship.model;

import java.math.BigInteger;
import java.util.Date;

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

	public static final String TOTAL_SUBMISSIONS = "TotalSubmissions";

	private String healthFacility;

	private BigInteger totalSubmited;

	private Date lastUpdate;

	public SubmitedSessions(final String healthFacility, final BigInteger totalSubmited, final Date lastUpdate) {
		this.healthFacility = healthFacility;
		this.totalSubmited = totalSubmited;
		this.lastUpdate = lastUpdate;
	}

	public SubmitedSessions() {
	}

	public String getHealthFacility() {
		return this.healthFacility;
	}

	public BigInteger getTotalSubmited() {
		return this.totalSubmited;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}
}
