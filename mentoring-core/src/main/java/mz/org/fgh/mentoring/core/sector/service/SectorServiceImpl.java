/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.sector.service;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.sector.dao.SectorDAO;
import mz.org.fgh.mentoring.core.sector.model.Sector;

import org.springframework.stereotype.Service;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(SectorService.NAME)
public class SectorServiceImpl extends AbstractService implements SectorService {

	@Inject
	private SectorDAO sectorDao;

	@Override
	public Sector createSector(final UserContext userContext, final Sector sector) throws BusinessException {

		// TODO generate code just a sample
		final String code = this.sectorDao.generateCode("MT", 8, "0");
		sector.setCode(code);

		return this.sectorDao.create(userContext.getId(), sector);
	}

	@Override
	public Sector updateSector(final UserContext userContext, final Sector sector) throws BusinessException {

		return this.sectorDao.update(userContext.getId(), sector);
	}
}
