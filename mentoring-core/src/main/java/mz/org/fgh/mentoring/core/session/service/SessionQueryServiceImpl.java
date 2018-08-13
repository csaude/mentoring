/**
 *
 */
package mz.org.fgh.mentoring.core.session.service;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.session.dao.SessionDAO;
import mz.org.fgh.mentoring.core.session.model.PerformedSession;
import mz.org.fgh.mentoring.core.session.model.SubmitedSessions;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
@Service(SessionQueryServiceImpl.NAME)
public class SessionQueryServiceImpl implements SessionQueryService {

	public static final String NAME = "mz.org.fgh.mentoring.core.session.service.SessionQueryServiceImpl";

	@Inject
	private SessionDAO sessionDAO;

	@Override
	public List<PerformedSession> findPerformedSessionsBySelectedFilter(final District distric,
	        final HealthFacility healthFacility, final ProgrammaticArea programmaticArea, final Form form,
	        final LocalDate startDate, final LocalDate endDate) {

		return this.sessionDAO.findBySelectedFilter(distric, healthFacility, programmaticArea, form, startDate, endDate,
		        LifeCycleStatus.ACTIVE);
	}

	@Override
	public List<SubmitedSessions> findNumberOfSessionsPerDistrict(final UserContext userContext) {
		return this.sessionDAO.findNumberOfSessionsPerDistrict(LifeCycleStatus.ACTIVE);
	}

	@Override
	public List<PerformedSession> findPerformedSessionsByTutorAndForm(final Tutor tutor, final Form form,
	        final LocalDate startDate, final LocalDate endDate) {

		return this.sessionDAO.findByTutorAndForm(tutor, form, startDate, endDate);
	}
}
