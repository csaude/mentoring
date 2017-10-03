/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.TutorProgramaticAreaTamplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormQueryService;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.formquestion.service.FormQuestionQueryService;
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

	private Form createdform;

	private ProgrammaticArea programmaticArea;

	@Override
	public void setUp() throws BusinessException {
		final Form form = EntityFactory.gimme(Form.class, FormTemplate.VALID);
		this.programmaticArea = this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        form.getProgrammaticArea());

		final List<Question> questions = EntityFactory.gimme(Question.class, 5, QuestionTemplate.VALID);

		for (final Question question : questions) {
			this.questionService.createQuestion(this.getUserContext(), question);
		}

		this.createdform = this.formService.createForm(this.getUserContext(), form, new HashSet<>(questions));
	}

	@Test
	public void shouldFetchByForm() throws BusinessException {

		final Form fetchedFrom = this.formQueryService.fetchByForm(this.getUserContext(), this.createdform);

		assertEquals(this.createdform.getCode(), fetchedFrom.getCode());
		assertFalse(fetchedFrom.getFromQuestions().isEmpty());

		fetchedFrom.getFromQuestions().stream().forEach(formQuestion -> {
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
}
