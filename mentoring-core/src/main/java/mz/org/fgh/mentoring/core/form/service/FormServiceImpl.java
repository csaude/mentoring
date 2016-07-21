/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.service;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.dao.FormDAO;
import mz.org.fgh.mentoring.core.form.model.Form;

import org.springframework.stereotype.Service;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(FormService.NAME)
public class FormServiceImpl extends AbstractService implements FormService {

	@Inject
	private FormDAO formDAO;

	@Override
	public  Form createForm(final UserContext userContext, final Form form)  throws BusinessException {

		// TODO generate code just a sample
		final String code = this.formDAO.generateCode("MT", 8, "0");
		form.setCode(code);

		return this.formDAO.create(userContext.getId(), form);
	}

	@Override
	public Form updateForm(final UserContext userContext, final Form form)  throws BusinessException {

		return this.formDAO.update(userContext.getId(), form);
	}
}
