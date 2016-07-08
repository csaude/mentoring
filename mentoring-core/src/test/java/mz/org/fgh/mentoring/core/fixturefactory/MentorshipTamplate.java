package mz.org.fgh.mentoring.core.fixturefactory;

import java.time.LocalDate;

import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

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
				this.add("startDate",
						this.random(LocalDate.now(), LocalDate.now()));
				this.add("endDate",
						this.random(LocalDate.now(), LocalDate.now()));
				this.add("tutor", one(Tutor.class, "name"));
			}
		});
	}

}
