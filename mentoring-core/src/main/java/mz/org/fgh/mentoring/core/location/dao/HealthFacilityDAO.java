/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;

/**
 * @author Stélio Moiane
 *
 */
public interface HealthFacilityDAO extends GenericDAO<HealthFacility, Long> {

	String NAME = "mz.org.fgh.mentoring.core.location.dao.HealthFacilityDAO";

	public static class QUERY {
		public static final String findByDistrict = "HealthFacility hf";
	}

	public static class QUERY_NAME {
		public static final String findByDistrict = "HealthFacility.findByDistrict";
	}

	List<HealthFacility> findByDistrict(Long userContextId, Long districtId);
}
