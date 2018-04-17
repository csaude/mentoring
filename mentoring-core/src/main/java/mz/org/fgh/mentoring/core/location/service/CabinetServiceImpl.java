/**
 *
 */
package mz.org.fgh.mentoring.core.location.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.location.dao.CabinetDAO;
import mz.org.fgh.mentoring.core.location.model.Cabinet;

/**
 * @author Stélio Moiane
 *
 */
@Service(CabinetServiceImpl.NAME)
public class CabinetServiceImpl extends AbstractService implements CabinetService {

	public static final String NAME = "mz.org.fgh.mentoring.core.location.service.CabinetServiceImpl";

	@Inject
	private CabinetDAO cabinetDAO;

	@Override
	public Cabinet createCabinet(final UserContext userContext, final Cabinet cabinet) throws BusinessException {
		return this.cabinetDAO.create(userContext.getUuid(), cabinet);
	}
}
