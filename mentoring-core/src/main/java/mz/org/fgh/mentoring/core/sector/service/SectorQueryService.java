/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.sector.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.sector.model.Sector;


/**
 * @author Eusebio Jose Maposse
 *
 */
public interface SectorQueryService {

	String NAME = "mz.org.fgh.mentoring.core.sector.service.SectorQueryService";
	
	List<Sector> findSectorsBySelectedFilter(final UserContext userContext, final String code, final String name,
			final String description) throws BusinessException;

}
