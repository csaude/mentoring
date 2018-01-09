/*
 * Friends in Global Health - FGH © 2016
 */

package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.model.QuestionType;
import mz.org.fgh.mentoring.core.util.QuestionCategory;

/**
 * @author Eusebio Jose Maposse
 *
 */
public class QuestionTemplate implements TemplateLoader {

	public static final String VALID = "VALID";
	public static final String TEXT_QUESTION = "TEXT_QUESTION";
	public static final String NUMERIC_QUESTION = "NUMERIC_QUESTION";
	public static final String BOOLEAN_QUESTION = "BOOLEAN_QUESTION";

	@Override
	public void load() {
		Fixture.of(Question.class).addTemplate(VALID, new Rule() {
			{
				this.add("question", this.random("VCT", "MCH", "MMI", "JPA"));
				this.add("questionType", this.random(QuestionType.TEXT, QuestionType.BOOLEAN));
				this.add("questionCategory",
				        this.random(QuestionCategory.ACCURACY, QuestionCategory.PUNCTUALITY, QuestionCategory.TOTALITY,
				                QuestionCategory.PRECISION, QuestionCategory.RELIABILITY, QuestionCategory.INTEGRITY,
				                QuestionCategory.CONFIDENTIALITY));
			}
		});

		Fixture.of(Question.class).addTemplate(TEXT_QUESTION).inherits(VALID, new Rule() {
			{
				this.add("questionType", QuestionType.TEXT);
			}
		});

		Fixture.of(Question.class).addTemplate(NUMERIC_QUESTION).inherits(VALID, new Rule() {
			{
				this.add("questionType", QuestionType.NUMERIC);
			}
		});

		Fixture.of(Question.class).addTemplate(BOOLEAN_QUESTION).inherits(VALID, new Rule() {
			{
				this.add("questionType", QuestionType.BOOLEAN);
			}
		});
	}
}
