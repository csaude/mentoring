/**
 *
 */
package mz.org.fgh.mentoring.core.location.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.location.model.Cabinet;

/**
 * @author Stélio Moiane
 *
 */
@Repository(CabinetDAOImpl.NAME)
public class CabinetDAOImpl extends GenericDAOImpl<Cabinet, Long> implements CabinetDAO {

	public static final String NAME = "mz.org.fgh.mentoring.core.location.dao.CabinetDAOImpl";

	@Override
	public Cabinet findByName(final String cabinetName, final LifeCycleStatus lifeCycleStatus) {
		return this.findSingleByNamedQuery(CabinetDAO.QUERY_NAME.findByName,
		        new ParamBuilder().add("cabinetName", cabinetName).add("lifeCycleStatus", lifeCycleStatus).process());
	}

	@Override
	public List<Cabinet> findAll(final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(CabinetDAO.QUERY_NAME.findAll,
		        new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus).process());
	}

}
