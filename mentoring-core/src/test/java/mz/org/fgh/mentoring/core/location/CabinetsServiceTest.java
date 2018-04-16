/**
 *
 */
package mz.org.fgh.mentoring.core.location;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.CabinetTemplate;
import mz.org.fgh.mentoring.core.location.model.Cabinet;
import mz.org.fgh.mentoring.core.location.service.CabinetService;

/**
 * @author Stélio Moiane
 *
 */
public class CabinetsServiceTest extends AbstractSpringTest {

	@Inject
	private CabinetService cabinetService;

	private Cabinet cabinet;

	@Override
	public void setUp() throws BusinessException {
		this.cabinet = EntityFactory.gimme(Cabinet.class, CabinetTemplate.VALID);
	}

	@Test
	public void shouldCreateCabinet() throws BusinessException {
		this.cabinetService.createCabinet(this.getUserContext(), this.cabinet);
		TestUtil.assertCreation(this.cabinet);
	}
}
