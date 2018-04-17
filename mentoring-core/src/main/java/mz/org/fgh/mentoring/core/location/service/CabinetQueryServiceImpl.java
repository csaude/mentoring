/**
 *
 */
package mz.org.fgh.mentoring.core.location.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.location.dao.CabinetDAO;
import mz.org.fgh.mentoring.core.location.model.Cabinet;

/**
 * @author Stélio Moiane
 *
 */

@Service(CabinetQueryServiceImpl.NAME)
public class CabinetQueryServiceImpl implements CabinetQueryService {

	public static final String NAME = "mz.org.fgh.mentoring.core.location.service.CabinetQueryServiceImpl";

	@Inject
	private CabinetDAO cabinetDAO;

	@Override
	public Cabinet findCabinetByName(final String cabinetName) throws BusinessException {
		return this.cabinetDAO.findByName(cabinetName, LifeCycleStatus.ACTIVE);
	}

	@Override
	public List<Cabinet> findAllCabinets() throws BusinessException {
		return this.cabinetDAO.findAll(LifeCycleStatus.ACTIVE);
	}

	@Override
	public Cabinet findCabinetByUuid(final String cabinetUuid) throws BusinessException {
		return this.cabinetDAO.findByUuid(cabinetUuid);
	}
}
