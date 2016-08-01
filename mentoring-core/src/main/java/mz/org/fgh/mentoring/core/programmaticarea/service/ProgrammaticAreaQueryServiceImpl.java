/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.programmaticarea.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.programmaticarea.dao.ProgrammaticAreaDAO;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;

/**
 * @author Stélio Moiane
 *
 */
@Service(ProgrammaticAreaQueryService.NAME)
public class ProgrammaticAreaQueryServiceImpl implements ProgrammaticAreaQueryService {

	@Inject
	private ProgrammaticAreaDAO programmaticAreaDAO;

	@Override
	public List<ProgrammaticArea> findSectorsBySelectedFilter(final UserContext userContext, final String code,
			final String name) throws BusinessException {

		return this.programmaticAreaDAO.findBySelectedFilter(userContext, code, name, LifeCycleStatus.ACTIVE);
	}
}
