/**
 *
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.six2six.fixturefactory.processor.Processor;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;

/**
 * @author Stélio Moiane
 *
 */
public class MentorshipProcessor implements Processor {

	private final UserContext userContext;

	private final FormService formService;

	private final CareerService careerService;

	private final ProgrammaticAreaService programmaticAreaService;

	private final DistrictService districtService;

	private final HealthFacilityService heathFacilityService;

	private final QuestionService questionService;

	private final TutorService tutorService;

	private final TutoredService tutoredService;

	public MentorshipProcessor(final UserContext userContext, final FormService formService,
	        final CareerService careerService, final ProgrammaticAreaService programmaticAreaService,
	        final DistrictService districtService, final HealthFacilityService heathFacilityService,
	        final QuestionService questionService, final TutorService tutorService,
	        final TutoredService tutoredService) {
		this.userContext = userContext;
		this.formService = formService;
		this.careerService = careerService;
		this.programmaticAreaService = programmaticAreaService;
		this.districtService = districtService;
		this.heathFacilityService = heathFacilityService;
		this.questionService = questionService;
		this.tutorService = tutorService;
		this.tutoredService = tutoredService;
	}

	@Override
	public void execute(final Object object) {

		if (object instanceof Mentorship) {
			final Mentorship mentorship = (Mentorship) object;

			final Set<Question> questions = mentorship.getForm().getFromQuestions().stream()
			        .map(FormQuestion::getQuestion).collect(Collectors.toSet());

			questions.forEach(question -> {
				try {
					this.questionService.createQuestion(this.userContext, question);
					mentorship.getAnswers().forEach(answer -> {
						final Question newQuestion = new Question();
						newQuestion.setUuid(question.getUuid());
						answer.setQuestion(newQuestion);
					});
				}
				catch (final BusinessException e) {
					e.printStackTrace();
				}
			});

			try {
				this.districtService.createDistrict(this.userContext, mentorship.getHealthFacility().getDistrict());
				this.heathFacilityService.createHealthFacility(this.userContext, mentorship.getHealthFacility());
				this.careerService.createCareer(this.userContext, mentorship.getTutor().getCareer());
				this.careerService.createCareer(this.userContext, mentorship.getTutored().getCareer());
				this.tutoredService.createTutored(this.userContext, mentorship.getTutored());
				this.tutorService.createTutor(this.userContext, mentorship.getTutor());
				this.programmaticAreaService.createProgrammaticArea(this.userContext,
				        mentorship.getForm().getProgrammaticArea());

				this.formService.createForm(this.userContext, mentorship.getForm(), questions);
			}
			catch (final BusinessException e) {
				e.printStackTrace();
			}
		}
	}
}
