/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutor.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
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

	@Override
	public List<Tutor> findAllTutors(final UserContext userContext) throws BusinessException {
		return this.tutorDAO.findAll(LifeCycleStatus.ACTIVE);
	}
}
