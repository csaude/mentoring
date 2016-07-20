/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutor;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;

/**
 * @author Stélio Moiane
 *
 */
@Service(TutorResource.NAME)
@Path("tutors")
public class TutorResourceImpl implements TutorResource {

	@Inject
	private TutorService tutorService;

	@Override
	public JResponse<Tutor> createTutor(final TutorBeanResource tutorBeanResource) throws BusinessException {

		final Tutor tutor = this.tutorService.createTutor(tutorBeanResource.getUserContext(),
				tutorBeanResource.getTutor());

		return JResponse.ok(tutor).build();
	}
}
