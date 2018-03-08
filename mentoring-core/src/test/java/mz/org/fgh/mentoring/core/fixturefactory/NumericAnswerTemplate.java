/**
 *
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.answer.model.NumericAnswer;

/**
 * @author Stélio Moiane
 *
 */
public class NumericAnswerTemplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(NumericAnswer.class).addTemplate(VALID, new Rule() {
			{
				this.add("numericValue", this.random(Integer.class, this.range(10, 30)));
			}
		});
	}
}
