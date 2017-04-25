/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutored.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface TutoredQueryService {

	String NAME = "mz.org.fgh.mentoring.core.tutored.service.TutoredQueryService";

	List<Tutored> findTutoredsBySelectedFilter(final UserContext userContext, final String uuid, final String code,
			final String name, final String surname, String phoneNumber, final String tutored) throws BusinessException;

	Tutored findTutoredByUuid(final UserContext userContext, final String uuid) throws BusinessException;
}
