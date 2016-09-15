/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.carrer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.carrer.model.Carrer;
import mz.org.fgh.mentoring.core.carrer.model.CarrerType;

/**
 * @author Stélio Moiane
 *
 */
@Repository(CarrerDAO.NAME)
public class CarrerDAOImpl extends GenericDAOImpl<Carrer, Long> implements CarrerDAO {

	@Override
	public List<Carrer> findByCarrerType(final CarrerType carrerType, final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(CarrerDAO.QUERY_NAME.findByCarrerType,
				new ParamBuilder().add("carrerType", carrerType).add("lifeCycleStatus", lifeCycleStatus).process());
	}

	@Override
	public Carrer findByCarrerId(Long carrerId, LifeCycleStatus lifeCycleStatus) {
		
		return this.findSingleByNamedQuery(CarrerDAO.QUERY_NAME.findByCarrerId,
				new ParamBuilder().add("carrerId", carrerId).add("lifeCycleStatus", lifeCycleStatus).process());
	}

}
