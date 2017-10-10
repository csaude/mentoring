/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.formquestion.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.formquestion.dao.FormQuestionDAO;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

/**
 * @author Eusebio Jose Maposse
 * @author Stélio Moiane
 *
 */
@Service(FormQuestionQueryService.NAME)
public class FormQuestionQueryServiceImpl implements FormQuestionQueryService {

	@Inject
	private FormQuestionDAO formQuestionDAO;

	@Override
	public List<FormQuestion> fetchFormQuestionsByTutor(final UserContext userContext) {
		return this.formQuestionDAO.fetchByTutor(LifeCycleStatus.ACTIVE, userContext.getUuid());
	}
}
