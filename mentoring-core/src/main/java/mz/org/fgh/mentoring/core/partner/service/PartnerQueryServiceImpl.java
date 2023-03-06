/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.partner.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.scripting.bsh.BshScriptUtils.BshExecutionException;
import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.career.dao.CareerDAO;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.model.CareerType;
import mz.org.fgh.mentoring.core.partner.dao.PartnerDAO;
import mz.org.fgh.mentoring.core.partner.model.Partner;

/**
 * @author Stélio Moiane
 *
 */
@Service(PartnerQueryService.NAME)
public class PartnerQueryServiceImpl implements PartnerQueryService {

	@Inject
	private PartnerDAO partnerDAO;

	@Override
	public List<Partner> findAllPartners(final UserContext userContext) throws BshExecutionException {
		return this.partnerDAO.findAll(LifeCycleStatus.ACTIVE);
	}

	
}
