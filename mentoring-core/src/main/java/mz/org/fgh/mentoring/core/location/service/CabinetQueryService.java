/**
 *
 */
package mz.org.fgh.mentoring.core.location.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.location.model.Cabinet;

/**
 * @author Stélio Moiane
 *
 */
public interface CabinetQueryService {

	Cabinet findCabinetByName(final String cabinetName) throws BusinessException;

	List<Cabinet> findAllCabinets() throws BusinessException;

	Cabinet findCabinetByUuid(final String cabbinetUuid) throws BusinessException;
}
