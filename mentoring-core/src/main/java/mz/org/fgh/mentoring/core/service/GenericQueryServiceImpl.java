/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 *
 */
public class GenericQueryServiceImpl<T> implements GenericQueryService<T> {

	@Override
	public T findByuuid(final UserContext userContext, final String uuid) throws BusinessException {
		return null;
	}
}
