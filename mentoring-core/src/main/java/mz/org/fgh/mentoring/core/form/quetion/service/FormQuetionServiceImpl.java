/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.quetion.service;

/*
 * Friends in Global Health - FGH © 2016
 */
import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.quetion.dao.FormQuetionDAO;
import mz.org.fgh.mentoring.core.form.quetion.model.FormQuetion;

import org.springframework.stereotype.Service;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(FormQuetionService.NAME)
public class FormQuetionServiceImpl extends AbstractService implements FormQuetionService {

	@Inject
	private FormQuetionDAO formQuetionDAO;

	@Override
	public FormQuetion createFormQuetion(final UserContext userContext, final FormQuetion formQuetion)
			throws BusinessException {

		return this.formQuetionDAO.create(userContext.getId(), formQuetion);
	}

	@Override
	public FormQuetion updateFormQuetion(final UserContext userContext, final FormQuetion formQuetion)
			throws BusinessException {

		return this.formQuetionDAO.update(userContext.getId(), formQuetion);
	}
}
