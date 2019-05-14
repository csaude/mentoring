/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutored.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.util.StringNormalizer;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.service.CareerQueryService;
import mz.org.fgh.mentoring.core.tutored.dao.TutoredDAO;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(TutoredService.NAME)
public class TutoredServiceImpl extends AbstractService implements TutoredService {

	@Inject
	private TutoredDAO tutoredDAO;

	@Inject
	private CareerQueryService careerQueryService;

	@Override
	public Tutored createTutored(final UserContext userContext, final Tutored tutored) throws BusinessException {

		final String code = this.tutoredDAO.generateCode("MTTD", 8, "0");
		tutored.setCode(code);

		tutored.setName(StringNormalizer.normalizeAndUppCase(tutored.getName()));
		tutored.setSurname(StringNormalizer.normalizeAndUppCase(tutored.getSurname()));

		return this.tutoredDAO.create(userContext.getUuid(), tutored);
	}

	@Override
	public Tutored updateTutored(final UserContext userContext, final Tutored tutored) throws BusinessException {

		tutored.setName(StringNormalizer.normalizeAndUppCase(tutored.getName()));
		tutored.setSurname(StringNormalizer.normalizeAndUppCase(tutored.getSurname()));

		this.tutoredDAO.update(userContext.getUuid(), tutored);

		return tutored;
	}

	@Override
	public List<Tutored> syncronizeTutoreds(final UserContext userContext, final List<Tutored> tutoreds)
	        throws BusinessException {

		for (final Tutored tutored : tutoreds) {

			try {
				final Tutored foundTutored = this.tutoredDAO.findByUuid(tutored.getUuid());
				final Career career = this.careerQueryService.findCarrerByuuid(userContext,
				        tutored.getCareer().getUuid());

				foundTutored.setCareer(career);
				foundTutored.setName(tutored.getName());
				foundTutored.setSurname(tutored.getSurname());
				foundTutored.setPhoneNumber(tutored.getPhoneNumber());
				foundTutored.setLifeCycleStatus(tutored.getLifeCycleStatus());

				this.updateTutored(userContext, foundTutored);
			}
			catch (final BusinessException e) {
			}
		}

		return tutoreds;
	}
}
