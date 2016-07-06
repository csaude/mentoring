/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutorando.service;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutorando.dao.TutorandoDAO;
import mz.org.fgh.mentoring.core.tutorando.model.Tutorando;

import org.springframework.stereotype.Service;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(TutorandoService.NAME)
public class TutorandoServiceImpl extends AbstractService implements
		TutorandoService {

	@Inject
	private TutorandoDAO tutorandoDao;

	@Override
	public Tutorando createTutorando(final UserContext userContext,
			final Tutorando tutorando) throws BusinessException {

		// TODO generate code just a sample
		final String code = this.tutorandoDao.generateCode("MT", 8, "0");
		tutorando.setCode(code);

		return this.tutorandoDao.create(userContext.getId(), tutorando);
	}

	@Override
	public Tutorando updateTutorando(final UserContext userContext,
			final Tutorando tutorando) throws BusinessException {

		return tutorandoDao.update(userContext.getId(), tutorando);
	}
}
