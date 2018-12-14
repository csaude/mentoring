/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.mentorship;

import com.sun.jersey.api.JResponse;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipQueryService;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;
import mz.org.fgh.mentoring.core.session.model.PerformedSession;
import mz.org.fgh.mentoring.core.session.model.SubmitedSessions;
import mz.org.fgh.mentoring.core.session.service.SessionQueryService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;
import mz.org.fgh.mentoring.integ.resources.mentorship.dto.SessionDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * @author Stélio Moiane
 *
 */
@Service(MentorshipResource.NAME)
@Path("mentorships")
public class MentorshipResourceImpl extends AbstractResource implements MentorshipResource {

	@Inject
	private MentorshipService mentorshipService;

	@Inject
	private MentorshipQueryService mentorshipQueryService;

	@Inject
	private SessionQueryService sessionQueryService;

	@Override
	public JResponse<Mentorship> createMentorshipProcess(final MentorshipBeanResource mentorshipBeanResource)
	        throws BusinessException {

		final Mentorship mentorship = this.mentorshipService.createMentorship(mentorshipBeanResource.getUserContext(),
		        mentorshipBeanResource.getMentorship());

		return JResponse.ok(mentorship).build();
	}

	@Override
	public JResponse<List<Mentorship>> findBySelectedFilter(final String code, final String tutor, final String tutored,
	        final String form, final String healthFacility, final String iterationType, final Integer iterationNumber, final String lifeCycleStatus,
			final String performedStartDate, final String performedEndDate) throws BusinessException {

		LocalDate performedStartDateParam = null;
		LocalDate performedEndDateParam = null;
		if(performedStartDate != null) {
			try {
				performedStartDateParam = LocalDate.parse(performedStartDate);
			} catch (DateTimeParseException e) {
				throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST)
						.entity("performedStartDate parameter should be of the form yyyy-mm-dd e.g. 2018-12-31")
						.build()
				);
			}
		}

		if(performedEndDate != null) {
			try {
				performedEndDateParam = LocalDate.parse(performedEndDate);
			} catch (DateTimeParseException e) {
				throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST)
						.entity("performedEndDate parameter should be of the form yyyy-mm-dd e.g. 2018-12-31")
						.build()
				);
			}
		}

		final List<Mentorship> mentorships = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContetx(),
		        code, tutor, tutored, form, healthFacility, iterationType, iterationNumber, lifeCycleStatus, performedStartDateParam, performedEndDateParam);

		return JResponse.ok(mentorships).build();
	}

	@Override
	public JResponse<MentorshipBeanResource> synchronizeMentorships(final MentorshipBeanResource resource)
	        throws BusinessException {

		this.mentorshipService.synchronizeMentorships(resource.getUserContext(), resource.getSessions());
		resource.setSessionUuids();

		return JResponse.ok(resource).build();
	}

	@Override
	public JResponse<List<SubmitedSessions>> findSubmitedSessions() throws BusinessException {

		final List<SubmitedSessions> sessions = this.sessionQueryService
		        .findNumberOfSessionsPerDistrict(this.getUserContetx());

		return JResponse.ok(sessions).build();
	}

	@Override
	public JResponse<List<PerformedSession>> findPerformedSessions(final String districtUuid,
	        final String healthFacilityUuid, final String formUuid, final String programmaticAreaUuid,
	        final String startDate, final String endDate) throws BusinessException {

		final SessionDTO sessionDTO = new SessionDTO(districtUuid, healthFacilityUuid, formUuid, programmaticAreaUuid,
		        startDate, endDate);

		final List<PerformedSession> performedSessions = this.sessionQueryService.findPerformedSessionsBySelectedFilter(
		        sessionDTO.getDistrict(), sessionDTO.getHealthFacility(), sessionDTO.getProgrammaticArea(),
		        sessionDTO.getForm(), sessionDTO.getStartDate(), sessionDTO.getEndDate());

		return JResponse.ok(performedSessions).build();
	}

	@Override
	public JResponse<List<PerformedSession>> findPerformedSessionsByTutorAndForm(final String tutorUuid,
	        final String formUuid, final String startDate, final String endDate) throws BusinessException {

		final SessionDTO sessionDTO = new SessionDTO(tutorUuid, formUuid, startDate, endDate);

		final List<PerformedSession> performedSessions = this.sessionQueryService.findPerformedSessionsByTutorAndForm(
		        sessionDTO.getTutor(), sessionDTO.getForm(), sessionDTO.getStartDate(), sessionDTO.getEndDate());

		return JResponse.ok(performedSessions).build();
	}
}
