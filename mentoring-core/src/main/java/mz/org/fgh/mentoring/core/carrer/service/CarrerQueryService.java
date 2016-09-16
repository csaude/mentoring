/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.carrer.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.carrer.model.Carrer;
import mz.org.fgh.mentoring.core.carrer.model.CarrerType;

/**
 * @author Stélio Moiane
 *
 */
public interface CarrerQueryService {
	String NAME = "mz.org.fgh.mentoring.core.carrer.service.CarrerQueryService";

	List<Carrer> findCarrersByCarrerType(final UserContext userContext, final CarrerType carrerType)
			throws BusinessException;
	Carrer findByCarrerId(final UserContext userContext, final Long carrerId);
}
