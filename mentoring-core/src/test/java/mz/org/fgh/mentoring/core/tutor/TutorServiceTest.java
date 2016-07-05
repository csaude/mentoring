/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutor;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutorTemplate;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;

/**
 * @author Stélio Moiane
 *
 */
public class TutorServiceTest extends AbstractSpringTest {

	@Inject
	private TutorService tutorService;

	@Override
	public void setUp() throws BusinessException {

	}

	@Test
	public void shouldCreateTutor() throws BusinessException {

		final Tutor tutor = EntityFactory.gimme(Tutor.class, TutorTemplate.VALID);

		this.tutorService.createTutor(this.getUserContext(), tutor);

		TestUtil.assertCreation(tutor);
	}
}
