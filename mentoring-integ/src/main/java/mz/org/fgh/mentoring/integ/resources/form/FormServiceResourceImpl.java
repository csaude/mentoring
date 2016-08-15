/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.form;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormQueryService;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
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

		final Form form = this.formService.createForm(formBeanResource.getUserContext(), formBeanResource.getForm(),
				formBeanResource.getQuestions());

		return JResponse.ok(form).build();
	}

	@Override
	public JResponse<List<Form>> findBySelectedFilter(String code, String name, ProgrammaticArea programmaticArea)
			throws BusinessException {
		final List<Form> forms = this.formQueryService.findBySelectedFilter(this.getUserContetx(), code, name,
				programmaticArea);

		return JResponse.ok(forms).build();
	}
}
