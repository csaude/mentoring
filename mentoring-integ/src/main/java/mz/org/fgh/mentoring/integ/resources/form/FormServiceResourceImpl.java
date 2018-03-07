/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Path;

import mz.org.fgh.mentoring.core.question.model.Question;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormQueryService;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Stélio Moiane
 *
 */
@Service(FormServiceResource.NAME)
@Path("forms")
public class FormServiceResourceImpl extends AbstractResource implements FormServiceResource {

	@Inject
	private FormService formService;

	@Inject
	private FormQueryService formQueryService;

	@Override
	public JResponse<Form> createForm(final FormBeanResource formBeanResource) throws BusinessException {
		Map<Integer, Question> sequenceQuestionMap = createSequenceQuestionMap(formBeanResource.getQuestionSequences());

		if(!sequenceQuestionMap.isEmpty()) {
			final Form form = this.formService.createForm(formBeanResource.getUserContext(), formBeanResource.getForm(),
					sequenceQuestionMap);

			return JResponse.ok(form).build();
		}

		// Do it with questions only
		final Form form = this.formService.createForm(formBeanResource.getUserContext(), formBeanResource.getForm(),
		        formBeanResource.getQuestions());

		return JResponse.ok(form).build();
	}

	@Override
	public JResponse<Form> updateForm(final FormBeanResource formBeanResource) throws BusinessException {
		// Create a Map.
		Map<Integer, Question> sequenceQuestionMap = createSequenceQuestionMap(formBeanResource.getQuestionSequences());

		if(!sequenceQuestionMap.isEmpty()) {
			final Form form = this.formService.updateForm(formBeanResource.getUserContext(), formBeanResource.getForm(),
					sequenceQuestionMap);

			return JResponse.ok(form).build();
		}

		// else do it with questions.
		final Form form = this.formService.updateForm(formBeanResource.getUserContext(), formBeanResource.getForm(),
				formBeanResource.getQuestions());

		return JResponse.ok(form).build();
	}

	@Override
	public JResponse<List<Form>> findBySelectedFilter(final String code, final String name,
	        final String programmaticAreaCode) throws BusinessException {

		final List<Form> forms = this.formQueryService.findBySelectedFilter(this.getUserContetx(), code, name,
		        programmaticAreaCode);

		return JResponse.ok(forms).build();
	}

	@Override
	public JResponse<List<Form>> findSampleIndicatorForms() throws BusinessException {

		final List<Form> forms = this.formQueryService.findSampleIndicatorForms();

		return JResponse.ok(forms).build();
	}

	private Map<Integer, Question> createSequenceQuestionMap(Set<QuestionSequence> questionSequences) {
		Map<Integer, Question> sequenceQuestionMap = new HashMap<>();

		for(QuestionSequence qs: questionSequences) {
			sequenceQuestionMap.put(qs.getSequence(), qs.getQuestion());
		}

		return sequenceQuestionMap;
	}
}
