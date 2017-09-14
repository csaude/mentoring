/**
 *
 */
package mz.org.fgh.mentoring.core.tutorprogramaticarea.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.dao.TutorProgrammaticAreaDAO;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgrammaticArea;

/**
 * @author Stélio Moiane
 *
 */
@Service(TutorProgrammaticAreaQueryService.NAME)
public class TutorProgrammaticAreaQueryServiceImpl implements TutorProgrammaticAreaQueryService {

	@Inject
	private TutorProgrammaticAreaDAO tutorProgrammaticAreaDAO;

	@Override
	public TutorProgrammaticArea findTutorMapByTutorAndProgrammaticArea(final UserContext userContext,
	        final Tutor tutor, final ProgrammaticArea programmaticArea) throws BusinessException {
		return this.tutorProgrammaticAreaDAO.findByTutorAndProgrammaticArea(tutor, programmaticArea,
		        LifeCycleStatus.ACTIVE);
	}
}
