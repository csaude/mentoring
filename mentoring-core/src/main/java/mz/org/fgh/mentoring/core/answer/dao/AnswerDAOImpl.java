/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.answer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.answer.model.Answer;

/**
 * @author Stélio Moiane
 *
 */
@Repository(AnswerDAO.NAME)
public class AnswerDAOImpl extends GenericDAOImpl<Answer, Long> implements AnswerDAO {

	@Override
	public List<Answer> fetchByMentorishipUuid(final String mentorshipUuid, final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(AnswerDAO.QUERY_NAME.fetchByMentorishipUuid, new ParamBuilder()
		        .add("mentorshipUuid", mentorshipUuid).add("lifeCycleStatus", lifeCycleStatus).process());
	}
}
