/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutor.dao;

import java.util.List;

import javax.persistence.Query;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

import org.springframework.stereotype.Repository;

/**
 * @author Stélio Moiane
 *
 */
@Repository(TutorDAO.NAME)
public class TutorDAOImpl extends GenericDAOImpl<Tutor, Long> implements TutorDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Tutor> findAll() {
		Query query = getEntityManager()
				.createQuery("select t from Tutor t");

		return query.getResultList();
	}

}
