package mz.org.fgh.mentoring.core.tutor;

import static org.junit.Assert.assertNotNull;

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

public class TutorQueryServiceTest extends AbstractSpringTest {

	private Tutor tutor;

	@Inject
	private TutorService tutorService;

	@Inject
	private TutorQueryService TutorQueryService;

	@Override
	public void setUp() throws BusinessException {
		this.tutor = EntityFactory.gimme(Tutor.class, TutorTemplate.VALID);

	}

	@Test
	public void shouldCreateTutor() throws BusinessException {
		
		this.tutorService.createTutor(this.getUserContext(), this.tutor);
		
		final List<Tutor> tutors = this.TutorQueryService.findAllTutors(this.getUserContext());
		
		assertNotNull(tutors.get(0));
	}
}
