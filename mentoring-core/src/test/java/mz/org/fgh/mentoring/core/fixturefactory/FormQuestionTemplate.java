package mz.org.fgh.mentoring.core.fixturefactory;

import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.question.model.FormQuestion;
import mz.org.fgh.mentoring.core.question.model.Question;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

/**
 * @author Eusebio Jose Maposse
 *
 */
public class FormQuestionTemplate implements TemplateLoader{


	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(FormQuestion.class).addTemplate(VALID, new Rule() {
			{
				this.add("form", one(Form.class, "valid"));
				add("question", one(Question.class, "valid"));

			}
		});
	}

}
