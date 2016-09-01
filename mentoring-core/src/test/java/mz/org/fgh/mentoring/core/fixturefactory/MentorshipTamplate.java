/*
 * Friends in Global Health - FGH © 2016
 */

package mz.org.fgh.mentoring.core.fixturefactory;

import java.time.LocalDateTime;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Eusebio Jose Maposse
 *
 */
public class MentorshipTamplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(Mentorship.class).addTemplate(VALID, new Rule() {
			{
				this.add("startDate", LocalDateTime.now());
				this.add("endDate", LocalDateTime.now());
				this.add("tutor", this.one(Tutor.class, TutorTemplate.VALID));
				this.add("tutored", this.one(Tutored.class, TutoredTemplate.VALID));
				this.add("form", this.one(Form.class, FormTemplate.VALID));
			}
		});
	}
}
