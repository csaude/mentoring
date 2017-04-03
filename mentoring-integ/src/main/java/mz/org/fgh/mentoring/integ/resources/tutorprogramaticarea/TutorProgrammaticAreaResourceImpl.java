/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutorprogramaticarea;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgramaticArea;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.service.TutorProgramaticAreaService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(TutorProgrammaticAreaResource.NAME)
@Path("tutorProgrammaticAreas")
public class TutorProgrammaticAreaResourceImpl extends AbstractResource implements TutorProgrammaticAreaResource {

	@Inject
	private TutorProgramaticAreaService tutorProgramaticAreaService;

	@Override
	public JResponse<TutorProgramaticArea> createTutorProgrammaticArea(
			TutorProgrammaticAreaBeanResource tutorProgrammaticAreaBeanResource) throws BusinessException {
	
		TutorProgramaticArea tutorProgramaticArea = tutorProgramaticAreaService.createTutorProgramaticArea(
				tutorProgrammaticAreaBeanResource.getUserContext(), tutorProgrammaticAreaBeanResource.getTutorProgramaticArea());
		
		return JResponse.ok(tutorProgramaticArea).build();
	}

	@Override
	public JResponse<TutorProgramaticArea> updateTutorProgrammaticArea(
			TutorProgrammaticAreaBeanResource tutorProgrammaticAreaBeanResource) throws BusinessException {

		TutorProgramaticArea tutorProgramaticArea = tutorProgramaticAreaService.updateTutorProgramaticArea(
				getUserContetx(), tutorProgrammaticAreaBeanResource.getTutorProgramaticArea());

		return JResponse.ok(tutorProgramaticArea).build();
	}

}
