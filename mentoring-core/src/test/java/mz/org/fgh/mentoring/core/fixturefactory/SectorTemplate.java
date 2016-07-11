/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.sector.model.Sector;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

/**
 * @author Eusebio Jose Maposse
 *
 */
public class SectorTemplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(Sector.class).addTemplate(VALID, new Rule() {
			{
				this.add("name", this.random("SIS", "MA"));
				this.add("description", this.random("Sector de Sistemas de Informacao", "Sector de Monitoria e Avaliacao"));
			}
		});
	}
}
