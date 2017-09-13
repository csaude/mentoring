package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgrammaticArea;

public class TutorProgramaticAreaTamplate implements TemplateLoader{

	public static final String VALID = "valid";

	@Override
	public void load() {
		Fixture.of(TutorProgrammaticArea.class).addTemplate(VALID, new Rule() {
			{
				this.add("tutor", this.one(Tutor.class, TutorTemplate.VALID));
				this.add("programmaticArea", this.one(ProgrammaticArea.class, ProgrammaticAreaTemplate.VALID));
			}
		});
	}
}
