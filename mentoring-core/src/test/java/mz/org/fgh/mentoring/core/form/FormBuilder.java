/**
 *
 */
package mz.org.fgh.mentoring.core.form;

import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormQuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.FormTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.ProgrammaticAreaTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.partner.service.PartnerService;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.service.QuestionCategoryService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;

/**
 * @author Stélio Moiane
 *
 */
@Ignore
@Service(FormBuilder.NAME)
public class FormBuilder extends AbstractSpringTest {

	public static final String NAME = "mz.org.fgh.mentoring.core.form.FormBuilder";

	@Inject
	private FormService formService;

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private QuestionService questionService;

	@Inject
	private QuestionCategoryService questionCategoryService;

	@Inject
	private PartnerService partnerService;

	private Form form;

	@Override
	public void setUp() throws BusinessException {
	}

	public Form build() throws BusinessException {

		this.getForm();

		this.form.setProgrammaticArea(
				this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
						EntityFactory.gimme(ProgrammaticArea.class, ProgrammaticAreaTemplate.VALID)));

		final List<FormQuestion> formQuestions = EntityFactory.gimme(FormQuestion.class, 10, FormQuestionTemplate.WITH_NO_FORM);

		for (final FormQuestion formQuestion : formQuestions) {

			this.questionCategoryService.createQuestionCategory(this.getUserContext(),
					formQuestion.getQuestion().getQuestionsCategory());

			this.questionService.createQuestion(this.getUserContext(), formQuestion.getQuestion());

			this.form.addFormQuestion(formQuestion);
		}

		this.partnerService.createPartner(this.getUserContext(), this.form.getPartner());

		this.formService.createForm(this.getUserContext(), this.form, new HashSet<>(formQuestions));

		return this.form;
	}

	public FormBuilder withName(final String formName) {
		this.getForm();
		this.form.setName(formName);
		return this;
	}

	private void getForm() {
		if (this.form == null || this.form.getId() != null) {
			this.form = EntityFactory.gimme(Form.class, FormTemplate.VALID);
		}
	}
}
