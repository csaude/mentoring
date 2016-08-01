/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.formquestion.dao;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

/**
 * @author Eusebio Jose Maposse
 * @author Stélio Moiane
 *
 */
@Repository(FormQuestionDAO.NAME)
public class FormQuestionDAOImpl extends GenericDAOImpl<FormQuestion, Long> implements FormQuestionDAO {

}
