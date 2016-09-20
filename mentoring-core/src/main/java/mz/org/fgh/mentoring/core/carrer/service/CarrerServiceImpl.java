/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.carrer.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.carrer.dao.CarrerDAO;
import mz.org.fgh.mentoring.core.carrer.model.Carrer;

/**
 * @author Stélio Moiane
 *
 */
@Service(CarrerService.NAME)
public class CarrerServiceImpl extends AbstractService implements CarrerService {

	@Inject
	private CarrerDAO carrerDAO;

	@Override
	public Carrer createCarrer(final UserContext userContext, final Carrer carrer) throws BusinessException {

		return this.carrerDAO.create(userContext.getId(), carrer);
	}
}
