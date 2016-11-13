/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.career;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.model.CareerType;
import mz.org.fgh.mentoring.core.career.service.CareerQueryService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Stélio Moiane
 *
 */
@Service(CareerResource.NAME)
@Path("careers")
public class CarrerResourceImpl extends AbstractResource implements CareerResource {

	@Inject
	private CareerQueryService careerQueryService;

	@Override
	public JResponse<List<Career>> getCareersByCareerType(final CareerType carrerType) throws BusinessException {

		final List<Career> carrers = this.careerQueryService.findCareersByCareerType(this.getUserContetx(), carrerType);

		return JResponse.ok(carrers).build();
	}

	@Override
	public JResponse<List<Career>> findAllCarrers() throws BusinessException {

		final List<Career> careers = this.careerQueryService.findAllCareers(this.getUserContetx());

		return JResponse.ok(careers).build();
	}
}
