/**
 *
 */
package mz.org.fgh.mentoring.core.session.service;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.location.model.Cabinet;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.session.dao.SessionDAO;
import mz.org.fgh.mentoring.core.session.model.PerformedSession;
import mz.org.fgh.mentoring.core.session.model.Session;
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
	        final Tutor tutor, final Cabinet cabinet, final LocalDate startDate, final LocalDate endDate) {

		return this.sessionDAO.findBySelectedFilter(distric, healthFacility, programmaticArea, form, tutor, cabinet,
		        startDate, endDate, LifeCycleStatus.ACTIVE);
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

	@Override
	public List<Session> findSessionsWithDuplicatedUuids() throws BusinessException {
		return this.sessionDAO.findWithDuplicatedUuids(LifeCycleStatus.ACTIVE);
	}

	@Override
	public List<Session> fetchSessionsByUuid(final String sessionUuid) throws BusinessException {
		return this.sessionDAO.fetchSessionsByUuid(sessionUuid, LifeCycleStatus.ACTIVE);
	}

	@Override
	public List<PerformedSession> findPerformedSessionsBySelectedFilterList(final District distric,
	        final HealthFacility healthFacility, final ProgrammaticArea programmaticArea, final Form form,
	        final Tutor tutor, final Cabinet cabinet, final LocalDate startDate, final LocalDate endDate) {

		return this.sessionDAO.findBySelectedFilterList(distric, healthFacility, programmaticArea, form, tutor, cabinet,
		        startDate, endDate, LifeCycleStatus.ACTIVE);
	}

	@Override
	public List<PerformedSession> findPerformedSessionsBySelectedFilterHTS(LocalDate startDate, LocalDate endDate) {
		
		return this.sessionDAO.findBySelectedFilterHTS(startDate, endDate);
	}

	@Override
	public List<PerformedSession> findPerformedSessionsBySelectedFilterNarrative(LocalDate startDate,
			LocalDate endDate) {
		return this.sessionDAO.findBySelectedFilterNarrative(startDate, endDate);
	}

	@Override
	public List<PerformedSession> findPerformedSessionsBySelectedFilterLast12Months() {
		return this.sessionDAO.findBySelectedFilterLast12Months();
	}

	@Override
	public List<PerformedSession> findPerformedSessionsByTutor(Tutor tutor, LocalDate startDate, LocalDate endDate) {
		return this.sessionDAO.findByTutor(tutor, startDate, endDate);
	}
}
