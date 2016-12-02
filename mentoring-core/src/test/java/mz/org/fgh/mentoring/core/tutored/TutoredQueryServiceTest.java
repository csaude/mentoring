package mz.org.fgh.mentoring.core.tutored;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutoredTemplate;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;
import mz.org.fgh.mentoring.core.tutored.service.TutoredQueryService;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;


public class TutoredQueryServiceTest extends AbstractSpringTest {

	private Tutored tutored;

	@Inject
	private TutoredService tutoredService;

	@Inject
	private TutoredQueryService TutoredQueryService;
	
	@Inject
	private CareerService carrerService;
	

	@Override
	public void setUp() throws BusinessException {
		this.tutored = EntityFactory.gimme(Tutored.class, TutoredTemplate.VALID);
		this.carrerService.createCareer(this.getUserContext(), this.tutored.getCareer());
		this.tutoredService.createTutored(this.getUserContext(), this.tutored);
	}

	@Test
	public void shouldFindTutorBySelectedFilter() throws BusinessException {

		final String code = null;
		final String name = null;
		final String phoneNumber = null;
		final String surname = "Maposse";
		final String carrer =  null;

		final List<Tutored> tutoreds = this.TutoredQueryService.findTutoredsBySelectedFilter(this.getUserContext(),
				code, name, surname, phoneNumber, carrer);

		assertFalse(tutoreds.isEmpty());

		for (final Tutored tutored : tutoreds) {
			assertEquals("Maposse", tutored.getSurname());

		}
	}
}
