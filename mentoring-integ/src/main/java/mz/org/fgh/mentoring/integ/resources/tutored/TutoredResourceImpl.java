/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutored;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(TutoredResource.NAME)
@Path("tutoreds")
public class TutoredResourceImpl implements TutoredResource {

	@Inject
	private TutoredService tutoredService;

	@Override
	public JResponse<Tutored> createTutored(final TutoredBeanResource tutoredBeanResource) throws BusinessException {

		final Tutored tutored = this.tutoredService.createTutored(tutoredBeanResource.getUserContext(),
				tutoredBeanResource.getTutored());

		return JResponse.ok(tutored).build();
	}
}
