/**
 *
 */
package mz.org.fgh.mentoring.core.session.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PerformedSession {

	private String district;
	private String healthFacility;
	private String formName;
	private Long totalPerformed;

	public PerformedSession() {
	}

	public PerformedSession(final String district, final String healthFacility, final String formName,
	        final Long totalPerformed) {
		this.district = district;
		this.healthFacility = healthFacility;
		this.formName = formName;
		this.totalPerformed = totalPerformed;
	}

	public String getDistrict() {
		return this.district;
	}

	public String getHealthFacility() {
		return this.healthFacility;
	}

	public String getFormName() {
		return this.formName;
	}

	public Long getTotalPerformed() {
		return this.totalPerformed;
	}
}
