/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.answer.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.answer.model.Answer;

/**
 * @author Stélio Moiane
 *
 */
public interface AnswerDAO extends GenericDAO<Answer, Long> {

	String NAME = "mz.org.fgh.mentoring.core.answer.dao.AnswerDAO";
}
