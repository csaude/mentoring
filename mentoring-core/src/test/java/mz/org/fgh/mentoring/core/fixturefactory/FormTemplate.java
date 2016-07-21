/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.sector.model.Sector;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

/**
 * @author Eusebio Jose Maposse
 *
 */
public class FormTemplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(Form.class).addTemplate(VALID, new Rule() {
			{
				this.add("name", this.random("MMT", "DFR", "HRT"));
				add("sector", one(Sector.class, "valid"));
			}
		});
	}
}
