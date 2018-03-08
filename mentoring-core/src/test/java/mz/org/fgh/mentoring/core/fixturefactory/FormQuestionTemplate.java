/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.question.model.Question;

/**
 * @author Eusebio Jose Maposse
 *
 */
public class FormQuestionTemplate implements TemplateLoader {

	public static final String VALID = "VALID";

	public static final String WITH_NO_FORM = "WITH_NO_FORM";

	@Override
	public void load() {
		Fixture.of(FormQuestion.class).addTemplate(VALID, new Rule() {
			{
				this.add("form", this.one(Form.class, FormTemplate.VALID));
				this.add("question", this.one(Question.class, QuestionTemplate.TEXT_QUESTION));
				this.add("mandatory", true);
			}
		});

		Fixture.of(FormQuestion.class).addTemplate(WITH_NO_FORM, new Rule() {
			{
				this.add("question", this.one(Question.class, QuestionTemplate.TEXT_QUESTION));
				this.add("mandatory", true);
			}
		});
	}
}
