/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutored;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;
import mz.org.fgh.mentoring.core.tutored.service.TutoredQueryService;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(TutoredResource.NAME)
@Path("tutoreds")
public class TutoredResourceImpl extends AbstractResource implements TutoredResource {

	@Inject
	private TutoredService tutoredService;

	@Inject
	private TutoredQueryService tutoredQueryService;

	@Override
	public JResponse<Tutored> createTutored(final TutoredBeanResource tutoredBeanResource) throws BusinessException {

		final Tutored tutored = this.tutoredService.createTutored(tutoredBeanResource.getUserContext(),
				tutoredBeanResource.getTutored());

		return JResponse.ok(tutored).build();
	}

	@Override
	public JResponse<List<Tutored>> findTutoreds(final String code, final String name, final String surname,
			final String phoneNumber, final String tutored) throws BusinessException {
		final List<Tutored> tutoreds = this.tutoredQueryService.findTutoredsBySelectedFilter(this.getUserContetx(),
				code, name, surname, phoneNumber, tutored);

		return JResponse.ok(tutoreds).build();
	}

	@Override
	public JResponse<Tutored> updateTutored(final TutoredBeanResource tutoredBeanResource) throws BusinessException {
		final Tutored tutored = this.tutoredService.updateTutored(tutoredBeanResource.getUserContext(),
				tutoredBeanResource.getTutored());

		return JResponse.ok(tutored).build();
	}

	@Override
	public JResponse<TutoredBeanResource> syncronizeTutoreds(final TutoredBeanResource tutoredBeanResource)
			throws BusinessException {

		this.tutoredService.syncronizeTutoreds(tutoredBeanResource.getUserContext(), tutoredBeanResource.getTutoreds());

		return JResponse.ok(tutoredBeanResource).build();
	}
}
