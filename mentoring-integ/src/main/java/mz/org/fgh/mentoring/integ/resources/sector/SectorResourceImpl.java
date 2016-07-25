/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.sector;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.sector.model.Sector;
import mz.org.fgh.mentoring.core.sector.service.SectorQueryService;
import mz.org.fgh.mentoring.core.sector.service.SectorService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(SectorResource.NAME)
@Path("sectors")
public class SectorResourceImpl extends AbstractResource implements SectorResource {

	@Inject
	private SectorService sectorService;

	@Inject
	private SectorQueryService sectorQueryService;

	@Override
	public JResponse<Sector> createSector(final SectorBeanResource sectorBeanResource) throws BusinessException {

		final Sector sector = this.sectorService.createSector(sectorBeanResource.getUserContext(),
				sectorBeanResource.getSector());

		return JResponse.ok(sector).build();
	}

	@Override
	public JResponse<List<Sector>> findSectors(String code, String name, String description) throws BusinessException {
		final List<Sector> sectors = this.sectorQueryService.findSectorsBySelectedFilter(this.getUserContetx(), code,
				name, description);

		return JResponse.ok(sectors).build();
	}

	@Override
	public JResponse<Sector> updateSector(SectorBeanResource SectorBeanResource) throws BusinessException {
		final Sector sector = this.sectorService.updateSector(SectorBeanResource.getUserContext(),
				SectorBeanResource.getSector());

		return JResponse.ok(sector).build();
	}
}
