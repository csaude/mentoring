/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.dao;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.location.model.District;

/**
 * @author Stélio Moiane
 *
 */
@Repository(DistrictDAO.NAME)
public class DistrictDAOImpl extends GenericDAOImpl<District, Long> implements DistrictDAO {

}