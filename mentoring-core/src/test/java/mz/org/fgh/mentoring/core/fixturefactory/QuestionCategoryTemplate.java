/**
 *
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.co.mozview.frameworks.core.util.UuidFactory;
import mz.org.fgh.mentoring.core.question.model.QuestionsCategory;

/**
 * @author Stélio Moiane
 *
 */
public class QuestionCategoryTemplate implements TemplateLoader {

	public static final String VALID = "VALID";

	@Override
	public void load() {
		Fixture.of(QuestionsCategory.class).addTemplate(VALID, new Rule() {
			{
				this.add("category", UuidFactory.generate());
			}
		});
	}
}
