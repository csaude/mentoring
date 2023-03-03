/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.partner.service;

import java.util.List;

import org.springframework.scripting.bsh.BshScriptUtils.BshExecutionException;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.model.CareerType;
import mz.org.fgh.mentoring.core.partner.model.Partner;

/**
 * @author Stélio Moiane
 *
 */
public interface PartnerQueryService {
	String NAME = "mz.org.fgh.mentoring.core.career.service.PartnerQueryService";

	List<Partner> findAllPartners(final UserContext userContext) throws BshExecutionException;

}
