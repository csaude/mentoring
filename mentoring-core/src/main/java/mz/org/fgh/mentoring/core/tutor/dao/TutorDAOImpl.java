/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutor.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<Tutor> findByNameOrSurnameOrCategory(Tutor param) {

		Map<String, String> map = new HashMap<String, String>();

		map.put("NAME", param.getName());
		map.put("SURNAME", param.getSurname());
		map.put("CAGTGORY", param.getCategory().toString());

		Query query = getEntityManager().createQuery(
				"select t from Tutor t where t.name = ? or t.surname = ? or t.category = ?");

		query.setParameter(1, param);
		return query.getResultList();
	}

}
