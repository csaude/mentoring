/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.answer;

import java.util.List;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Stélio Moiane
 *
 */
public interface AnswerQueryService {

	String NAME = "mz.org.fgh.mentoring.core.answer.AnswerQueryService";

	List<Answer> fetchAnswersByMentorship(final UserContext userContext, final Mentorship mentorship);
}
