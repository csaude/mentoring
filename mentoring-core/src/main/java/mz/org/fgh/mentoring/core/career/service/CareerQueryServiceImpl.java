/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.career.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.scripting.bsh.BshScriptUtils.BshExecutionException;
import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.career.dao.CareerDAO;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.model.CareerType;

/**
 * @author Stélio Moiane
 *
 */
@Service(CareerQueryService.NAME)
public class CareerQueryServiceImpl implements CareerQueryService {

	@Inject
	private CareerDAO careerDAO;

	@Override
	public List<Career> findCareersByCareerType(final UserContext userContext, final CareerType careerType)
			throws BusinessException {
		return this.careerDAO.findByCarrerType(careerType, LifeCycleStatus.ACTIVE);
	}

	@Override
	public Career findByCareerId(final UserContext userContext, final Long carrerId) {
		return this.careerDAO.findByCarrerId(carrerId, LifeCycleStatus.ACTIVE);
	}

	@Override
	public List<Career> findAllCareers(final UserContext userContext) throws BshExecutionException {
		return this.careerDAO.findAll(LifeCycleStatus.ACTIVE);
	}

	@Override
	public Career findCarrerByuuid(final UserContext userContext, final String uuid) throws BusinessException {
		return this.careerDAO.findByUuid(uuid);
	}
}
