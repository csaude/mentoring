/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.question.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.question.model.Question;

import org.springframework.stereotype.Repository;

/**
 * @author Stélio Moiane
 *
 */
@Repository(QuestionDAO.NAME)
public class QuestionDAOImpl extends GenericDAOImpl<Question, Long> implements QuestionDAO {

}
