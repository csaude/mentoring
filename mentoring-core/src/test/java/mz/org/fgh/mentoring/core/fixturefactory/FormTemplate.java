/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.model.FormType;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
public class FormTemplate implements TemplateLoader {

	public static final String VALID = "VALID";

	@Override
	public void load() {
		Fixture.of(Form.class).addTemplate(VALID, new Rule() {
			{
				this.add("name", this.random("MMT", "DFR", "HRT"));
				this.add("programmaticArea", this.one(ProgrammaticArea.class, "valid"));
				this.add("formType", this.random(FormType.MENTORING, FormType.INDICATORS));
				this.add("target", this.random(Integer.class, this.range(1, 10)));
				this.add("formQuestions", this.has(5).of(FormQuestion.class, FormQuestionTemplate.WITH_NO_FORM));
			}
		});
	}
}
