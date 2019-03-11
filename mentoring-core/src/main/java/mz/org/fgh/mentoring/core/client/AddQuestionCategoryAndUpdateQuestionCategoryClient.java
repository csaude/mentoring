/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.model.QuestionCategory;
import mz.org.fgh.mentoring.core.question.model.QuestionsCategory;
import mz.org.fgh.mentoring.core.question.service.QuestionCategoryService;
import mz.org.fgh.mentoring.core.question.service.QuestionQueryService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;

/**
 * @author Stélio Moiane
 *
 */
public class AddQuestionCategoryAndUpdateQuestionCategoryClient
        extends ClientConfig<AddQuestionCategoryAndUpdateQuestionCategoryClient> {

	private static Logger logger = Logger.getLogger(AddQuestionCategoryAndUpdateQuestionCategoryClient.class.getName());

	private QuestionCategoryService questionCategoryService;

	private QuestionQueryService questionQueryService;

	private QuestionService questionService;

	@Override
	public int process(final AddQuestionCategoryAndUpdateQuestionCategoryClient client) throws BusinessException {

		int count = 0;

		final List<QuestionCategory> questionCategories = new ArrayList<>(Arrays.asList(QuestionCategory.values()));

		logger.info(questionCategories.size() + " Question category (ies) will be addedd.....");

		for (final QuestionCategory questionCategory : questionCategories) {

			final QuestionsCategory questionsCategory = new QuestionsCategory();
			questionsCategory.setCategory(questionCategory.getLabel());
			this.questionCategoryService.createQuestionCategory(this.getUserContext(), questionsCategory);

			this.updateQuestion(questionCategory, questionsCategory);

			count++;
		}

		logger.info(questionCategories.size() + " Question category (ies) was successfully added...");

		return count;
	}

	private void updateQuestion(final QuestionCategory questionCategory, final QuestionsCategory questionsCategory)
	        throws BusinessException {

		final List<Question> questions = this.questionQueryService.findQuestionsBySelectedFilter(this.getUserContext(),
		        null, null, null, questionCategory);

		for (final Question question : questions) {
			question.setQuestionsCategory(questionsCategory);
			this.questionService.updateQuestion(this.getUserContext(), question);
		}
	}

	public void setQuestionCategoryService(final QuestionCategoryService questionCategoryService) {
		this.questionCategoryService = questionCategoryService;
	}

	public void setQuestionQueryService(final QuestionQueryService questionQueryService) {
		this.questionQueryService = questionQueryService;
	}

	public void setQuestionService(final QuestionService questionService) {
		this.questionService = questionService;
	}

	public static void main(final String[] args) throws BusinessException {

		logger.info("The Client is Starting to execute ........");

		final AddQuestionCategoryAndUpdateQuestionCategoryClient client = new AddQuestionCategoryAndUpdateQuestionCategoryClient();
		client.setup();

		client.setQuestionCategoryService(client.getBean(QuestionCategoryService.class));
		client.setQuestionQueryService(client.getBean(QuestionQueryService.class));
		client.setQuestionService(client.getBean(QuestionService.class));

		client.process(client);

		client.close();

		logger.info("The Client was executed with success ........");
	}
}
