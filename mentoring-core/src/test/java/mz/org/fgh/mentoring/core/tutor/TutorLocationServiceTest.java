/**
 *
 */
package mz.org.fgh.mentoring.core.tutor;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.scheduling.annotation.Async;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.HealthFacilityTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.TutorTemplate;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.model.TutorLocation;
import mz.org.fgh.mentoring.core.tutor.service.TutorLocationService;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;

/**
 * @author Stélio Moiane
 *
 */
public class TutorLocationServiceTest extends AbstractSpringTest {

	@Inject
	private TutorLocationService tutorLocationService;

	@Inject
	private CareerService careerService;

	@Inject
	private TutorService tutorService;

	@Inject
	private DistrictService districtService;

	@Inject
	private HealthFacilityService healthFacilityService;

	private Tutor tutor;

	private List<HealthFacility> locations;

	@Override
	public void setUp() throws BusinessException {
		this.tutor = this.buildTutor();
		this.locations = this.buildLocations();
	}

	private List<HealthFacility> buildLocations() {
		return EntityFactory.gimme(HealthFacility.class, 10, HealthFacilityTemplate.VALID, result -> {
			if (result instanceof HealthFacility) {
				final HealthFacility healthFacility = (HealthFacility) result;
				try {
					this.districtService.createDistrict(this.getUserContext(), healthFacility.getDistrict());
					this.healthFacilityService.createHealthFacility(this.getUserContext(), healthFacility);
				} catch (final BusinessException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Tutor buildTutor() {
		return EntityFactory.gimme(Tutor.class, TutorTemplate.VALID, result -> {
			if (result instanceof Tutor) {

				TutorLocationServiceTest.this.tutor = (Tutor) result;

				try {
					TutorLocationServiceTest.this.careerService.createCareer(TutorLocationServiceTest.this.getUserContext(),
							TutorLocationServiceTest.this.tutor.getCareer());
					TutorLocationServiceTest.this.tutorService.createTutor(TutorLocationServiceTest.this.getUserContext(),
							TutorLocationServiceTest.this.tutor);
				} catch (final BusinessException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Async
	@Test
	public void shouldAllocateTutorLocations() throws BusinessException {

		final List<TutorLocation> tutorLocations = this.tutorLocationService.allocateTutorLocations(this.getUserContext(), this.tutor,
				this.locations);

		Assert.assertEquals(tutorLocations.size(), this.locations.size());
		tutorLocations.forEach(tutorLocation -> Assert.assertEquals(tutorLocation.getTutor().getUuid(), this.tutor.getUuid()));
	}

}
