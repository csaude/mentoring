/*
 * Friends in Global Health - FGH © 2016
 */

package mz.org.fgh.mentoring.core.fixturefactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.answer.model.TextAnswer;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.location.model.Cabinet;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Eusebio Jose Maposse
 *
 */
public class MentorshipTemplate implements TemplateLoader {

	public static final String VALID = "VALID";
	public static final String WITH_ANSWERS = "WITH_ANSWERS";
	public static final String DATE_PERFORMED_MAY_12_2018 = "PERFORMED_DATE_MAY_12_2018";
	public static final String DATE_PERFORMED_MAY_20_2018 = "PERFORMED_DATE_MAY_20_2018";

	public static final LocalDate DATE_MAY_12_2018 = LocalDate.of(2018, 5, 12);
	public static final LocalDate DATE_MAY_20_2018 = LocalDate.of(2018, 5, 20);

	@Override
	public void load() {
		Fixture.of(Mentorship.class).addTemplate(MentorshipTemplate.VALID, new Rule() {
			{
				this.add("startDate", LocalDateTime.now());
				this.add("endDate", LocalDateTime.now());
				this.add("tutor", this.one(Tutor.class, TutorTemplate.VALID));
				this.add("tutored", this.one(Tutored.class, TutoredTemplate.VALID));
				this.add("form", this.one(Form.class, FormTemplate.VALID));
				this.add("healthFacility", this.one(HealthFacility.class, HealthFacilityTemplate.VALID));
				this.add("performedDate", LocalDate.now());
				this.add("cabinet", this.one(Cabinet.class, CabinetTemplate.VALID));
			}
		});

		Fixture.of(Mentorship.class).addTemplate(MentorshipTemplate.WITH_ANSWERS).inherits(MentorshipTemplate.VALID, new Rule() {
			{
				this.add("answers", this.has(5).of(TextAnswer.class, TextAnswerTemplate.VALID));
				this.add("form", this.one(Form.class, FormTemplate.WITH_FORM_QUESTIONS));
			}
		});

		Fixture.of(Mentorship.class).addTemplate(MentorshipTemplate.DATE_PERFORMED_MAY_12_2018).inherits(MentorshipTemplate.VALID, new Rule() {
			{
				this.add("performedDate", MentorshipTemplate.DATE_MAY_12_2018);
			}
		});

		Fixture.of(Mentorship.class).addTemplate(MentorshipTemplate.DATE_PERFORMED_MAY_20_2018).inherits(MentorshipTemplate.VALID, new Rule() {
			{
				this.add("performedDate", MentorshipTemplate.DATE_MAY_20_2018);
			}
		});
	}
}
