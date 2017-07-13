/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.answer;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.answer.dao.AnswerDAO;
import mz.org.fgh.mentoring.core.answer.model.TextAnswer;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Stélio Moiane
 *
 */
@Service(AnswerQueryService.NAME)
public class AnswerQueryServiceImpl implements AnswerQueryService {

	@Inject
	private AnswerDAO answerDAO;

	@Override
	public List<TextAnswer> fetchAnswersByMentorship(final UserContext userContext, final Mentorship mentorship) {

		return this.answerDAO.fetchByMentorishipUuid(mentorship.getUuid(), LifeCycleStatus.ACTIVE);
	}
}
