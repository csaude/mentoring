/**
 *
 */
package mz.org.fgh.mentoring.core.session;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.SessionTemplate;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.session.service.SessionService;

/**
 * @author Stélio Moiane
 *
 */
public class SessionServiceTest extends AbstractSpringTest {

	@Inject
	private SessionService sessionService;

	private Session session;

	@Override
	public void setUp() throws BusinessException {
		this.session = EntityFactory.gimme(Session.class, SessionTemplate.VALID);
	}

	@Test
	public void shouldCreateMentoringSession() throws BusinessException {
		this.sessionService.createSession(this.getUserContext(), this.session);
		TestUtil.assertCreation(this.session);
	}
}
