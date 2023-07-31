/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.dao;

import java.util.List;

import javax.persistence.NoResultException;

import mz.org.fgh.mentoring.core.location.model.Province;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;

/**
 * @author Stélio Moiane
 *
 */
@Repository(HealthFacilityDAO.NAME)
public class HealthFacilityDAOImpl extends GenericDAOImpl<HealthFacility, Long> implements HealthFacilityDAO {

	@Override
	public List<HealthFacility> findByDistrict(final Long districtId, final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(HealthFacilityDAO.QUERY_NAME.findByDistrict,
				new ParamBuilder().add("districtId", districtId).add("lifeCycleStatus", lifeCycleStatus).process());
	}

	@Override
	public List<HealthFacility> fetchdAll(final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(HealthFacilityDAO.QUERY_NAME.fetchAll,
				new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus).process());
	}

	@Override
	public List<HealthFacility> fetchByTutor(Tutor tutor, LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(QUERY_NAME.fetchByTutor, new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus).add("tutorUUID", tutor.getUuid()).process());
	}

	@Override
	public HealthFacility findByDistrictAndName(final District district, final String healthFacility,
												final LifeCycleStatus lifeCycleStatus) {
		try {
			return this.findSingleByNamedQuery(HealthFacilityDAO.QUERY_NAME.findByDistrictAndName, new ParamBuilder().add("districtId", district.getId()).add("healthFacility", healthFacility) .add("lifeCycleStatus", lifeCycleStatus).process());
		}
		catch (final NoResultException e) {
			return null;
		}
	}

	@Override
	public List<HealthFacility> findByProvince(Province province, LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(HealthFacilityDAO.QUERY_NAME.findByProvince,
				new ParamBuilder().add("province", province).add("lifeCycleStatus", lifeCycleStatus).process());
	}
}
