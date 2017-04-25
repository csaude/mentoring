/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.career.service;

import java.util.List;

import org.springframework.scripting.bsh.BshScriptUtils.BshExecutionException;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.model.CareerType;

/**
 * @author Stélio Moiane
 *
 */
public interface CareerQueryService {
	String NAME = "mz.org.fgh.mentoring.core.career.service.CareerQueryService";

	List<Career> findCareersByCareerType(final UserContext userContext, final CareerType careerType)
			throws BusinessException;

	Career findByCareerId(final UserContext userContext, final Long careerId) throws BusinessException;

	List<Career> findAllCareers(final UserContext userContext) throws BshExecutionException;

	Career findCarrerByuuid(final UserContext userContext, final String uuid) throws BusinessException;
}
