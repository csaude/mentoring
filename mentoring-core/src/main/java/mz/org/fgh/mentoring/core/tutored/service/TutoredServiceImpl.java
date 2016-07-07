/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutored.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutored.dao.TutoredDAO;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(TutoredService.NAME)
public class TutoredServiceImpl extends AbstractService implements TutoredService {

	@Inject
	private TutoredDAO tutorandoDao;

	@Override
	public Tutored createTutored(final UserContext userContext, final Tutored tutored) throws BusinessException {

		// TODO generate code just a sample
		final String code = this.tutorandoDao.generateCode("MT", 8, "0");
		tutored.setCode(code);

		return this.tutorandoDao.create(userContext.getId(), tutored);
	}

	@Override
	public Tutored updateTutored(final UserContext userContext, final Tutored tutored) throws BusinessException {

		return this.tutorandoDao.update(userContext.getId(), tutored);
	}
}