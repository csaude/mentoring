/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.career.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.career.model.Career;

/**
 * @author Stélio Moiane
 *
 */
public interface CareerService {

	String NAME = "mz.org.fgh.mentoring.core.carrer.service.CarrerService";

	Career createCareer(final UserContext userContext, final Career carrer) throws BusinessException;

}
