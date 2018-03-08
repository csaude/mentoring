/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.util.LocalDateAdapter;

/**
 * @author Stélio Moiane
 *
 */
public class DuplicatedIndicator {

	private final Long healthFacilityId;

	private final Long formId;

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private final LocalDate referredMonth;

	private final Long total;

	public DuplicatedIndicator(final Long healthFacilityId, final Long formId, final LocalDate referredMonth,
	        final Long total) {
		this.healthFacilityId = healthFacilityId;
		this.formId = formId;
		this.referredMonth = referredMonth;
		this.total = total;
	}

	public HealthFacility getHealthFacility() {
		final HealthFacility healthFacility = new HealthFacility();
		healthFacility.setId(this.healthFacilityId);
		return healthFacility;
	}

	public Form getForm() {
		final Form form = new Form();
		form.setId(this.formId);
		return form;
	}

	public LocalDate getReferredMonth() {
		return this.referredMonth;
	}

	public Long getTotal() {
		return this.total;
	}
}
