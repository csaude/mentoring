/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutored.service;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutored.dao.TutoredDAO;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

import org.springframework.stereotype.Service;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(TutoredService.NAME)
public class TutoredServiceImpl extends AbstractService implements
		TutoredService {

	@Inject
	private TutoredDAO tutorandoDao;

	@Override
	public Tutored createTutorando(final UserContext userContext,
			final Tutored tutorando) throws BusinessException {

		// TODO generate code just a sample
		final String code = this.tutorandoDao.generateCode("MT", 8, "0");
		tutorando.setCode(code);

		return this.tutorandoDao.create(userContext.getId(), tutorando);
	}

	@Override
	public Tutored updateTutorando(final UserContext userContext,
			final Tutored tutorando) throws BusinessException {

		return tutorandoDao.update(userContext.getId(), tutorando);
	}
}
