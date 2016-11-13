/*
 * Friends in Global Health - FGH © 2016
 */

package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
public class TutorTemplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(Tutor.class).addTemplate(VALID, new Rule() {
			{
				this.add("name", this.random("Stelio Klesio", "Eusebio Jose", "Helio Estevao"));
				this.add("surname", this.random("Moiane", "Maposse", "Machabane"));
				this.add("carrer", this.one(Career.class, "valid"));
				this.add("phoneNumber", this.random("840665903", "840665903"));
			}
		});

	}
}
