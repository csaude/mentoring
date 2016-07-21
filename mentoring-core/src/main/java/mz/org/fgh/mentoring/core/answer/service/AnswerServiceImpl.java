/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.answer.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.answer.dao.AnswerDAO;
import mz.org.fgh.mentoring.core.answer.model.Answer;

/**
 * @author Stélio Moiane
 *
 */
@Service(AnswerService.NAME)
public class AnswerServiceImpl extends AbstractService implements AnswerService {

	@Inject
	private AnswerDAO answerDAO;

	@Override
	public <T extends Answer>  Answer createAnswer(final UserContext userContext, final Answer answer) throws BusinessException {
		return this.answerDAO.create(userContext.getId(), answer);
	}

}
