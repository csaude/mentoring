/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.form;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

/**
 * @author Stélio Moiane
 *
 */
public interface FormQuestionServiceResource {

	String NAME = "mz.org.fgh.mentoring.integ.resources.form.FormQuestionServiceResource";

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	JResponse<List<FormQuestion>> fetchAllFormQuestion() throws BusinessException;
}
