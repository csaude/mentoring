/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.carrer.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.carrer.model.Carrer;

/**
 * @author Stélio Moiane
 *
 */
public interface CarrerService {

	String NAME = "mz.org.fgh.mentoring.core.carrer.service.CarrerService";

	Carrer createCarrer(final UserContext userContext, final Carrer carrer) throws BusinessException;

}
