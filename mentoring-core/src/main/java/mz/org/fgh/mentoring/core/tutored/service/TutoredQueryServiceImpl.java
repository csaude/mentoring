/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutored.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutored.dao.TutoredDAO;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(TutoredQueryService.NAME)
public class TutoredQueryServiceImpl implements TutoredQueryService {

	@Inject
	private TutoredDAO tutoredDAO;

	@Override
	public List<Tutored> findTutoredsBySelectedFilter(final UserContext userContext, final String uuid,
			final String code, final String name, final String surname, final String phoneNumber, final String tutored)
			throws BusinessException {
		return this.tutoredDAO.findBySelectedFilter(uuid, code, name, surname, phoneNumber, tutored,
				LifeCycleStatus.ACTIVE);
	}

	@Override
	public List<Tutored> findBySelectedFilterByTutor(final UserContext userContext,final Long tutorId, final String code,
													 final String name,final String surname,final String phoneNumber) throws BusinessException {
		return tutoredDAO.findBySelectedFilterByTutor(tutorId ,code, name, surname, phoneNumber, LifeCycleStatus.ACTIVE);
	}

	@Override
	public Tutored findTutoredByUuid(final UserContext userContext, final String uuid) throws BusinessException {
		return this.tutoredDAO.findByUuid(uuid);
	}

	@Override
	public List<Tutored> findTutoredsByUser(final String userUuid) {
		return this.tutoredDAO.fetchByUser(userUuid, LifeCycleStatus.ACTIVE);
	}
}
