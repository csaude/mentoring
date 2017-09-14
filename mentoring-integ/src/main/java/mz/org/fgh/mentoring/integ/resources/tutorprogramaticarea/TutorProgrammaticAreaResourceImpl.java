/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutorprogramaticarea;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgrammaticArea;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.service.TutorProgrammaticAreaService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(TutorProgrammaticAreaResource.NAME)
@Path("tutor-programmatic-areas")
public class TutorProgrammaticAreaResourceImpl extends AbstractResource implements TutorProgrammaticAreaResource {

	@Inject
	private TutorProgrammaticAreaService tutorProgramaticAreaService;

	@Override
	public JResponse<TutorProgrammaticArea> createTutorProgrammaticArea(
			TutorProgrammaticAreaBeanResource tutorProgrammaticAreaBeanResource) throws BusinessException {
	
		TutorProgrammaticArea tutorProgramaticArea = tutorProgramaticAreaService.mapTutorToProgramaticArea(
				tutorProgrammaticAreaBeanResource.getUserContext(), tutorProgrammaticAreaBeanResource.getTutorProgramaticArea());
		
		return JResponse.ok(tutorProgramaticArea).build();
	}

	@Override
	public JResponse<TutorProgrammaticArea> updateTutorProgrammaticArea(
			TutorProgrammaticAreaBeanResource tutorProgrammaticAreaBeanResource) throws BusinessException {

		TutorProgrammaticArea tutorProgramaticArea = tutorProgramaticAreaService.updateTutorProgramaticArea(
				getUserContetx(), tutorProgrammaticAreaBeanResource.getTutorProgramaticArea());

		return JResponse.ok(tutorProgramaticArea).build();
	}

}
