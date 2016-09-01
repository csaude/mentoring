package mz.org.fgh.mentoring.core.tutor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutorTemplate;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorQueryService;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.util.Category;

public class TutorQueryServiceTest extends AbstractSpringTest {

	private Tutor tutor;

	@Inject
	private TutorService tutorService;

	@Inject
	private TutorQueryService TutorQueryService;

	@Override
	public void setUp() throws BusinessException {
		this.tutor = EntityFactory.gimme(Tutor.class, TutorTemplate.DATA_MANAGER);
		this.tutorService.createTutor(this.getUserContext(), this.tutor);
	}

	@Test
	public void shouldFindTutorBySelectedFilter() throws BusinessException {

		final String code = null;
		final String name = null;
		final String surname = null;
		final Category category = Category.DATA_MANAGER;

		final List<Tutor> tutors = this.TutorQueryService.findTutorsBySelectedFilter(this.getUserContext(), code, name,
				surname, category);

		assertFalse(tutors.isEmpty());

		for (final Tutor tutor : tutors) {
			assertEquals(category, tutor.getCategory());
		}
	}
}
