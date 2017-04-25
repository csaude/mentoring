package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;
import mz.org.fgh.mentoring.core.tutortutored.model.TutorTudored;

/**
 * @author Eusebio Jose Maposse
 *
 */
public class TutorTutoredTamplate implements TemplateLoader {

	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(TutorTudored.class).addTemplate(VALID, new Rule() {
			{
				this.add("tutor", this.one(Tutor.class, TutorTemplate.VALID));
				this.add("tutored", this.one(Tutored.class, TutoredTemplate.VALID));
			}
		});
	}

}
