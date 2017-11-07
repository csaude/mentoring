/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.mentorship;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.jayway.restassured.response.Response;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.fixturefactory.MentorshipTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;
import mz.org.fgh.mentoring.integ.config.IntegAbstractSpringTest;
import mz.org.fgh.mentoring.integ.resources.mentorship.AnswerHelper;
import mz.org.fgh.mentoring.integ.resources.mentorship.MentorshipBeanResource;
import mz.org.fgh.mentoring.integ.resources.mentorship.MentorshipHelper;
import mz.org.fgh.mentoring.integ.util.Server;

/**
 * @author Stélio Moiane
 *
 */
public class MentorshipResourseTest extends IntegAbstractSpringTest {

	@Inject
	private TutorService tutorService;

	@Inject
	private TutoredService tutoredService;

	@Inject
	private QuestionService questionService;

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private FormService formService;

	@Inject
	private DistrictService districtService;

	@Inject
	private HealthFacilityService heathFacilityService;

	@Inject
	private CareerService careerService;

	@Inject
	private ApplicationContext applicationContext;

	private Form form;

	private List<AnswerHelper> answers;

	private Mentorship mentorship;

	private HttpServer server;

	private Tutor tutor;

	@Override
	public void setUp() throws BusinessException {

		this.server = new Server().uriBase("http://localhost/services").port(8081)
				.resourcesPackage("mz.org.fgh.mentoring.integ").context(this.applicationContext).initialize();

		this.mentorship = EntityFactory.gimme(Mentorship.class, MentorshipTemplate.VALID);
		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutor().getCareer());
		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutored().getCareer());
		this.tutor = this.tutorService.createTutor(this.getUserContext(), this.mentorship.getTutor());
		this.tutoredService.createTutored(this.getUserContext(), this.mentorship.getTutored());

		final List<Question> questions = EntityFactory.gimme(Question.class, 5, QuestionTemplate.TEXT_QUESTION);
		this.answers = new ArrayList<>();

		for (final Question question : questions) {
			this.questionService.createQuestion(this.getUserContext(), question);

			final AnswerHelper answer = new AnswerHelper(question.getUuid(), null, "COMPETEMTE");

			this.answers.add(answer);
		}

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
				this.mentorship.getForm().getProgrammaticArea());

		this.form = this.mentorship.getForm();
		this.formService.createForm(this.getUserContext(), this.form, new HashSet<>(questions));

		this.districtService.createDistrict(this.getUserContext(), this.mentorship.getHealthFacility().getDistrict());
		this.heathFacilityService.createHealthFacility(this.getUserContext(), this.mentorship.getHealthFacility());
	}

	@Test
	public void shouldSyncMentorshipProcess() {

		final MentorshipBeanResource resource = new MentorshipBeanResource();

		resource.setMentorships(Arrays.asList(
				new MentorshipHelper(this.mentorship, "12-04-2017 23:30:28", "12-04-2017 23:30:28", this.answers),
				new MentorshipHelper(this.mentorship, "12-04-2017 23:30:28", "12-04-2017 23:30:28", this.answers),
				new MentorshipHelper(this.mentorship, "12-04-2017 23:30:28", "12-04-2017 23:30:28", this.answers)));

		final UserContext context = this.getUserContext();
		context.setPhoneNumber(this.tutor.getPhoneNumber());

		resource.setUserContext(context);

		final Response post = given().contentType("application/json").body(resource).when()
				.post("/services/mentorships/sync");

		assertNotNull(post);
	}

	@Override
	public void tearDown() {
		super.tearDown();
		this.server.stop();
	}
}