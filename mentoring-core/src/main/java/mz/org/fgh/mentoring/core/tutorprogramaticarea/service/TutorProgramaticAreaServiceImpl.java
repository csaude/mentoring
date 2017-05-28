package mz.org.fgh.mentoring.core.tutorprogramaticarea.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.dao.TutorProgramaticAreaDao;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgramaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(TutorProgramaticAreaService.NAME)
public class TutorProgramaticAreaServiceImpl extends AbstractService implements TutorProgramaticAreaService {

	@Inject
	private TutorProgramaticAreaDao tutorProgramaticAreaDao;

	@Override
	public TutorProgramaticArea createTutorProgramaticArea(final UserContext userContext,
			final TutorProgramaticArea tutorProgramaticArea) throws BusinessException {

		final TutorProgramaticArea createdTutorProgramaticArea = this.tutorProgramaticAreaDao.create(userContext.getUuid(),
				tutorProgramaticArea);

		return createdTutorProgramaticArea;
	}

	@Override
	public TutorProgramaticArea updateTutorProgramaticArea(final UserContext userContext,
			final TutorProgramaticArea tutorProgramaticArea) throws BusinessException {
		return this.tutorProgramaticAreaDao.create(userContext.getUuid(), tutorProgramaticArea);
	}

}
