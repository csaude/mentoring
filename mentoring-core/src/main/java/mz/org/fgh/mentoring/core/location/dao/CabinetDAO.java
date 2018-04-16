/**
 *
 */
package mz.org.fgh.mentoring.core.location.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.location.model.Cabinet;

/**
 * @author Stélio Moiane
 *
 */
public interface CabinetDAO extends GenericDAO<Cabinet, Long> {

	class QUERY {
		public static final String findByName = "SELECT c FROM Cabinet c WHERE c.name = :cabinetName AND c.lifeCycleStatus = :lifeCycleStatus";
		public static final String findAll = "SELECT c FROM Cabinet c WHERE c.lifeCycleStatus = :lifeCycleStatus";
	}

	class QUERY_NAME {
		public static final String findByName = "Cabinet.findByName";
		public static final String findAll = "Cabinet.findAll";
	}

	Cabinet findByName(String cabinetName, LifeCycleStatus lifeCycleStatus);

	List<Cabinet> findAll(LifeCycleStatus lifeCycleStatus);
}
