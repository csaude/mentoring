/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.util;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.service.CareerQueryService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.formquestion.service.FormQuestionQueryService;
import mz.org.fgh.mentoring.core.location.model.Cabinet;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.location.service.CabinetQueryService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityQueryService;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;
import mz.org.fgh.mentoring.core.tutored.service.TutoredQueryService;

/**
 * @author Stélio Moiane
 *
 */
@Service(MetadataResource.NAME)
@Path("metadata")
public class MetadataResourceImpl implements MetadataResource {

	@Inject
	private HealthFacilityQueryService healthFacilityQueryService;

	@Inject
	private CareerQueryService careerQueryService;

	@Inject
	private FormQuestionQueryService formQuestionQueryService;

	@Inject
	private TutoredQueryService tutoredQueryService;

	@Inject
	private CabinetQueryService cabinetQueryService;

	@Override
	public JResponse<Metadata> loadMetadata(final String uuid) throws BusinessException {

		final UserContext userContext = new UserContext();
		userContext.setUuid(uuid);

		final List<HealthFacility> healthFacilities = this.healthFacilityQueryService
		        .fetchAllHealthFacilities(userContext);

		final List<Career> careers = this.careerQueryService.findAllCareers(userContext);

		final List<FormQuestion> formQuestions = this.formQuestionQueryService.fetchFormQuestionsByTutor(userContext);

		final List<Tutored> tutoreds = this.tutoredQueryService.findTutoredsByUser(userContext.getUuid());

		final List<Cabinet> cabinets = this.cabinetQueryService.findAllCabinets();

		final Metadata metadata = new Metadata(healthFacilities, careers, formQuestions, tutoreds, cabinets);

		return JResponse.ok(metadata).build();
	}
}
