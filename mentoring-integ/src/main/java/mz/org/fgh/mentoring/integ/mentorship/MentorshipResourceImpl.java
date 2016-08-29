/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.mentorship;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;

/**
 * @author Stélio Moiane
 *
 */
@Service(MentorshipResource.NAME)
@Path("mentorships")
public class MentorshipResourceImpl implements MentorshipResource {

	@Inject
	private MentorshipService mentorshipService;

	@Override
	public JResponse<Mentorship> createMentorshipProcess(final MentorshipBeanResource mentorshipBeanResource)
			throws BusinessException {

		final Mentorship mentorship = this.mentorshipService.createMentorship(mentorshipBeanResource.getUserContext(),
				mentorshipBeanResource.getMentorship(), mentorshipBeanResource.getForm(),
				mentorshipBeanResource.getAnswers());

		return JResponse.ok(mentorship).build();
	}
}
