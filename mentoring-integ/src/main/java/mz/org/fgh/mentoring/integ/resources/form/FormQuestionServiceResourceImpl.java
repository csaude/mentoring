/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.form;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.formquestion.service.FormQuestionQueryService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Stélio Moiane
 *
 */
@Service(FormQuestionServiceResource.NAME)
@Path("formquestions")
public class FormQuestionServiceResourceImpl extends AbstractResource implements FormQuestionServiceResource {

	@Inject
	private FormQuestionQueryService formQuestionQueryService;

	@Override
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<FormQuestion>> fetchFormQuestions(@QueryParam("formId") final Long formId)
	        throws BusinessException {

		if (formId != null) {
			final Form form = new Form();
			form.setId(formId);

			return JResponse.ok(this.formQuestionQueryService.findFormQuestionByForm(form)).build();
		}

		final List<FormQuestion> formQuestions = this.formQuestionQueryService.fetchFormQuestionsByTutor(this.getUserContetx());

		return JResponse.ok(formQuestions).build();
	}
}
