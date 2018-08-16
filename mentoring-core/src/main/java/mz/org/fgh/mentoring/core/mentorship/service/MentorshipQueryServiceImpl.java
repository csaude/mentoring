/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship.service;

import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.mentorship.dao.MentorshipDAO;
import mz.org.fgh.mentoring.core.mentorship.model.IterationType;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(MentorshipQueryService.NAME)
public class MentorshipQueryServiceImpl implements MentorshipQueryService {
	@Inject
	private MentorshipDAO mentorshipDAO;

	@Override
	public List<Mentorship> fetchBySelectedFilter(final UserContext userContext, final String code, final String tutor, final String tutored,
												  final String formName, final String healthFacility, final String iterationType,
												  final Integer iterationNumber) {

		IterationType type = null;
		if(iterationType != null) {
			type = IterationType.valueOf(iterationType.toUpperCase());
		}
		return this.mentorshipDAO.fetchBySelectedFilter(code, tutor, tutored, formName, healthFacility, type, iterationNumber, LifeCycleStatus.ACTIVE);
	}
}
