/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.exception.ErrorMessage;

/**
 * @author Stélio Moiane
 *
 */
@Provider
public class MentoringExceptionHandler implements ExceptionMapper<BusinessException> {

	@Override
	public Response toResponse(final BusinessException businessException) {
		return Response.ok(new ErrorMessage(businessException.getMessage())).build();
	}
}