/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.programmaticarea.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.programmaticarea.dao.ProgrammaticAreaDAO;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(ProgrammaticAreaService.NAME)
public class ProgrammaticAreaServiceImpl extends AbstractService implements ProgrammaticAreaService {

	@Inject
	private ProgrammaticAreaDAO sectorDao;

	@Override
	public ProgrammaticArea createProgrammaticArea(final UserContext userContext, final ProgrammaticArea programmaticArea) throws BusinessException {

		// TODO generate code just a sample
		final String code = this.sectorDao.generateCode("MT", 8, "0");
		programmaticArea.setCode(code);

		return this.sectorDao.create(userContext.getId(), programmaticArea);
	}

	@Override
	public ProgrammaticArea updateProgrammaticArea(final UserContext userContext, final ProgrammaticArea programmaticArea) throws BusinessException {

		return this.sectorDao.update(userContext.getId(), programmaticArea);
	}
}
