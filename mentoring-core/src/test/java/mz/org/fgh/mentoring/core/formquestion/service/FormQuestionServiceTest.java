package mz.org.fgh.mentoring.core.formquestion.service;


import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.ProgrammaticAreaTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Willa aka Baba Imu on 3/9/18.
 */
public class FormQuestionServiceTest extends AbstractSpringTest {
    @Inject private ProgrammaticAreaService programmaticAreaService;
    @Inject private FormQuestionService formQuestionService;
    @Inject private QuestionService questionService;
    @Inject private FormService formService;

    private Set<Question> questions = new HashSet<>();
    private Form form;

    @Override
    public void setUp() throws BusinessException {
        ProgrammaticArea programmaticArea = EntityFactory.gimme(ProgrammaticArea.class, ProgrammaticAreaTemplate.VALID);
        form = EntityFactory.gimme(Form.class, FormTemplate.VALID);
        form.setProgrammaticArea(programmaticAreaService.createProgrammaticArea(getUserContext(), programmaticArea));

        List<Question> createdQuestions = (EntityFactory.gimme(Question.class, 10, QuestionTemplate.VALID));
        for (Question question : createdQuestions) {
            questionService.createQuestion(this.getUserContext(), question);
            questions.add(question);
        }
        formService.createForm(getUserContext(), form, new HashSet<Question>(createdQuestions));
    }

    @Test
    public void getFormQuestionByFormId_shouldReturnAListOfFormQuestionGivenFormId() throws BusinessException {
        List<FormQuestion> formQuestions = formQuestionService.getFormQuestionByFormId(form.getId());

        assertFalse(formQuestions.isEmpty());
        assertEquals(questions.size(), formQuestions.size());
    }
}
