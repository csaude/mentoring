/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.question;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.question.model.QuestionsCategory;
import mz.org.fgh.mentoring.core.question.service.QuestionCategoryQueryService;
import mz.org.fgh.mentoring.core.question.service.QuestionCategoryService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Stélio Moiane
 *
 */
@Service(QuestionCategoryResourceImpl.NAME)
@Path("question-categories")
public class QuestionCategoryResourceImpl extends AbstractResource implements QuestionCategoryResource {

	public static final String NAME = "mz.org.fgh.mentoring.integ.resources.question.QuestionCategoryResourceImpl";

	@Inject
	private QuestionCategoryService questionCategoryService;

	@Inject
	private QuestionCategoryQueryService questionCategoryQueryService;

	@Override
	public JResponse<QuestionsCategory> createQuestionCategory(
	        final QuestionCategoryBeanResource questionCategoryBeanResource) throws BusinessException {

		final QuestionsCategory questionCategory = this.questionCategoryService.createQuestionCategory(
		        questionCategoryBeanResource.getUserContext(), questionCategoryBeanResource.getQuestionsCategory());

		return JResponse.ok(questionCategory).build();
	}

	@Override
	public JResponse<List<QuestionsCategory>> findAllQuestionCategories() throws BusinessException {

		final List<QuestionsCategory> questionCategories = this.questionCategoryQueryService
		        .findAllQuestionCategories();

		return JResponse.ok(questionCategories).build();
	}
}
