/**
 *
 */
package mz.org.fgh.mentoring.core.partner.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.partner.dao.PartnerDAO;
import mz.org.fgh.mentoring.core.partner.model.Partner;

/**
 * @author Stélio Moiane
 *
 */
@Service(PartnerService.NAME)
public class PartnerServiceImpl extends AbstractService implements PartnerService {

	@Inject
	private PartnerDAO partnerDAO;

	@Override
	public Partner createPartner(final UserContext userContext, final Partner partner) throws BusinessException {
		this.partnerDAO.create(userContext.getUuid(), partner);
		return partner;
	}

	@Override
	public Partner updatePartner(final UserContext userContext, final Partner partner) throws BusinessException {
		this.partnerDAO.update(userContext.getUuid(), partner);
		return partner;
	}
}
