/*
 * Friends in Global Health - FGH © 2016
 */

package mz.org.fgh.mentoring.core.fixturefactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
public class IndicatorTemplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(Indicator.class).addTemplate(VALID, new Rule() {
			{
				this.add("tutor", this.one(Tutor.class, TutorTemplate.VALID));
				this.add("form", this.one(Form.class, FormTemplate.VALID));
				this.add("healthFacility", this.one(HealthFacility.class, HealthFacilityTemplate.VALID));
				this.add("performedDate", LocalDateTime.now());
				this.add("referredMonth", LocalDate.now());
			}
		});
	}
}
