/**
 *
 */
package mz.org.fgh.mentoring.core.session.service;

import java.time.LocalDate;
import java.util.List;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.session.model.PerformedSession;
import mz.org.fgh.mentoring.core.session.model.SubmitedSessions;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
public interface SessionQueryService {

	List<PerformedSession> findPerformedSessionsBySelectedFilter(final District distric,
	        final HealthFacility healthFacility, final ProgrammaticArea programmaticArea, final Form form,
	        final LocalDate startDate, final LocalDate endDate);

	List<SubmitedSessions> findNumberOfSessionsPerDistrict(UserContext userContext);

	List<PerformedSession> findPerformedSessionsByTutorAndForm(Tutor tutor, Form form, LocalDate startDate,
	        LocalDate endDate);
}
