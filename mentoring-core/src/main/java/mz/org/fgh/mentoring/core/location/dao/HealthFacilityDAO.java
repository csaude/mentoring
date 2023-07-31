/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.location.model.Province;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
public interface HealthFacilityDAO extends GenericDAO<HealthFacility, Long> {

	String NAME = "mz.org.fgh.mentoring.core.location.dao.HealthFacilityDAO";

	public static class QUERY {
		public static final String fetchByTutor = 	"SELECT hf " +
				"FROM HealthFacility hf inner join fetch hf.district d " +
				"						INNER JOIN FETCH hf.tutorLocations tl " +
				"						INNER JOIN tl.tutor t " +
				"						INNER JOIN t.partner fp " +
				"WHERE hf.lifeCycleStatus = :lifeCycleStatus " +
				"		AND t.uuid = :tutorUUID ";
		public static final String findByDistrict = "SELECT hf FROM HealthFacility hf inner join fetch hf.district d WHERE d.id = :districtId AND hf.lifeCycleStatus = :lifeCycleStatus";

		public static final String findByProvince = "SELECT hf FROM HealthFacility hf inner join fetch hf.district d WHERE d.province = :province AND hf.lifeCycleStatus = :lifeCycleStatus";

		public static final String fetchAll = "SELECT hf FROM HealthFacility hf INNER JOIN FETCH hf.district d WHERE hf.lifeCycleStatus = :lifeCycleStatus";
		public static final String findByDistrictAndName = "SELECT hf FROM HealthFacility hf INNER JOIN hf.district d WHERE d.id = :districtId AND hf.healthFacility = :healthFacility AND hf.lifeCycleStatus = :lifeCycleStatus";
	}

	public static class QUERY_NAME {
		public static final String findByDistrict = "HealthFacility.findByDistrict";

		public static final String fetchByTutor = "HealthFacility.fetchByTutor";
		public static final String fetchAll = "HealthFacility.findAll";
		public static final String findByDistrictAndName = "HealthFacility.findByDistrictAndName";

		public static final String findByProvince = "HealthFacility.findByProvince";

	}

	List<HealthFacility> findByDistrict(final Long districtId, final LifeCycleStatus lifeCycleStatus);

	List<HealthFacility> fetchdAll(final LifeCycleStatus lifeCycleStatus);

	List<HealthFacility> fetchByTutor(final Tutor tutor, final LifeCycleStatus lifeCycleStatus);


	HealthFacility findByDistrictAndName(final District district, final String healthFacility,
										 final LifeCycleStatus lifeCycleStatus);

	List<HealthFacility> findByProvince(final Province province, final LifeCycleStatus lifeCycleStatus);


}
