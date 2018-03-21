/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SampleIndicator {

	public static final String NAME = "SampleIndicator";

	private String district;

	private String healthFacility;

	private String form;

	private BigDecimal collectedSamples;

	private BigDecimal rejectedSamples;

	private BigDecimal receivedSamples;

	private BigDecimal transportedSamples;

	public SampleIndicator() {
	}

	public SampleIndicator(final String district, final String healthFacility, final String form,
	        final BigDecimal collectedSamples, final BigDecimal rejectedSamples, final BigDecimal receivedSamples,
	        final BigDecimal transportedSamples) {
		this.district = district;
		this.healthFacility = healthFacility;
		this.form = form;
		this.collectedSamples = collectedSamples;
		this.rejectedSamples = rejectedSamples;
		this.receivedSamples = receivedSamples;
		this.transportedSamples = transportedSamples;
	}

	public String getDistrict() {
		return this.district;
	}

	public String getHealthFacility() {
		return this.healthFacility;
	}

	public String getForm() {
		return this.form;
	}

	public BigDecimal getCollectedSamples() {
		return this.collectedSamples;
	}

	public BigDecimal getRejectedSamples() {
		return this.rejectedSamples;
	}

	public BigDecimal getReceivedSamples() {
		return this.receivedSamples;
	}

	public BigDecimal getTransportedSamples() {
		return this.transportedSamples;
	}
}
