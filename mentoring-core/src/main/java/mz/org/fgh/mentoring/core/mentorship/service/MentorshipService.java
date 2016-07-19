/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface MentorshipService {

	String NAME = "mz.org.fgh.mentoring.core.mentorship.service.MentorshipService";

	Mentorship createMentorship(final UserContext userContext, final Mentorship mentorship) throws BusinessException;

	Mentorship updateMentorship(final UserContext userContext, final Mentorship mentorship) throws BusinessException;
}
