/**
 *
 */
package mz.org.fgh.mentoring.core.mentorship;

import java.time.LocalDate;

import javax.inject.Inject;

import org.junit.Ignore;
import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.model.TextAnswer;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.MentorshipTemplate;
import mz.org.fgh.mentoring.core.form.FormBuilder;
import mz.org.fgh.mentoring.core.location.service.CabinetService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.mentorship.model.IterationType;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;

/**
 * @author Stélio Moiane
 *
 */
@Ignore
@Service(MentorshipBuilder.NAME)
public class MentorshipBuilder extends AbstractSpringTest {

	public static final String NAME = "mz.org.fgh.mentoring.core.mentorship.MentorshipBuilder";

	@Inject
	private CareerService careerService;

	@Inject
	private TutorService tutorService;

	@Inject
	private TutoredService tutoredService;

	@Inject
	private CabinetService cabinetService;

	@Inject
	private DistrictService districtService;

	@Inject
	private HealthFacilityService healthFacilityService;

	@Inject
	private MentorshipService mentorshipService;

	@Inject
	private FormBuilder formBuilder;

	private Mentorship mentorship;

	@Override
	public void setUp() throws BusinessException {
	}

	public MentorshipBuilder mentorship() {
		this.mentorship = EntityFactory.gimme(Mentorship.class, MentorshipTemplate.VALID);
		return this;
	}

	public MentorshipBuilder withPerformedDate(final LocalDate performedDate) {
		this.mentorship.setPerformedDate(performedDate);
		return this;
	}

	public MentorshipBuilder withSession(final Session session) {
		this.mentorship.setSession(session);
		return this;
	}

	public Mentorship build() throws BusinessException {

		if (this.mentorship == null) {
			throw new BusinessException("You must call the mentorship() first... Thank you");
		}

		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutor().getCareer());
		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutored().getCareer());
		this.tutorService.createTutor(this.getUserContext(), this.mentorship.getTutor());
		this.tutoredService.createTutored(this.getUserContext(), this.mentorship.getTutored());
		this.cabinetService.createCabinet(this.getUserContext(), this.mentorship.getCabinet());

		this.mentorship.setForm(this.formBuilder.build());

		this.districtService.createDistrict(this.getUserContext(), this.mentorship.getHealthFacility().getDistrict());
		this.healthFacilityService.createHealthFacility(this.getUserContext(), this.mentorship.getHealthFacility());

		this.mentorship.getForm().getFormQuestions().forEach(formQuestion -> {

			final Answer answer = new TextAnswer();
			answer.setQuestion(formQuestion.getQuestion());
			answer.setValue("COMPETENTE");

			this.mentorship.addAnswer(answer);
		});

		this.mentorship.setIterationType(IterationType.PATIENT);
		this.mentorship.setIterationNumber(1);

		this.mentorshipService.createMentorship(this.getUserContext(), this.mentorship);

		return this.mentorship;
	}
}
