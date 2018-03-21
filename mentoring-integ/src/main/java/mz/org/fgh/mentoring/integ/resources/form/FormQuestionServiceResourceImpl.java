/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.form;

import com.sun.jersey.api.JResponse;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.formquestion.service.FormQuestionQueryService;
import mz.org.fgh.mentoring.core.formquestion.service.FormQuestionService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Stélio Moiane
 *
 */
@Service(FormQuestionServiceResource.NAME)
@Path("formquestions")
public class FormQuestionServiceResourceImpl extends AbstractResource implements FormQuestionServiceResource {

	@Inject private FormQuestionQueryService formQuestionQueryService;
	@Inject private FormQuestionService formQuestionService;

	@Override
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<FormQuestion>> fetchFormQuestions(@QueryParam("formId") Long formId) throws BusinessException {
		if(formId != null) {
			return JResponse.ok(formQuestionService.getFormQuestionByFormId(formId)).build();
		}

		final List<FormQuestion> formQuestions = this.formQuestionQueryService
		        .fetchFormQuestionsByTutor(this.getUserContetx());

		return JResponse.ok(formQuestions).build();
	}
}
