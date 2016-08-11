/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.dao.FormDAO;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(FormQueryService.NAME)
public class FormQueryServiceImpl implements FormQueryService {

	@Inject
	private FormDAO formDAO;

	@Override
	public Form fetchByForm(final UserContext userContext, final Form form) throws BusinessException {
		return formDAO.fetchByFormId(form.getId());
	}

	@Override
	public List<Form> findBySelectedFilter(final UserContext userContext, String code, String name, ProgrammaticArea programaticArea) {
		return formDAO.findBySelectedFilter(code, name, programaticArea, LifeCycleStatus.ACTIVE);
	}

}
