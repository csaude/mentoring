/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.answer.model.TextAnswer;

/**
 * @author Stélio Moiane
 *
 */
public class TextAnswerTemplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(TextAnswer.class).addTemplate(VALID, new Rule() {
			{
				this.add("textValue", this.random("COMPETENTE", "NAO SATISFATORIO", "NA"));
			}
		});
	}
}
