/*
 * Friends in Global Health - FGH © 2016
 */

package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;

/**
 * @author Eusebio Jose Maposse
 * @author Stélio Moiane
 *
 */
public class ProgrammaticAreaTemplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(ProgrammaticArea.class).addTemplate(VALID, new Rule() {
			{
				this.add("name", this.random("SIS", "MA"));
				this.add("description",
						this.random("Sector de Sistemas de Informacao", "Sector de Monitoria e Avaliacao"));
			}
		});
	}
}
