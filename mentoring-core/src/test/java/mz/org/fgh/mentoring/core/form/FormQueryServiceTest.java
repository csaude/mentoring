/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form;

import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_COLLECTED_SAMPLES;
import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_RECEIVED_SAMPLES;
import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_REJECTED_SAMPLES;
import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_TRANSPORTED_SAMPLES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.model.NumericAnswer;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormQuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.FormTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.IndicatorTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionProcessor;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.TutorProgramaticAreaTamplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormQueryService;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.formquestion.service.FormQuestionQueryService;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;
import mz.org.fgh.mentoring.core.indicator.service.IndicatorService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.programmaticarea.dao.ProgrammaticAreaDAO;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgrammaticArea;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.service.TutorProgrammaticAreaService;

/**
 * @author Stélio Moiane
 *
 */
public class FormQueryServiceTest extends AbstractSpringTest {

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private QuestionService questionService;

	@Inject
	private FormService formService;

	@Inject
	private FormQueryService formQueryService;

	@Inject
	private ProgrammaticAreaDAO programmaticAreaDAO;

	@Inject
	private FormQuestionQueryService formQuestionQueryService;

	@Inject
	private TutorProgrammaticAreaService tutorProgrammaticAreaService;

	@Inject
	private TutorService tutorService;

	@Inject
	private CareerService careerService;

	@Inject
	private HealthFacilityService healthFacilityService;

	@Inject
	private DistrictService districtService;

	@Inject
	private IndicatorService indicatorService;

	private Form createdform;

	private ProgrammaticArea programmaticArea;

	@Override
	public void setUp() throws BusinessException {
		final Form form = EntityFactory.gimme(Form.class, FormTemplate.VALID);
		this.programmaticArea = this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        form.getProgrammaticArea());

		final List<FormQuestion> formQuestions = EntityFactory.gimme(FormQuestion.class, 5,
		        FormQuestionTemplate.WITH_NO_FORM);

		for (final FormQuestion formQuestion : formQuestions) {
			this.questionService.createQuestion(this.getUserContext(), formQuestion.getQuestion());
		}

		this.createdform = this.formService.createForm(this.getUserContext(), form, new HashSet<>(formQuestions));
	}

	@Test
	public void shouldFetchByForm() throws BusinessException {

		final Form fetchedFrom = this.formQueryService.fetchByForm(this.getUserContext(), this.createdform);

		assertEquals(this.createdform.getCode(), fetchedFrom.getCode());
		assertFalse(fetchedFrom.getFormQuestions().isEmpty());

		fetchedFrom.getFormQuestions().stream().forEach(formQuestion -> {
			assertEquals(formQuestion.getForm().getCode(), fetchedFrom.getCode());
			assertNotNull(formQuestion.getQuestion());
		});
	}

	@Test
	public void shouldFindBySelectedFilter() throws BusinessException {
		final String code = null;
		final String name = null;
		final ProgrammaticArea programmaticArea = this.programmaticAreaDAO
		        .findById(this.createdform.getProgrammaticArea().getId());

		final List<Form> forms = this.formQueryService.findBySelectedFilter(this.getUserContext(), code, name,
		        programmaticArea.getCode());

		assertTrue(!forms.isEmpty());
		for (final Form form : forms) {
			assertEquals(form.getProgrammaticArea().getCode(), programmaticArea.getCode());
		}
	}

	@Test
	public void shouldFetchFormQuestionsByTutor() throws BusinessException {

		final TutorProgrammaticArea tutorProgrammaticArea = EntityFactory.gimme(TutorProgrammaticArea.class,
		        TutorProgramaticAreaTamplate.VALID);
		tutorProgrammaticArea.setProgrammaticArea(this.programmaticArea);
		final Tutor tutor = tutorProgrammaticArea.getTutor();
		this.careerService.createCareer(this.getUserContext(), tutor.getCareer());
		this.tutorService.createTutor(this.getUserContext(), tutor);
		this.tutorProgrammaticAreaService.mapTutorToProgramaticArea(this.getUserContext(), tutorProgrammaticArea);
		final UserContext context = this.getUserContext();
		context.setUuid(tutor.getUuid());

		final List<FormQuestion> formQuestions = this.formQuestionQueryService.fetchFormQuestionsByTutor(context);

		assertFalse(formQuestions.isEmpty());

		for (final FormQuestion formQuestion : formQuestions) {
			assertNotNull(formQuestion.getForm());
			assertNotNull(formQuestion.getForm().getProgrammaticArea());
			assertNotNull(formQuestion.getQuestion());
		}
	}

	@Test
	public void shouldFindSampleIndicatorForms() throws BusinessException {

		final Indicator indicator = EntityFactory.gimme(Indicator.class, IndicatorTemplate.VALID);
		this.careerService.createCareer(this.getUserContext(), indicator.getTutor().getCareer());
		this.tutorService.createTutor(this.getUserContext(), indicator.getTutor());

		final Question question1 = EntityFactory.gimme(Question.class, QuestionTemplate.NUMERIC_QUESTION,
		        new QuestionProcessor());
		question1.setUuid(NUMBER_OF_COLLECTED_SAMPLES.getValue());
		this.questionService.createQuestion(this.getUserContext(), question1);
		final FormQuestion formQuestion1 = EntityFactory.gimme(FormQuestion.class, FormQuestionTemplate.WITH_NO_FORM);
		formQuestion1.setQuestion(question1);

		final Question question2 = EntityFactory.gimme(Question.class, QuestionTemplate.NUMERIC_QUESTION,
		        new QuestionProcessor());
		question2.setUuid(NUMBER_OF_TRANSPORTED_SAMPLES.getValue());
		this.questionService.createQuestion(this.getUserContext(), question2);
		final FormQuestion formQuestion2 = EntityFactory.gimme(FormQuestion.class, FormQuestionTemplate.WITH_NO_FORM);
		formQuestion2.setQuestion(question2);

		final Question question3 = EntityFactory.gimme(Question.class, QuestionTemplate.NUMERIC_QUESTION,
		        new QuestionProcessor());
		question3.setUuid(NUMBER_OF_REJECTED_SAMPLES.getValue());
		this.questionService.createQuestion(this.getUserContext(), question3);
		final FormQuestion formQuestion3 = EntityFactory.gimme(FormQuestion.class, FormQuestionTemplate.WITH_NO_FORM);
		formQuestion3.setQuestion(question3);

		final Question question4 = EntityFactory.gimme(Question.class, QuestionTemplate.NUMERIC_QUESTION,
		        new QuestionProcessor());
		question4.setUuid(NUMBER_OF_RECEIVED_SAMPLES.getValue());
		this.questionService.createQuestion(this.getUserContext(), question4);

		final FormQuestion formQuestion4 = EntityFactory.gimme(FormQuestion.class, FormQuestionTemplate.WITH_NO_FORM);
		formQuestion4.setQuestion(question4);

		final Set<FormQuestion> formQuestions = new HashSet<>();
		formQuestions.add(formQuestion1);
		formQuestions.add(formQuestion2);
		formQuestions.add(formQuestion3);
		formQuestions.add(formQuestion4);

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        indicator.getForm().getProgrammaticArea());

		final Form form = indicator.getForm();
		this.formService.createForm(this.getUserContext(), form, formQuestions);

		this.districtService.createDistrict(this.getUserContext(), indicator.getHealthFacility().getDistrict());
		this.healthFacilityService.createHealthFacility(this.getUserContext(), indicator.getHealthFacility());

		final Answer answer1 = new NumericAnswer();
		answer1.setQuestion(question1);
		answer1.setValue(String.valueOf(50));

		final Answer answer2 = new NumericAnswer();
		answer2.setQuestion(question2);
		answer2.setValue(String.valueOf(10));

		final Answer answer3 = new NumericAnswer();
		answer3.setQuestion(question3);
		answer3.setValue(String.valueOf(40));

		final Answer answer4 = new NumericAnswer();
		answer4.setQuestion(question4);
		answer4.setValue(String.valueOf(30));

		this.indicatorService.createIndicator(this.getUserContext(), indicator, form,
		        Arrays.asList(answer1, answer2, answer3, answer4));

		final List<Form> forms = this.formQueryService.findSampleIndicatorForms();

		assertFalse(forms.isEmpty());
	}
}
