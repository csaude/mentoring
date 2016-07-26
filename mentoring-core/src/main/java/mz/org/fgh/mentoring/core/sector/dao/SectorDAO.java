/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.sector.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.sector.model.Sector;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface SectorDAO extends GenericDAO<Sector, Long> {

	public String NAME = "mz.org.fgh.mentoring.core.sector.dao.SectorDAO";

	public List<Sector> findBySelectedFilter(final UserContext userContext, final String code, final String name, final String descripion,
			LifeCycleStatus lifeCycleStatus);

}
