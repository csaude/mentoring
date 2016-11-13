/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.career.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.model.CareerType;

/**
 * @author Stélio Moiane
 *
 */
@Repository(CareerDAO.NAME)
public class CarrerDAOImpl extends GenericDAOImpl<Career, Long> implements CareerDAO {

	@Override
	public List<Career> findByCarrerType(final CareerType carrerType, final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(CareerDAO.QUERY_NAME.findByCarrerType,
				new ParamBuilder().add("careerType", carrerType).add("lifeCycleStatus", lifeCycleStatus).process());
	}

	@Override
	public Career findByCarrerId(final Long carrerId, final LifeCycleStatus lifeCycleStatus) {

		return this.findSingleByNamedQuery(CareerDAO.QUERY_NAME.findByCarrerId,
				new ParamBuilder().add("carrerId", carrerId).add("lifeCycleStatus", lifeCycleStatus).process());
	}

	@Override
	public List<Career> findAll(final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(CareerDAO.QUERY_NAME.findAll,
				new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus).process());
	}
}
