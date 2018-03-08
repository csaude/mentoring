/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import mz.org.fgh.mentoring.core.util.LocalDateAdapter;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SampleIndicator {

	private String district;

	private String healthFacility;

	private String form;

	private String indicator;

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate referredMonth;

	private Integer value;

	public SampleIndicator() {
	}

	public SampleIndicator(final String district, final String healthFacility, final String form,
	        final String indicator, final LocalDate referredMonth, final Integer value) {
		this.district = district;
		this.healthFacility = healthFacility;
		this.form = form;
		this.indicator = indicator;
		this.referredMonth = referredMonth;
		this.value = value;
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

	public String getIndicator() {
		return this.indicator;
	}

	public LocalDate getReferredMonth() {
		return this.referredMonth;
	}

	public Integer getValue() {
		return this.value;
	}
}
