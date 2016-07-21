/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.quetion.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.quetion.model.FormQuetion;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface FormQuetionService {

	String NAME = "mz.org.fgh.mentoring.core.form.quetion.service.FormQuetionService";

	FormQuetion createFormQuetion(final UserContext userContext, final FormQuetion formQuetion) throws BusinessException;

	FormQuetion updateFormQuetion(final UserContext userContext, final FormQuetion formQuetion) throws BusinessException;
}
