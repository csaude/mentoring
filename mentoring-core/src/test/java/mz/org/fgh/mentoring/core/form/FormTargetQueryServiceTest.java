/**
 *
 */
package mz.org.fgh.mentoring.core.form;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormQuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.FormTargetTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.ProgrammaticAreaTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.TutorTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.model.FormTarget;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.form.service.FormTargetQueryService;
import mz.org.fgh.mentoring.core.form.service.FormTargetService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;

/**
 * @author Stélio Moiane
 *
 */
public class FormTargetQueryServiceTest extends AbstractSpringTest {

	@Inject
	private CareerService careerService;

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private QuestionService questionService;

	@Inject
	private FormService formService;

	@Inject
	private FormTargetService formTargetService;

	@Inject
	private TutorService tutorService;

	@Inject
	private FormTargetQueryService formTargetQueryService;

	private Tutor tutor;

	@Override
	public void setUp() throws BusinessException {

		final FormTarget formTarget = EntityFactory.gimme(FormTarget.class, FormTargetTemplate.VALID);

		this.careerService.createCareer(this.getUserContext(), formTarget.getCareer());

		final ProgrammaticArea programmaticArea = EntityFactory.gimme(ProgrammaticArea.class,
		        ProgrammaticAreaTemplate.VALID);

		final Form form = formTarget.getForm();

		form.setProgrammaticArea(
		        this.programmaticAreaService.createProgrammaticArea(this.getUserContext(), programmaticArea));

		final List<FormQuestion> formQuestions = (EntityFactory.gimme(FormQuestion.class, 10,
		        FormQuestionTemplate.WITH_NO_FORM));

		formQuestions.forEach(formQuestion -> {
			try {
				this.questionService.createQuestion(this.getUserContext(), formQuestion.getQuestion());
			}
			catch (final BusinessException e) {
				e.printStackTrace();
			}
		});

		this.formService.createForm(this.getUserContext(), form, new HashSet<>(formQuestions));
		this.formTargetService.createFormTarget(this.getUserContext(), formTarget);

		this.tutor = EntityFactory.gimme(Tutor.class, TutorTemplate.VALID);
		this.tutor.setEmail("stelio.moiane@fgh.org.mz");
		this.tutor.setCareer(formTarget.getCareer());

		this.tutorService.createTutor(this.getUserContext(), this.tutor);

	}

	@Test
	public void shouldFindFormTargetByTutor() throws BusinessException {
		final List<FormTarget> formTargets = this.formTargetQueryService.findFormTargetByTutor(this.tutor);
		assertFalse(formTargets.isEmpty());

		for (final FormTarget formTarget : formTargets) {
			assertNotNull(formTarget.getCareer().getUuid());
			assertNotNull(formTarget.getForm().getUuid());
		}
	}
}
