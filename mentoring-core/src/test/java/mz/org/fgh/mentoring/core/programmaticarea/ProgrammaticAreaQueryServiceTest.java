/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.programmaticarea;

import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.ProgrammaticAreaTemplate;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaQueryService;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;

/**
 * @author Stélio Moiane
 *
 */
public class ProgrammaticAreaQueryServiceTest extends AbstractSpringTest {

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private ProgrammaticAreaQueryService programmaticAreaQueryService;

	private ProgrammaticArea programmaticArea;

	@Override
	public void setUp() throws BusinessException {
		this.programmaticArea = EntityFactory.gimme(ProgrammaticArea.class, ProgrammaticAreaTemplate.VALID);
		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(), this.programmaticArea);
	}

	@Test
	public void shouldFindSectorBySelectedFilter() throws BusinessException {

		final String code = null;
		final String name = null;

		final List<ProgrammaticArea> programmaticAreas = this.programmaticAreaQueryService
				.findSectorsBySelectedFilter(this.getUserContext(), code, name);

		assertFalse(programmaticAreas.isEmpty());
	}
}
