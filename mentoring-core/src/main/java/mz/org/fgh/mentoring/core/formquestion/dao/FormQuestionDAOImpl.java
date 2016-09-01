/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.formquestion.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

/**
 * @author Eusebio Jose Maposse
 * @author Stélio Moiane
 *
 */
@Repository(FormQuestionDAO.NAME)
public class FormQuestionDAOImpl extends GenericDAOImpl<FormQuestion, Long> implements FormQuestionDAO {

	@Override
	public List<FormQuestion> findByFormId(Long formId) {
		return this.findByNamedQuery(FormQuestionDAO.QUERY_NAME.findByFormId,
				new ParamBuilder().add("formId", formId).process());
	}

	@Override
	public FormQuestion findByFormIdAndQuestionId(Long formId, Long questionId) {
		return this.findSingleByNamedQuery(FormQuestionDAO.QUERY_NAME.findByFormIdAndQuestionId,
				new ParamBuilder().add("formId", formId).add("questionId", questionId).process());
	}

}
