/**
 *
 */
package mz.org.fgh.mentoring.core.tutor.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.tutor.dao.TutorLocationDAO;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.model.TutorLocation;

/**
 * @author Stélio Moiane
 *
 */
@Service(TutorLocationServiceImpl.NAME)
public class TutorLocationServiceImpl extends AbstractService implements TutorLocationService {

	public static final String NAME = "mz.org.fgh.mentoring.core.tutor.service.TutorLocationServiceImpl";

	@Inject
	private TutorLocationDAO tutorLocationDAO;

	@Override
	public List<TutorLocation> allocateTutorLocations(final UserContext userContext, final Tutor tutor, final List<HealthFacility> locations)
			throws BusinessException {

		final List<TutorLocation> tutorLocations = new ArrayList<>();

		locations.forEach(location -> {
			final TutorLocation tutorLocation = new TutorLocation();
			tutorLocation.setTutor(tutor);
			tutorLocation.setLocation(location);

			this.tutorLocationDAO.create(userContext.getUuid(), tutorLocation);

			tutorLocations.add(tutorLocation);
		});

		return tutorLocations;
	}

	@Override
	public List<TutorLocation> findByTutorId(Long id) throws BusinessException {
		return tutorLocationDAO.findByTutorId(id, LifeCycleStatus.ACTIVE);
	}
}
