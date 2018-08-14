/**
 *
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.model.FormTarget;

/**
 * @author Stélio Moiane
 *
 */
public class FormTargetTemplate implements TemplateLoader {

	public static final String VALID = "VALID";

	@Override
	public void load() {
		Fixture.of(FormTarget.class).addTemplate(VALID, new Rule() {
			{
				this.add("form", this.one(Form.class, FormTemplate.VALID));
				this.add("career", this.one(Career.class, CareerTemplate.VALID));
				this.add("target", this.random(Integer.class, this.range(1, 10)));
			}
		});
	}
}
