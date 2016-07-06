/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutorando.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutorando.model.Tutorando;

import org.springframework.stereotype.Service;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(TutorandoService.NAME)
public class TutorandoServiceImpl extends AbstractService implements
		TutorandoService {

	@Override
	public Tutor createTutor(final UserContext userContext,
			final Tutorando tutorando) throws BusinessException {

		return null;
	}

	@Override
	public Tutor updateTutor(final UserContext userContext,
			final Tutorando tutorando) throws BusinessException {
		return null;
	}
}
