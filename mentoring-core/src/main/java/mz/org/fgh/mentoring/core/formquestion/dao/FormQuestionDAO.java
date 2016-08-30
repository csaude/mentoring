/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.formquestion.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface FormQuestionDAO extends GenericDAO<FormQuestion, Long> {

	public String NAME = "mz.org.fgh.mentoring.core.formquestion.dao.FormQuestionDAO";
	
	public static class QUERY {
		public static final String findByFormId = "SELECT fq FROM FormQuestion fq WHERE fq.form.id = :formId";
		public static final String findByFormIdAndQuestionId = "SELECT fq FROM FormQuestion fq WHERE fq.form.id = :formId AND fq.question.id =:questionId";

	}

	public static class QUERY_NAME {
		public static final String findByFormId = "FormQuestion.findByFormId";
		public static final String findByFormIdAndQuestionId = "FormQuestion.findByFormIdAndQuestionId";

	}
	
	public List<FormQuestion> findByFormId(Long formId);
	public List<FormQuestion> findByFormIdAndQuestionId(Long formId, Long questionId);


}
