/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutored;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutorTemplate;
import mz.org.fgh.mentoring.core.tutored.dao.TutoredDAO;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;

import org.junit.Test;

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

		this.tutoredService.createTutorando(this.getUserContext(), this.tutored);

		TestUtil.assertCreation(this.tutored);
	}

	@Test
	public void shouldUpdateTutored() throws BusinessException {

		this.tutoredService.createTutorando(this.getUserContext(), this.tutored);

		final Tutored tutorandoUpdate = this.tutoredDAO.findById(this.tutored.getId());

		tutorandoUpdate.setName("Bernado Jose");
		tutorandoUpdate.setSurname("Cossa");

		this.tutoredService.updateTutorando(this.getUserContext(), tutorandoUpdate);

		TestUtil.assertUpdate(tutorandoUpdate);
		assertNotNull(tutorandoUpdate.getId());
		assertEquals("Bernado Jose", tutorandoUpdate.getName());
		assertEquals("Cossa", tutorandoUpdate.getSurname());

	}


}
