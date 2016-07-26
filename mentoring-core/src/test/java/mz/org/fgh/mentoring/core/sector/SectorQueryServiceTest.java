/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.sector;

import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.SectorTemplate;
import mz.org.fgh.mentoring.core.sector.model.Sector;
import mz.org.fgh.mentoring.core.sector.service.SectorQueryService;
import mz.org.fgh.mentoring.core.sector.service.SectorService;

/**
 * @author Eusebio Jose Maposse
 *
 */

public class SectorQueryServiceTest extends AbstractSpringTest {

	private Sector sector;

	@Inject
	private SectorService sectorService;

	@Inject
	private SectorQueryService sectorQueryService;

	@Override
	public void setUp() throws BusinessException {
		this.sector = EntityFactory.gimme(Sector.class,  SectorTemplate.VALID);
		this.sectorService.createSector(this.getUserContext(), this.sector);
	}

	@Test
	public void shouldFindSectorBySelectedFilter() throws BusinessException {

		final String code = null;
		final String name = null;
		final String description = null;

		final List<Sector> Sectors = this.sectorQueryService.findSectorsBySelectedFilter(this.getUserContext(), code,
				name, description);

		assertFalse(Sectors.isEmpty());

	}
}
