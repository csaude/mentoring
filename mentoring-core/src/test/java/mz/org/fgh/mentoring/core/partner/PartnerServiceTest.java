/**
 *
 */
package mz.org.fgh.mentoring.core.partner;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.PartnerTemplate;
import mz.org.fgh.mentoring.core.partner.model.Partner;
import mz.org.fgh.mentoring.core.partner.service.PartnerService;

/**
 * @author Stélio Moiane
 *
 */
public class PartnerServiceTest extends AbstractSpringTest {

	@Inject
	private PartnerService partnerService;

	private Partner partner;

	@Override
	public void setUp() throws BusinessException {
		this.partner = EntityFactory.gimme(Partner.class, PartnerTemplate.VALID);
	}

	@Test
	public void shouldCreatePartner() throws BusinessException {

		this.partnerService.createPartner(this.getUserContext(), this.partner);

		TestUtil.assertCreation(this.partner);
	}

	@Test
	public void shouldUpdatePartner() throws BusinessException {

		this.partnerService.createPartner(this.getUserContext(), this.partner);
		this.partnerService.updatePartner(this.getUserContext(), this.partner);

		TestUtil.assertUpdate(this.partner);
	}
}
