/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.sector;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.sector.model.Sector;
import mz.org.fgh.mentoring.core.sector.service.SectorService;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(SectorResource.NAME)
@Path("sectors")
public class SectorResourceImpl implements SectorResource {

	@Inject
	private SectorService sectorService;

	@Override
	public JResponse<Sector> createSector(final SectorBeanResource sectorBeanResource) throws BusinessException {

		final Sector sector = this.sectorService.createSector(sectorBeanResource.getUserContext(),
				sectorBeanResource.getSector());

		return JResponse.ok(sector).build();
	}
}
