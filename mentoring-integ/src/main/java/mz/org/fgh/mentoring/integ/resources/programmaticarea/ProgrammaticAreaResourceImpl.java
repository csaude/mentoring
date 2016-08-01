/*
 * Friends in Global Health - FGH © 2016
 */

package mz.org.fgh.mentoring.integ.resources.programmaticarea;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaQueryService;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Stélio Moiane
 *
 */
@Service(ProgrammaticAreaResource.NAME)
public class ProgrammaticAreaResourceImpl extends AbstractResource implements ProgrammaticAreaResource {

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private ProgrammaticAreaQueryService programmaticAreaQueryService;

	@Override
	public JResponse<ProgrammaticArea> createProgrammaticArea(
			final ProgrammaticAreaBeanResource programmaticAreaBeanResource) throws BusinessException {

		final ProgrammaticArea programmaticArea = this.programmaticAreaService.createProgrammaticArea(
				programmaticAreaBeanResource.getUserContext(), programmaticAreaBeanResource.getProgrammaticArea());

		return JResponse.ok(programmaticArea).build();
	}

	@Override
	public JResponse<List<ProgrammaticArea>> findProgrammaticAreas(final String code, final String name,
			final String surname) throws BusinessException {

		final List<ProgrammaticArea> programmaticAreas = this.programmaticAreaQueryService
				.findSectorsBySelectedFilter(this.getUserContetx(), code, surname);

		return JResponse.ok(programmaticAreas).build();
	}

	@Override
	public JResponse<ProgrammaticArea> updateProgrammaticArea(
			final ProgrammaticAreaBeanResource programmaticAreaBeanResource) throws BusinessException {

		final ProgrammaticArea programmaticArea = this.programmaticAreaService.updateProgrammaticArea(
				programmaticAreaBeanResource.getUserContext(), programmaticAreaBeanResource.getProgrammaticArea());

		return JResponse.ok(programmaticArea).build();
	}

}
