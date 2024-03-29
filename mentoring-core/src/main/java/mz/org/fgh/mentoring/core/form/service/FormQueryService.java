/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.model.Form;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface FormQueryService {

	String NAME = "mz.org.fgh.mentoring.core.form.service.FormQueryService";

	Form fetchByForm(final UserContext userContext, final Form form) throws BusinessException;

	List<Form> findBySelectedFilter(final UserContext userContext, final String code, final String name, final String programmaticAreaCode, final String partnerUUID) throws BusinessException;

	Form findFormByUuid(final UserContext userContext, final String uuid) throws BusinessException;

	List<Form> findSampleIndicatorForms() throws BusinessException;
}
