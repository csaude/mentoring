/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.indicator;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.org.fgh.mentoring.core.answer.model.AnswerHelper;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.util.LocalDateAdapter;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IndicatorHelper {

	private Indicator indicator;

	private List<AnswerHelper> answerHelpers;

	private String districtUuid;

	private String healthFacilityUuid;

	private String formUuid;

	public IndicatorHelper() {
	}

	public IndicatorHelper(final String districtUuid, final String healthFacilityUuid, final String formUuid) {
		this.districtUuid = districtUuid;
		this.healthFacilityUuid = healthFacilityUuid;
		this.formUuid = formUuid;
	}

	public Indicator getIndicator() {
		if (this.indicator != null) {
			this.indicator.setId(null);
		}
		return this.indicator;
	}

	public void setIndicator(final Indicator indicator) {
		this.indicator = indicator;
	}

	public List<AnswerHelper> getAnswerHelpers() {
		return this.answerHelpers;
	}

	public void setAnswerHelpers(final List<AnswerHelper> answerHelpers) {
		this.answerHelpers = answerHelpers;
	}

	public District getDistrict() {

		if (this.districtUuid == null) {
			return null;
		}

		final District district = new District();
		district.setUuid(this.districtUuid);

		return district;
	}

	public HealthFacility getHealthFacility() {

		if (this.healthFacilityUuid == null) {
			return null;
		}

		final HealthFacility healthFacility = new HealthFacility();
		healthFacility.setUuid(this.healthFacilityUuid);

		return healthFacility;
	}

	public Form getForm() {

		if (this.formUuid == null) {
			return null;
		}

		final Form form = new Form();
		form.setUuid(this.formUuid);

		return form;
	}

	public LocalDate getLocalDate(final String date) {

		if (date == null) {
			return null;
		}

		return new LocalDateAdapter().unmarshal(date);
	}
}
