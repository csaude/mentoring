/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.carrer.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.carrer.model.Carrer;
import mz.org.fgh.mentoring.core.carrer.model.CarrerType;

/**
 * @author Stélio Moiane
 *
 */
public interface CarrerDAO extends GenericDAO<Carrer, Long> {

	final String NAME = "mz.org.fgh.mentoring.core.carrer.dao.CarrerDAO";

	public static class QUERY {
		public static final String findByCarrerType = "SELECT c FROM Carrer c WHERE c.carrerType = :carrerType AND c.lifeCycleStatus = :lifeCycleStatus";
	}

	public static class QUERY_NAME {
		public static final String findByCarrerType = "Carrer.findByCarrerType";
	}

	List<Carrer> findByCarrerType(final CarrerType carrerType, final LifeCycleStatus lifeCycleStatus);
}
