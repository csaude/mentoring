/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutored;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutorTemplate;
import mz.org.fgh.mentoring.core.tutored.dao.TutoredDAO;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;

/**
 * @author Stélio Moiane
 *
 *
 */
public class TutoredServiceTest extends AbstractSpringTest {

	@Inject
	private TutoredService tutoredService;

	@Inject
	private TutoredDAO tutoredDAO;

	private Tutored tutored;

	@Override
	public void setUp() throws BusinessException {
		this.tutored = EntityFactory.gimme(Tutored.class, TutorTemplate.VALID);
	}

	@Test
	public void shouldCreateTutored() throws BusinessException {

		this.tutoredService.createTutored(this.getUserContext(), this.tutored);

		TestUtil.assertCreation(this.tutored);
	}

	@Test
	public void shouldUpdateTutored() throws BusinessException {

		this.tutoredService.createTutored(this.getUserContext(), this.tutored);

		final Tutored updateTutored = this.tutoredDAO.findById(this.tutored.getId());

		final String name = "Bernado Jose";
		final String surname = "Cossa";

		updateTutored.setName(name);
		updateTutored.setSurname(surname);

		this.tutoredService.updateTutored(this.getUserContext(), updateTutored);

		TestUtil.assertUpdate(updateTutored);

		assertEquals(name, updateTutored.getName());
		assertEquals(surname, updateTutored.getSurname());
	}

}
