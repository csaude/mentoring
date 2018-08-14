/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.career.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.model.CareerType;

/**
 * @author Stélio Moiane
 *
 */
public interface CareerDAO extends GenericDAO<Career, Long> {

	final String NAME = "mz.org.fgh.mentoring.core.career.dao.CareerDAO";

	class QUERY {
		public static final String findByCarrerType = "SELECT c FROM Career c WHERE c.careerType = :careerType AND c.lifeCycleStatus = :lifeCycleStatus";
		public static final String findByCarrerId = "SELECT c FROM Career c WHERE c.id = :careerId AND c.lifeCycleStatus = :lifeCycleStatus";
		public static final String findAll = "SELECT c FROM Career c WHERE c.lifeCycleStatus = :lifeCycleStatus";
		public static final String findByTypeAndPosition = "SELECT c FROM Career c WHERE c.careerType = :careerType AND c.position = :position AND c.lifeCycleStatus = :lifeCycleStatus";

	}

	class QUERY_NAME {
		public static final String findByCarrerType = "Career.findByCareerType";
		public static final String findByCarrerId = "Career.findByCareerId";
		public static final String findAll = "Career.findAll";
		public static final String findByTypeAndPosition = "Career.findByTypeAndPosition";
	}

	List<Career> findByCarrerType(final CareerType careerType, final LifeCycleStatus lifeCycleStatus);

	Career findByCarrerId(final Long careerId, final LifeCycleStatus lifeCycleStatus);

	List<Career> findAll(final LifeCycleStatus lifeCycleStatus);

	Career findByTypeAndPosition(final CareerType careerType, final String position,
	        final LifeCycleStatus lifeCycleStatus) throws BusinessException;
}
