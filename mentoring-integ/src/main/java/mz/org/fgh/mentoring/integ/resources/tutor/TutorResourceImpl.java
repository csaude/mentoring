/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutor;

import javax.ws.rs.Path;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.integ.config.AbstractUserContext;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;
import com.sun.jersey.spi.inject.Inject;

/**
 * @author Stélio Moiane
 *
 */
@SuppressWarnings("deprecation")
@Service(TutorResource.NAME)
@Path("tutors")
public class TutorResourceImpl extends AbstractUserContext implements TutorResource {

	@Inject
	private TutorService tutorService;

	@Override
	public JResponse<Tutor> createTutor(final Tutor tutor) throws BusinessException {

		tutorService.createTutor(getUserContext(), tutor);

		return JResponse.ok(tutor).build();
	}

}
