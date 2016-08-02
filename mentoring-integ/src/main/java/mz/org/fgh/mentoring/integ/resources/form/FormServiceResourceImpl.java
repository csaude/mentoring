/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.form;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;

/**
 * @author Stélio Moiane
 *
 */
@Service(FormServiceResource.NAME)
@Path("forms")
public class FormServiceResourceImpl implements FormServiceResource {

	@Inject
	private FormService formService;

	@Override
	public JResponse<Form> createForm(final FormBeanResource formBeanResource) throws BusinessException {

		final Form form = this.formService.createForm(formBeanResource.getUserContext(), formBeanResource.getForm(),
				formBeanResource.getQuestions());

		return JResponse.ok(form).build();
	}
}
