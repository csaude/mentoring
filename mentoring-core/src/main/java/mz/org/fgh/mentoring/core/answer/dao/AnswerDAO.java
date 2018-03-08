/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.answer.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.answer.model.Answer;

/**
 * @author Stélio Moiane
 *
 */
public interface AnswerDAO extends GenericDAO<Answer, Long> {

	String NAME = "mz.org.fgh.mentoring.core.answer.dao.AnswerDAO";

	class QUERY {
		public static final String fetchByMentorishipUuid = "SELECT a FROM Answer a INNER JOIN FETCH a.question q INNER JOIN a.mentorship m WHERE m.uuid = :mentorshipUuid AND a.lifeCycleStatus = :lifeCycleStatus ORDER BY q.questionCategory ASC";
	}

	class QUERY_NAME {
		public static final String fetchByMentorishipUuid = "Answer.fetchByMentorishipUuid";
	}

	List<Answer> fetchByMentorishipUuid(final String mentorshipUuid, final LifeCycleStatus lifeCycleStatus);
}
