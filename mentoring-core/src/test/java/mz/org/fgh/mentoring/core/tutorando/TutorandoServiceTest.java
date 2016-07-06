/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutorando;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutorTemplate;
import mz.org.fgh.mentoring.core.tutorando.dao.TutorandoDAO;
import mz.org.fgh.mentoring.core.tutorando.model.Tutorando;
import mz.org.fgh.mentoring.core.tutorando.service.TutorandoService;

import org.junit.Test;

/**
 * @author Stélio Moiane
 * 
 *
 */
public class TutorandoServiceTest extends AbstractSpringTest {

	@Inject
	private TutorandoService tutorandoService;

	@Inject
	private TutorandoDAO tutorandoDAO;

	private Tutorando tutorando;

	@Override
	public void setUp() throws BusinessException {
		this.tutorando = EntityFactory.gimme(Tutorando.class, TutorTemplate.VALID);
	}

	@Test
	public void shouldCreateTutor() throws BusinessException {

		this.tutorandoService.createTutorando(this.getUserContext(), this.tutorando);

		TestUtil.assertCreation(this.tutorando);
	}

	@Test
	public void shouldUpdateTutor() throws BusinessException {

		this.tutorandoService.createTutorando(this.getUserContext(), this.tutorando);

		final Tutorando tutorandoUpdate = this.tutorandoDAO.findById(this.tutorando.getId());

		tutorandoUpdate.setName("Bernado Jose");
		tutorandoUpdate.setSurname("Cossa");

		this.tutorandoService.updateTutorando(this.getUserContext(), tutorandoUpdate);

		TestUtil.assertUpdate(tutorandoUpdate);
		assertNotNull(tutorandoUpdate.getId());
		assertEquals("Eurico Jose", tutorandoUpdate.getName());
		assertEquals("Maposse", tutorandoUpdate.getSurname());

	}


}
