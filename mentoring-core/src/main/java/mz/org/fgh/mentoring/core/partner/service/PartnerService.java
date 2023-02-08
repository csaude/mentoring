/**
 *
 */
package mz.org.fgh.mentoring.core.partner.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.partner.model.Partner;

/**
 * @author Stélio Moiane
 *
 */
public interface PartnerService {

	public static final String NAME = "mz.org.fgh.mentoring.core.partner.service.PartnerService";

	Partner createPartner(UserContext userContext, Partner partner) throws BusinessException;

	Partner updatePartner(UserContext userContext, Partner partner) throws BusinessException;
}
