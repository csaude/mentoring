/**
 *
 */
package mz.org.fgh.mentoring.core.form;

import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormTargetTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.ProgrammaticAreaTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.model.FormTarget;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.form.service.FormTargetService;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionService;

/**
 * @author Stélio Moiane
 *
 */
public class FormTargetServiceTest extends AbstractSpringTest {

	@Inject
	private FormTargetService formTargetService;

	@Inject
	private FormService formService;

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private QuestionService questionService;

	@Inject
	private CareerService careerService;

	private FormTarget formTarget;

	@Override
	public void setUp() throws BusinessException {

		this.formTarget = EntityFactory.gimme(FormTarget.class, FormTargetTemplate.VALID);

		this.careerService.createCareer(this.getUserContext(), this.formTarget.getCareer());

		final ProgrammaticArea programmaticArea = EntityFactory.gimme(ProgrammaticArea.class,
		        ProgrammaticAreaTemplate.VALID);

		final Form form = this.formTarget.getForm();

		form.setProgrammaticArea(
		        this.programmaticAreaService.createProgrammaticArea(this.getUserContext(), programmaticArea));

		final List<Question> questions = (EntityFactory.gimme(Question.class, 10, QuestionTemplate.VALID));

		questions.forEach(question -> {
			try {
				this.questionService.createQuestion(this.getUserContext(), question);
			}
			catch (final BusinessException e) {
				e.printStackTrace();
			}
		});

		this.formService.createForm(this.getUserContext(), form, new HashSet<>(questions));
	}

	@Test
	public void shouldCreateFormTarget() {

		this.formTargetService.createFormTarget(this.getUserContext(), this.formTarget);

		TestUtil.assertCreation(this.formTarget);
	}
}
