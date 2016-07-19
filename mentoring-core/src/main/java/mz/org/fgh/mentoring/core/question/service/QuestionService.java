/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.question.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.question.model.Question;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface QuestionService {

	String NAME = "mz.org.fgh.mentoring.core.question.service.QuestionService";

	Question createQuestion(final UserContext userContext, final  Question question) throws BusinessException;

	Question updateQuestion(final UserContext userContext, final  Question question) throws BusinessException;
}
