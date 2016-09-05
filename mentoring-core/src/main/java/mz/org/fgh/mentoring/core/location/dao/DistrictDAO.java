/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.location.model.District;

/**
 * @author Stélio Moiane
 *
 */
public interface DistrictDAO extends GenericDAO<District, Long> {

	String NAME = "mz.org.fgh.mentoring.core.location.dao.DistrictDAO";

}
