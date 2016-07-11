/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.sector.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.sector.model.Sector;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface SectorDAO extends GenericDAO<Sector, Long> {
	
	public String NAME = "mz.org.fgh.mentoring.core.sector.dao.SectorDAO";
}
