/**
 *
 */
package mz.org.fgh.mentoring.core.location.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.location.model.Cabinet;

/**
 * @author Stélio Moiane
 *
 */
public interface CabinetService {

	Cabinet createCabinet(final UserContext userContext, final Cabinet cabinet) throws BusinessException;
}
