/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.carrer;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.carrer.model.Carrer;
import mz.org.fgh.mentoring.core.carrer.model.CarrerType;
import mz.org.fgh.mentoring.core.carrer.service.CarrerQueryService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Stélio Moiane
 *
 */
@Service(CarrerResource.NAME)
@Path("carrers")
public class CarrerResourceImpl extends AbstractResource implements CarrerResource {

	@Inject
	private CarrerQueryService carrerQueryService;

	@Override
	public JResponse<List<Carrer>> getCarrersByCarrerType(final CarrerType carrerType) throws BusinessException {

		final List<Carrer> carrers = this.carrerQueryService.findCarrersByCarrerType(this.getUserContetx(), carrerType);

		return JResponse.ok(carrers).build();
	}
}
