/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.tutorando.model.Tutorando;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

/**
 * @author Eusebio Jose Maposse
 *
 */
public class TutorandoTemplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(Tutorando.class).addTemplate(VALID, new Rule() {
			{
				this.add("name", this.random("Domingos Jose", "Eusebio Jose", "Helio Estevao"));
				this.add("surname", this.random("Moiane", "Marta", "Muianga"));
				this.add("phoneNumber", this.random("840665903", "824537865", "876543567"));
			}
		});
	}
}
