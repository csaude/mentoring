/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutor.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */

public interface TutorDAO extends GenericDAO<Tutor, Long> {

	String NAME = "mz.org.fgh.mentoring.core.tutor.dao.TutorDAO";

	
	public List<Tutor> findAll();

}
