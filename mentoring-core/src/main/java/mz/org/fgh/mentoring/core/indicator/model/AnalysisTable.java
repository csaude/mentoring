/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AnalysisTable {

	public static final String NAME = "AnalysisTable";

	private static final int DECIMAL_PLACES = 2;

	private String form;

	private BigDecimal collectedSamples;

	private BigDecimal referredSamples;

	private BigDecimal rejectedSamples;

	private BigDecimal receivedResult;

	private BigDecimal transportation;

	private BigDecimal rejection;

	private BigDecimal result;

	private BigDecimal totalResult;

	public AnalysisTable(final String form, final BigDecimal collectedSamples, final BigDecimal referredSamples,
	        final BigDecimal rejectedSamples, final BigDecimal receivedResult, final BigDecimal transportation,
	        final BigDecimal rejection, final BigDecimal result) {
		this.form = form;

		this.collectedSamples = collectedSamples;
		this.referredSamples = referredSamples;
		this.rejectedSamples = rejectedSamples;
		this.receivedResult = receivedResult;

		this.transportation = this.formatTo2DecimalPlaces(transportation);
		this.rejection = this.formatTo2DecimalPlaces(rejection);
		this.result = this.formatTo2DecimalPlaces(result);
		this.totalResult = this.result.add(this.rejection);
	}

	public AnalysisTable() {
	}

	public String getForm() {
		return this.form;
	}

	public BigDecimal getCollectedSamples() {
		return this.collectedSamples;
	}

	public BigDecimal getReferredSamples() {
		return this.referredSamples;
	}

	public BigDecimal getRejectedSamples() {
		return this.rejectedSamples;
	}

	public BigDecimal getReceivedResult() {
		return this.receivedResult;
	}

	public BigDecimal getTransportation() {
		return this.transportation;
	}

	public BigDecimal getRejection() {
		return this.rejection;
	}

	public BigDecimal getResult() {
		return this.result;
	}

	public BigDecimal getTotalResult() {
		return this.totalResult;
	}

	private BigDecimal formatTo2DecimalPlaces(final BigDecimal value) {

		if (value == null) {
			return BigDecimal.ZERO;
		}

		return value.setScale(DECIMAL_PLACES, RoundingMode.HALF_EVEN);
	}
}
