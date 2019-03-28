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
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.career.model.CareerType;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorQueryService;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Stélio Moiane
 *
 */
@Service(TutorResource.NAME)
@Path(TutorResourceImpl.TUTOR_RESOURCE_PATH)
public class TutorResourceImpl extends AbstractResource implements TutorResource {
	public static final String TUTOR_RESOURCE_PATH = "tutors";
	@Inject
	private TutorService tutorService;

	@Inject
	private TutorQueryService tutorQueryService;

	@Override
	public JResponse<Tutor> createTutor(final TutorBeanResource tutorBeanResource) throws BusinessException {

		final Tutor tutor = tutorBeanResource.getTutor();

		try {
			this.tutorQueryService.fetchTutorByUuid(tutorBeanResource.getUserContext(), tutor.getUuid());
		}
		catch (final BusinessException ex) {
			this.tutorService.createTutor(tutorBeanResource.getUserContext(), tutor);
		}

		return JResponse.ok(tutor).build();
	}

	@Override
	public JResponse<List<Tutor>> findTutors(final String code, final String name, final String surname,
	        final CareerType careerType, final String phoneNumber) throws BusinessException {

		final List<Tutor> tutors = this.tutorQueryService.findTutorsBySelectedFilter(this.getUserContetx(), code, name,
		        surname, careerType, phoneNumber);

		return JResponse.ok(tutors).build();
	}

	@Override
	public JResponse<Tutor> updateTutor(final TutorBeanResource tutorBeanResource) throws BusinessException {

		final Tutor tutor = this.tutorService.updateTutor(tutorBeanResource.getUserContext(),
		        tutorBeanResource.getTutor());

		return JResponse.ok(tutor).build();
	}

	@Override
	public JResponse<Tutor> fetchTutorByUuid(final String uuid) throws BusinessException {

		final Tutor tutor = this.tutorQueryService.fetchTutorByUuid(this.getUserContetx(), uuid);

		return JResponse.ok(tutor).build();
	}

	@Override
	public JResponse<Tutor> resetPassword(final UserContext userContext) throws BusinessException {

		final Tutor tutor = this.tutorQueryService.fetchTutorByEmail(userContext, userContext.getEmail());
		this.tutorService.resetPassword(userContext, tutor);

		return JResponse.ok(tutor).build();
	}
}
