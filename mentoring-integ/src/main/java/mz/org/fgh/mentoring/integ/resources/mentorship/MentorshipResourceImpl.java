/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.mentorship;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipQueryService;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Stélio Moiane
 *
 */
@Service(MentorshipResource.NAME)
@Path("mentorships")
public class MentorshipResourceImpl  extends AbstractResource implements MentorshipResource {

	@Inject
	private MentorshipService mentorshipService;
	@Inject
	private MentorshipQueryService mentorshipQueryService;

	@Override
	public JResponse<Mentorship> createMentorshipProcess(final MentorshipBeanResource mentorshipBeanResource)
			throws BusinessException {

		final Mentorship mentorship = this.mentorshipService.createMentorship(mentorshipBeanResource.getUserContext(),
				mentorshipBeanResource.getMentorship(), mentorshipBeanResource.getForm(),
				mentorshipBeanResource.getAnswers());

		return JResponse.ok(mentorship).build();
	}

	@Override
	public JResponse<List<Mentorship>> findBySelectedFilter(String code, String tutor, String tutored)
			throws BusinessException {
		
		final List<Mentorship> mentorships = this.mentorshipQueryService.findBySelectedFilter(getUserContetx(),code, tutor,
				tutored);
		return JResponse.ok(mentorships).build();
	}
}
