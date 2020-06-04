/**
 *
 */
package mz.org.fgh.mentoring.core.session.service;

import java.time.LocalDate;
import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.location.model.Cabinet;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.session.model.PerformedSession;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.session.model.SubmitedSessions;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
public interface SessionQueryService {

	List<PerformedSession> findPerformedSessionsBySelectedFilter(final District distric, HealthFacility healthFacility,
	        ProgrammaticArea programmaticArea, Form form, Tutor tutor, Cabinet cabinet, LocalDate startDate,
	        LocalDate endDate);

	List<PerformedSession> findPerformedSessionsBySelectedFilterList(final District distric,
	        HealthFacility healthFacility, ProgrammaticArea programmaticArea, Form form, Tutor tutor, Cabinet cabinet,
	        LocalDate startDate, LocalDate endDate);
	
	List<SubmitedSessions> findNumberOfSessionsPerDistrict(UserContext userContext);

	List<PerformedSession> findPerformedSessionsByTutorAndForm(Tutor tutor, Form form, LocalDate startDate,
	        LocalDate endDate);

	List<Session> findSessionsWithDuplicatedUuids() throws BusinessException;

	List<Session> fetchSessionsByUuid(String sessionUuid) throws BusinessException;
	
	List<PerformedSession> findPerformedSessionsByTutor(Tutor tutor, LocalDate startDate,
	        LocalDate endDate);
	
	//Custom Reports
	
	List<PerformedSession> findPerformedSessionsBySelectedFilterHTS(LocalDate startDate, LocalDate endDate);
	
	List<PerformedSession> findPerformedSessionsBySelectedFilterNarrative(LocalDate startDate, LocalDate endDate);
	
	List<PerformedSession> findPerformedSessionsBySelectedFilterLast12Months();
	
	List<PerformedSession> findPerformedSessionsBySelectedFilterIndicators(LocalDate startDate, LocalDate endDate);
	
	List<PerformedSession> findPerformedSessionsBySelectedFilterIndicatorsList(LocalDate startDate, LocalDate endDate);
	
	List<PerformedSession> findPerformedSessionsBySelectedFilterHTS(LocalDate startDate, LocalDate endDate,String tutoredUuid);

}
