/**
 *
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import mz.co.mozview.frameworks.core.fixtureFactory.TemplateLoader;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.session.model.SessionStatus;

/**
 * @author Stélio Moiane
 *
 */
public class SessionTemplate implements TemplateLoader {

	public static final String VALID = "VALID";

	@Override
	public void load() {
		Fixture.of(Session.class).addTemplate(VALID, new Rule() {
			{
				this.add("startDate", LocalDateTime.now());
				this.add("endDate", LocalDateTime.now());
				this.add("performedDate", LocalDate.now());
				this.add("status", this.random(SessionStatus.COMPLETE, SessionStatus.INCOMPLETE));
				this.add("reason", "NA");
				this.add("mentorships", this.has(5).of(Mentorship.class, MentorshipTemplate.WITH_ANSWERS));
			}
		});
	}
}
