/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.sector.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.sector.model.Sector;

import org.springframework.stereotype.Repository;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(SectorDAO.NAME)
public class SectorDAOImpl extends GenericDAOImpl<Sector, Long> implements SectorDAO {

}
