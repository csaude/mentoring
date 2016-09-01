/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

/**
 * @author Eusebio Jose Maposse
 *
 */
public class TutoredTemplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(Tutored.class).addTemplate(VALID, new Rule() {
			{
				this.add("name", this.random("Domingos Jose", "Eusebio Jose", "Helio Estevao"));
				this.add("surname", this.random("Maposse", "Maposse", "Maposse"));
				this.add("phoneNumber", this.random("840665903", "824537865", "876543567"));
			}
		});
	}
}
