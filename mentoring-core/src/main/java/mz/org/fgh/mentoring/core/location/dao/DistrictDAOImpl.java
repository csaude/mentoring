/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.Province;

/**
 * @author Stélio Moiane
 *
 */
@Repository(DistrictDAO.NAME)
public class DistrictDAOImpl extends GenericDAOImpl<District, Long> implements DistrictDAO {

	@Override
	public List<District> findByProvince(final Province province, final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(DistrictDAO.QUERY_NAME.findByProvince,
				new ParamBuilder().add("province", province).add("lifeCycleStatus", lifeCycleStatus).process());
	}

}