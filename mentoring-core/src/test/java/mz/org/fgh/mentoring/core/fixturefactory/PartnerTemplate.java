/**
 *
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.partner.model.Partner;

/**
 * @author Stélio Moiane
 *
 */
public class PartnerTemplate implements TemplateLoader {

	public static final String VALID = "VALID";

	@Override
	public void load() {
		Fixture.of(Partner.class).addTemplate(PartnerTemplate.VALID, new Rule() {
			{
				this.add("name", this.random("ICAP", "FGH", "EGPAF", "ARIEL", "ECHO"));
				this.add("description", "Parceiro implementador de programas de Cuidade & Tratamento");
			}
		});
	}

}
