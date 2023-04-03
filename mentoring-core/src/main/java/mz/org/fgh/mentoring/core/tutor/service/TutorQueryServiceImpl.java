/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutor.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.util.PropertyValues;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.career.model.CareerType;
import mz.org.fgh.mentoring.core.tutor.dao.TutorDAO;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(TutorQueryService.NAME)
public class TutorQueryServiceImpl implements TutorQueryService {

	@Inject
	private TutorDAO tutorDAO;

	@Inject
	private PropertyValues propertyValues;

	@Override
	public List<Tutor> findTutorsBySelectedFilter(final UserContext userContext, final String code, final String name,
	        final String surname, final CareerType careerType, final String phoneNumber) throws BusinessException {
		return this.tutorDAO.findBySelectedFilter(code, name, surname, phoneNumber, careerType, LifeCycleStatus.ACTIVE);
	}

	@Override
	public Tutor fetchTutorByUuid(final UserContext userContext, final String uuid) throws BusinessException {

		Tutor tutor = null;

		try {
			tutor = this.tutorDAO.fetchByUuid(uuid);
		}
		catch (final NoResultException ex) {
			throw new BusinessException(this.propertyValues.getPropValues("no.result.found"));
		}

		return tutor;
	}

	@Override
	public Tutor fetchTutorByEmail(final UserContext userContext, final String email) throws BusinessException {

		Tutor tutor = null;

		try {
			tutor = this.tutorDAO.fecthByEmail(email, LifeCycleStatus.ACTIVE);
		}
		catch (final NoResultException ex) {
			throw new BusinessException(this.propertyValues.getPropValues("no.result.found"));
		}

		return tutor;
	}

	@Override
	public List<Tutor> findTutorsBySelectedFilter(UserContext userContext, String code, String name, String surname,
			CareerType careerType, String phoneNumber, String partnerName) throws BusinessException {
		
		return this.tutorDAO.findBySelectedFilter(code, name, surname, phoneNumber,partnerName , careerType, LifeCycleStatus.ACTIVE);
	}
}
