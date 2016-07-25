/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.sector.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.sector.dao.SectorDAO;
import mz.org.fgh.mentoring.core.sector.model.Sector;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(SectorQueryService.NAME)
public class SectorQueryServiceImpl implements SectorQueryService {
	
	@Inject
	private SectorDAO sectorDAO;

	@Override
	public List<Sector> findSectorsBySelectedFilter(UserContext userContext, String code, String name,
			String description) throws BusinessException {
		return sectorDAO.findBySelectedFilter(userContext, code, name, description, LifeCycleStatus.ACTIVE);
	}

}
