/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.question.service;

import java.util.List;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.model.QuestionType;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface QuestionQueryService {

	String NAME = "mz.org.fgh.mentoring.core.question.service.QuestionQueryService";

	List<Question> findQuestionsBySelectedFilter(final UserContext userContext, final String code,
			final String question, final QuestionType questionType);

}
