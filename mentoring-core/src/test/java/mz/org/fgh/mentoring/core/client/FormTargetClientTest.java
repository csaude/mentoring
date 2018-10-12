/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.files.FileReaderService;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.util.GenericObject;
import mz.org.fgh.mentoring.core.career.service.CareerQueryService;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormQuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.FormTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.ProgrammaticAreaTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormQueryService;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.form.service.FormTargetService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;

/**
 * @author Stélio Moiane
 *
 */
public class FormTargetClientTest extends AbstractSpringTest {

	@Inject
	private FileReaderService fileReaderService;

	@Inject
	private CareerQueryService careerQueryService;

	@Inject
	private FormQueryService formQueryService;

	@Inject
	private CareerService careerService;

	@Inject
	private FormService formService;

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private QuestionService questionService;

	@Inject
	private FormTargetService formTargetService;

	private FormTargetClient client;

	@Override
	public void setUp() throws BusinessException {

		this.client = new FormTargetClient();
		this.client.setFileReaderService(this.fileReaderService);
		this.client.setCareerQueryService(this.careerQueryService);
		this.client.setFormQueryService(this.formQueryService);
		this.client.setFormTargetService(this.formTargetService);

		final CarrerClient carrerClient = new CarrerClient();
		carrerClient.setFileReaderService(this.fileReaderService);
		carrerClient.setCareerQueryService(this.careerQueryService);
		carrerClient.setCareerService(this.careerService);
		carrerClient.process(carrerClient);

		final ProgrammaticArea programmaticArea = EntityFactory.gimme(ProgrammaticArea.class,
		        ProgrammaticAreaTemplate.VALID);

		final Form form = EntityFactory.gimme(Form.class, FormTemplate.VALID);
		form.setName("TABELA DE VERIFICACAO DAS COMPETENCIAS EM ATIP");

		form.setProgrammaticArea(
		        this.programmaticAreaService.createProgrammaticArea(this.getUserContext(), programmaticArea));

		final List<FormQuestion> formQuestions = EntityFactory.gimme(FormQuestion.class, 10,
		        FormQuestionTemplate.WITH_NO_FORM);

		formQuestions.forEach(question -> {
			this.createQuestion(question);
		});

		this.formService.createForm(this.getUserContext(), form, new HashSet<>(formQuestions));
	}

	private void createQuestion(final FormQuestion formQuestion) {
		try {
			this.questionService.createQuestion(this.getUserContext(), formQuestion.getQuestion());
		}
		catch (final BusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldAddFormTargets() throws BusinessException {

		final int records = this.client.process(this.client);
		final List<GenericObject> careers = this.fileReaderService.readfile("form-targets.xlsx");
		assertEquals(careers.size(), records);
	}
}
