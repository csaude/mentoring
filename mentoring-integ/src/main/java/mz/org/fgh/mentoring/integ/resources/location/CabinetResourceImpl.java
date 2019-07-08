/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.location;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.location.model.Cabinet;
import mz.org.fgh.mentoring.core.location.service.CabinetQueryService;

/**
 * @author Stélio Moiane
 *
 */
@Service(CabinetResourceImpl.NAME)
@Path("cabinets")
public class CabinetResourceImpl implements CabinetResource {

	public static final String NAME = "mz.org.fgh.mentoring.integ.resources.location.CabinetResourceImpl";

	@Inject
	private CabinetQueryService cabinetQueryService;

	@Override
	public JResponse<List<Cabinet>> getCabinets() throws BusinessException {
		final List<Cabinet> cabinets = this.cabinetQueryService.findAllCabinets();
		return JResponse.ok(cabinets).build();
	}
}
