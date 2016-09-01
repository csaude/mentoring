/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.answer.model.BooleanAnswer;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

/**
 * @author Stélio Moiane
 *
 */
public class BooleanAnswerTemplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {
		
		Fixture.of(BooleanAnswer.class).addTemplate(VALID, new Rule() {
			{
				this.add("booleanValue", this.random(Boolean.FALSE, Boolean.TRUE));
			}
		});
	}

}
