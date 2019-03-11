/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.util;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.org.fgh.mentoring.core.career.model.CareerType;
import mz.org.fgh.mentoring.core.form.model.FormType;
import mz.org.fgh.mentoring.core.location.model.Province;
import mz.org.fgh.mentoring.core.question.model.QuestionCategory;
import mz.org.fgh.mentoring.core.question.model.QuestionType;

/**
 * @author Stélio Moiane
 *
 */
public interface ResourceUtils {

	String NAME = "mz.org.fgh.mentoring.integ.resources.util.ResourceUtils";

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/provinces")
	public JResponse<List<Province>> getProvinces();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/questionscategories")
	public JResponse<List<QuestionCategory>> getQuestionCategory();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/careertypes")
	public JResponse<List<CareerType>> getCarrerTypes();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/questiontypes")
	public JResponse<List<QuestionType>> getQuestionTypes();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/formtypes")
	public JResponse<List<FormType>> getFormTypes();
}
