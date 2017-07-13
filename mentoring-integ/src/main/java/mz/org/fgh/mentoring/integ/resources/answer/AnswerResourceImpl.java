/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.answer;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.answer.AnswerQueryService;
import mz.org.fgh.mentoring.core.answer.model.TextAnswer;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Stélio Moiane
 *
 */
@Service(AnswerResource.NAME)
@Path("answers")
public class AnswerResourceImpl extends AbstractResource implements AnswerResource {

	@Inject
	private AnswerQueryService answerQueryService;

	@Override
	public JResponse<List<TextAnswer>> fetchAnswersByMentorshipUuid(final String mentorshipUuid)
			throws BusinessException {

		final Mentorship mentorship = new Mentorship();
		mentorship.setUuid(mentorshipUuid);

		final List<TextAnswer> answers = this.answerQueryService.fetchAnswersByMentorship(this.getUserContetx(),
				mentorship);

		return JResponse.ok(answers).build();
	}

}
