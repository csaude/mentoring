/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.programmaticarea;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.ProgrammaticAreaTemplate;
import mz.org.fgh.mentoring.core.programmaticarea.dao.ProgrammaticAreaDAO;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;

/**
 * @author Eusebio Jose Maposse
 *
 *
 */
public class ProgrammaticAreaServiceTest extends AbstractSpringTest {

	@Inject
	private ProgrammaticAreaService sectorService;

	@Inject
	private ProgrammaticAreaDAO programmaticAreaDAO;

	private ProgrammaticArea programmaticArea;

	@Override
	public void setUp() throws BusinessException {
		this.programmaticArea = EntityFactory.gimme(ProgrammaticArea.class, ProgrammaticAreaTemplate.VALID);
	}

	@Test
	public void shouldCreateSector() throws BusinessException {

		this.sectorService.createProgrammaticArea(this.getUserContext(), this.programmaticArea);

		TestUtil.assertCreation(this.programmaticArea);
	}

	@Test
	public void shouldUpdateSector() throws BusinessException {

		this.sectorService.createProgrammaticArea(this.getUserContext(), this.programmaticArea);

		final ProgrammaticArea sectorUpdate = this.programmaticAreaDAO.findById(this.programmaticArea.getId());


		this.sectorService.updateProgrammaticArea(this.getUserContext(), sectorUpdate);

		TestUtil.assertUpdate(sectorUpdate);
	}
}
