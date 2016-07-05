/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutor.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutor.dao.TutorDAO;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
@Service(TutorService.NAME)
public class TutorServiceImpl extends AbstractService implements TutorService {

	@Inject
	private TutorDAO tutorDAO;

	@Override
	public Tutor createTutor(final UserContext userContext, final Tutor tutor) throws BusinessException {

		// TODO generate code just a sample
		final String code = this.tutorDAO.generateCode("MT", 8, "0");
		tutor.setCode(code);

		return this.tutorDAO.create(userContext.getId(), tutor);
	}

	@Override
	public Tutor updateTutor(final UserContext userContext, final Tutor tutor) throws BusinessException {

		return this.tutorDAO.update(userContext.getId(), tutor);

	}
}
