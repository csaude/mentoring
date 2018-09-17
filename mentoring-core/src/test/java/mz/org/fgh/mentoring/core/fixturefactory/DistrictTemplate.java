/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.co.mozview.frameworks.core.util.UuidFactory;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.Province;

/**
 * @author Stélio Moiane
 *
 */
public class DistrictTemplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(District.class).addTemplate(VALID, new Rule() {
			{
				this.add("province", this.random(Province.MAPUTO, Province.GAZA, Province.INHAMBANE, Province.SOFALA,
				        Province.TETE, Province.ZAMBEZIA, Province.NAMPULA, Province.MANICA, Province.CABO_DELEGADO));
				this.add("district", UuidFactory.generate());
			}
		});
	}
}
