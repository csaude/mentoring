/**
 *
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.co.mozview.frameworks.core.util.UuidFactory;
import mz.org.fgh.mentoring.core.location.model.Cabinet;

/**
 * @author Stélio Moiane
 *
 */
public class CabinetTemplate implements TemplateLoader {

	public static final String VALID = "VALID";

	@Override
	public void load() {

		Fixture.of(Cabinet.class).addTemplate(VALID, new Rule() {
			{
				this.add("name", this.random("CCR" + UuidFactory.generate(), "CPF" + UuidFactory.generate(),
				        "BS" + UuidFactory.generate()));
			}
		});
	}
}
