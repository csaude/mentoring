/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.sector;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutorTemplate;
import mz.org.fgh.mentoring.core.sector.dao.SectorDAO;
import mz.org.fgh.mentoring.core.sector.model.Sector;
import mz.org.fgh.mentoring.core.sector.service.SectorService;

import org.junit.Test;

/**
 * @author Eusebio Jose Maposse
 *
 *
 */
public class SectorServiceTest extends AbstractSpringTest {

	@Inject
	private SectorService sectorService;

	@Inject
	private SectorDAO sectorDAO;

	private Sector sector;

	@Override
	public void setUp() throws BusinessException {
		this.sector = EntityFactory.gimme(Sector.class, TutorTemplate.VALID);
	}

	@Test
	public void shouldCreateSector() throws BusinessException {

		this.sectorService.createSector(this.getUserContext(), this.sector);

		TestUtil.assertCreation(this.sector);
	}

	@Test
	public void shouldUpdateSector() throws BusinessException {

		this.sectorService.createSector(this.getUserContext(), this.sector);

		final Sector sectorUpdate = this.sectorDAO.findById(this.sector.getId());


		this.sectorService.updateSector(this.getUserContext(), sectorUpdate);

		TestUtil.assertUpdate(sectorUpdate);
	}

}
