/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship.service;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.mentorship.dao.MentorshipDAO;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

import org.springframework.stereotype.Service;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(MentorshipService.NAME)
public class MentorshipServiceImpl extends AbstractService implements MentorshipService {

	@Inject
	private MentorshipDAO mentorshipDAO;

	@Override
	public Mentorship createMentorship(final UserContext userContext, final Mentorship mentorship) throws BusinessException {

		// TODO generate code just a sample
		final String code = this.mentorshipDAO.generateCode("MT", 8, "0");
		mentorship.setCode(code);

		return this.mentorshipDAO.create(userContext.getId(), mentorship);
	}

	@Override
	public Mentorship updateMentorship(final UserContext userContext, final Mentorship mentorship) throws BusinessException {

		return this.mentorshipDAO.update(userContext.getId(), mentorship);
	}
}
