/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;

/**
 * @author Stélio Moiane
 *
 */
public interface HealthFacilityDAO extends GenericDAO<HealthFacility, Long> {

	String NAME = "mz.org.fgh.mentoring.core.location.dao.HealthFacilityDAO";

	public static class QUERY {
		public static final String findByDistrict = "SELECT hf FROM HealthFacility hf  WHERE hf.district.id =:districtId AND hf.lifeCycleStatus = :lifeCycleStatus";
	}

	public static class QUERY_NAME {
		public static final String findByDistrict = "HealthFacility.findByDistrict";
	}

	List<HealthFacility> findByDistrict(Long userContextId, Long districtId, LifeCycleStatus lifeCycleStatus);
}
