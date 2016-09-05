/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;

/**
 * @author Stélio Moiane
 *
 */
public class HealthFacilityTemplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(HealthFacility.class).addTemplate(VALID, new Rule() {
			{
				this.add("district", this.one(District.class, DistrictTemplate.VALID));
				this.add("healthFacility", this.random("CS B.Gruveta", "CS Caiaia", "CS Cololo"));
			}
		});
	}
}
