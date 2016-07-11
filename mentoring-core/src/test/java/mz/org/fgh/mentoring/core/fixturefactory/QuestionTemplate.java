/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.util.QuestionType;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

/**
 * @author Eusebio Jose Maposse
 *
 */
public class QuestionTemplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(Question.class).addTemplate(VALID, new Rule() {
			{
				this.add("question", this.random("VCT", "MCH", "MMI"));
				this.add("questionType", this.random(QuestionType.TEXT, QuestionType.TEXT));
			}
		});
	}
}
