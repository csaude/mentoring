/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.question.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.form.question.model.FormQuestion;

import org.springframework.stereotype.Repository;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(FormQuestionDAO.NAME)
public class FormQuestionDAOImpl extends GenericDAOImpl<FormQuestion, Long> implements FormQuestionDAO {

}
