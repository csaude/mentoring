/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import mz.co.mozview.frameworks.core.exception.BusinessException;

/**
 * @author Stélio Moiane
 *
 *         This is the Exception Default Handler class for Jersey
 *
 */
@Provider
public class MentoringExceptionHandler implements ExceptionMapper<BusinessException> {

	@Override
	public Response toResponse(final BusinessException businessException) {
		return Response.status(Status.BAD_REQUEST).entity(new ErrorMessage(businessException.getMessage())).build();
	}
}
