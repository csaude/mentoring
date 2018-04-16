/**
 *
 */
package mz.org.fgh.mentoring.core.location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.CabinetTemplate;
import mz.org.fgh.mentoring.core.location.model.Cabinet;
import mz.org.fgh.mentoring.core.location.service.CabinetQueryService;
import mz.org.fgh.mentoring.core.location.service.CabinetService;

/**
 * @author Stélio Moiane
 *
 */
public class CabinetQueryServiceTest extends AbstractSpringTest {

	@Inject
	private CabinetService cabinetService;

	@Inject
	private CabinetQueryService cabinetQueryService;

	private Cabinet cabinet;

	@Override
	public void setUp() throws BusinessException {
		this.cabinet = EntityFactory.gimme(Cabinet.class, CabinetTemplate.VALID);
		this.cabinetService.createCabinet(this.getUserContext(), this.cabinet);
	}

	@Test
	public void shouldFindCabinetByName() throws BusinessException {

		final Cabinet foundCabinet = this.cabinetQueryService.findCabinetByName(this.cabinet.getName());

		assertNotNull(foundCabinet);
		assertEquals(this.cabinet.getName(), foundCabinet.getName());
	}

	@Test(expected = NoResultException.class)
	public void shouldNotFindCabinetByName() throws BusinessException {
		this.cabinetQueryService.findCabinetByName("stelio");
	}

	@Test
	public void shouldFindAllCabinets() throws BusinessException {
		final List<Cabinet> cabinets = this.cabinetQueryService.findAllCabinets();

		assertFalse(cabinets.isEmpty());
		final Cabinet cabinet2 = cabinets.stream().findFirst().get();
		assertEquals(cabinet2.getLifeCycleStatus(), this.cabinet.getLifeCycleStatus());
	}

	@Test
	public void shouldFindCabinetByUuid() throws BusinessException {
		final Cabinet foundCabinet = this.cabinetQueryService.findCabinetByUuid(this.cabinet.getUuid());
		assertEquals(foundCabinet.getUuid(), this.cabinet.getUuid());
	}
}
