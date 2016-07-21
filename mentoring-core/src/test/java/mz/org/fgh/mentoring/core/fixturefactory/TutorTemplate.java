/*
 * Friends in Global Health - FGH © 2016
 */

package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.util.Category;

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
				this.add("category", this.random(Category.DATA_MANAGER, Category.DATA_OFFICER));
			}
		});
	}
}
