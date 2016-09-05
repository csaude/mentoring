/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.carrer.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.carrer.dao.CarrerDAO;
import mz.org.fgh.mentoring.core.carrer.model.Carrer;
import mz.org.fgh.mentoring.core.carrer.model.CarrerType;

/**
 * @author Stélio Moiane
 *
 */
@Service(CarrerQueryService.NAME)
public class CarrerQueryServiceImpl implements CarrerQueryService {

	@Inject
	private CarrerDAO carrerDAO;

	@Override
	public List<Carrer> findCarrersByCarrerType(final UserContext userContext, final CarrerType carrerType)
			throws BusinessException {
		return this.carrerDAO.findByCarrerType(carrerType, LifeCycleStatus.ACTIVE);
	}
}
