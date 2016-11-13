/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.career.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.career.dao.CareerDAO;
import mz.org.fgh.mentoring.core.career.model.Career;

/**
 * @author Stélio Moiane
 *
 */
@Service(CareerService.NAME)
public class CarrerServiceImpl extends AbstractService implements CareerService {

	@Inject
	private CareerDAO careerDAO;

	@Override
	public Career createCareer(final UserContext userContext, final Career career) throws BusinessException {

		return this.careerDAO.create(userContext.getId(), career);
	}
}
