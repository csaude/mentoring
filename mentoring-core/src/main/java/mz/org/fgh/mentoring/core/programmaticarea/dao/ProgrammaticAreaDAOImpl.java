/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.programmaticarea.dao;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(ProgrammaticAreaDAO.NAME)
public class ProgrammaticAreaDAOImpl extends GenericDAOImpl<ProgrammaticArea, Long> implements ProgrammaticAreaDAO {

}
