/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.sector.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.sector.model.Sector;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface SectorService {

	String NAME = "mz.org.fgh.mentoring.core.sector.service.SectorService";

	Sector createSector(final UserContext userContext, final Sector sector) throws BusinessException;

	Sector updateSector(final UserContext userContext, final Sector sector) throws BusinessException;
}
