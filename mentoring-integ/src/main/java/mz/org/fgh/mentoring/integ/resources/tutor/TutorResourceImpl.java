/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutor;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorQueryService;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Stélio Moiane
 *
 */
@Service(TutorResource.NAME)
@Path("tutors")
public class TutorResourceImpl extends AbstractResource implements TutorResource {

	@Inject
	private TutorService tutorService;

	@Inject
	private TutorQueryService tutorQueryService;

	@Override
	public JResponse<Tutor> createTutor(final TutorBeanResource tutorBeanResource) throws BusinessException {

		final Tutor tutor = this.tutorService.createTutor(tutorBeanResource.getUserContext(),
				tutorBeanResource.getTutor(), tutorBeanResource.getCarrer());

		return JResponse.ok(tutor).build();
	}

	@Override
	public JResponse<List<Tutor>> findTutors(final String code, final String name, final String surname,
			final String carrer, final String phoneNumber) throws BusinessException {

		final List<Tutor> tutors = this.tutorQueryService.findTutorsBySelectedFilter(getUserContetx(), code, name, surname, carrer, phoneNumber);

		return JResponse.ok(tutors).build();
	}

	@Override
	public JResponse<Tutor> updateTutor(final TutorBeanResource tutorBeanResource) throws BusinessException {

		final Tutor tutor = this.tutorService.updateTutor(tutorBeanResource.getUserContext(),
				tutorBeanResource.getTutor());

		return JResponse.ok(tutor).build();
	}
}
