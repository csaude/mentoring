/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.mentorship.dao.MentorshipDAO;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(MentorshipQueryService.NAME)
public class MentorshipQueryServiceImpl implements MentorshipQueryService {

	@Inject
	private MentorshipDAO mentorshipDAO;

	@Override
	public List<Mentorship> fetchBySelectedFilter(final UserContext userContext, final String code, final String tutor,
	        final String tutored, final String formName, final String healthFacility) {

		return this.mentorshipDAO.fetchBySelectedFilter(code, tutor, tutored, formName, healthFacility,
		        LifeCycleStatus.ACTIVE);
	}
}
