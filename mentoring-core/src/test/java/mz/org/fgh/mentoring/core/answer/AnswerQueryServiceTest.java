/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.answer;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.service.AnswerQueryService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.mentorship.MentorshipBuilder;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Stélio Moiane
 *
 */
public class AnswerQueryServiceTest extends AbstractSpringTest {

	@Inject
	private AnswerQueryService answerQueryService;

	@Inject
	private MentorshipBuilder mentorshipBuilder;

	private Mentorship mentorship;

	@Override
	public void setUp() throws BusinessException {
		this.mentorship = this.mentorshipBuilder.mentorship().build();
	}

	@Test
	public void shouldFetchAnswersByMentorship() {

		final List<Answer> answers = this.answerQueryService.fetchAnswersByMentorship(this.getUserContext(),
				this.mentorship);

		Assert.assertFalse(answers.isEmpty());

		for (final Answer answer : answers) {
			Assert.assertNotNull(answer.getQuestion());
		}
	}
}
