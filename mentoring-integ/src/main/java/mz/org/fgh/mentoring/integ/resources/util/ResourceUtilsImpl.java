/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.util;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Path;

import com.sun.jersey.api.JResponse;

import mz.org.fgh.mentoring.core.career.model.CareerType;
import mz.org.fgh.mentoring.core.form.model.FormType;
import mz.org.fgh.mentoring.core.location.model.Province;
import mz.org.fgh.mentoring.core.mentorship.model.Month;
import mz.org.fgh.mentoring.core.question.model.QuestionType;
import mz.org.fgh.mentoring.core.util.QuestionCategory;

@Path("utils")
public class ResourceUtilsImpl implements ResourceUtils {

	@Override
	public JResponse<List<Province>> getProvinces() {
		return JResponse.ok(Arrays.asList(Province.values())).build();
	}

	@Override
	public JResponse<List<QuestionCategory>> getQuestionCategory() {
		return JResponse.ok(Arrays.asList(QuestionCategory.values())).build();
	}

	@Override
	public JResponse<List<CareerType>> getCarrerTypes() {
		return JResponse.ok(Arrays.asList(CareerType.values())).build();
	}

	@Override
	public JResponse<List<QuestionType>> getQuestionTypes() {
		return JResponse.ok(Arrays.asList(QuestionType.values())).build();
	}

	@Override
	public JResponse<List<Month>> getMonths() {
		return JResponse.ok(Arrays.asList(Month.values())).build();
	}

	@Override
	public JResponse<List<FormType>> getFormTypes() {
		return JResponse.ok(Arrays.asList(FormType.values())).build();
	}
}
