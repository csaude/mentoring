/**
 *
 */
package mz.org.fgh.mentoring.core.indicator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.model.BooleanAnswer;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.IndicatorTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;
import mz.org.fgh.mentoring.core.indicator.service.IndicatorService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;

/**
 * @author Stélio Moiane
 *
 */
public class IndicatorServiceTest extends AbstractSpringTest {

	@Inject
	private TutorService tutorService;

	@Inject
	private QuestionService questionService;

	@Inject
	private FormService formService;

	@Inject
	private CareerService careerService;

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private HealthFacilityService heathFacilityService;

	@Inject
	private DistrictService districtService;

	@Inject
	private IndicatorService indicatorService;

	private Indicator indicator;

	private Question question;

	private Form form;

	@Override
	public void setUp() throws BusinessException {

		this.indicator = EntityFactory.gimme(Indicator.class, IndicatorTemplate.VALID);
		this.careerService.createCareer(this.getUserContext(), this.indicator.getTutor().getCareer());
		this.tutorService.createTutor(this.getUserContext(), this.indicator.getTutor());

		this.question = EntityFactory.gimme(Question.class, QuestionTemplate.BOOLEAN_QUESTION);
		this.questionService.createQuestion(this.getUserContext(), this.question);

		final Set<Question> questions = new HashSet<>();
		questions.add(this.question);

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        this.indicator.getForm().getProgrammaticArea());

		this.form = this.indicator.getForm();
		this.formService.createForm(this.getUserContext(), this.form, questions);

		this.districtService.createDistrict(this.getUserContext(), this.indicator.getHealthFacility().getDistrict());
		this.heathFacilityService.createHealthFacility(this.getUserContext(), this.indicator.getHealthFacility());
	}

	@Test
	public void shouldCreateIndicator() throws BusinessException {

		final Answer answer = new BooleanAnswer();
		answer.setQuestion(this.question);
		answer.setValue(String.valueOf(Boolean.TRUE));

		this.indicatorService.createIndicator(this.getUserContext(), this.indicator, this.form, Arrays.asList(answer));

		TestUtil.assertCreation(this.indicator);
	}

}
