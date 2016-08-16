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
	private TutoredDAO tutoredDao;

	@Override
	public List<Tutored> findTutoredsBySelectedFilter(UserContext userContext, String code, String name, String surname,
			String phoneNumber) throws BusinessException {
		return tutoredDao.findBySelectedFilter(code, name, surname, phoneNumber, LifeCycleStatus.ACTIVE);
	}

}
