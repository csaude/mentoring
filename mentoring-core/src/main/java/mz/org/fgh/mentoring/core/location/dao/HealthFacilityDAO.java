/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;

/**
 * @author Stélio Moiane
 *
 */
public interface HealthFacilityDAO extends GenericDAO<HealthFacility, Long> {

	String NAME = "mz.org.fgh.mentoring.core.location.dao.HealthFacilityDAO";
}
