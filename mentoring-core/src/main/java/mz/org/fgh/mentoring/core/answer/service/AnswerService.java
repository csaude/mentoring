/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.answer.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.answer.model.Answer;

/**
 * @author Stélio Moiane
 *
 */
public interface AnswerService {

	String NAME = "mz.org.fgh.mentoring.core.answer.service.AnswerService";

	Answer createAnswer(final UserContext userContext, final Answer answer) throws BusinessException;

}
