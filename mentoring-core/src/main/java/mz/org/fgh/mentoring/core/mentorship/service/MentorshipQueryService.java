/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship.service;

import java.util.List;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.mentorship.model.IterationType;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface MentorshipQueryService {

	String NAME = "mz.org.fgh.mentoring.core.mentorship.service.MentorshipQueryService";

	public List<Mentorship> fetchBySelectedFilter(final UserContext userContext, String code, final String tutor,
												  final String tutored, final String formName, final String healthFacility,
												  final String iterationType, final Integer iterationNumber);
}
