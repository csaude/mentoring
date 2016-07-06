/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutorando.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.tutorando.model.Tutorando;

import org.springframework.stereotype.Repository;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(TutorandoDAO.NAME)
public class TutorandoDAOImpl extends GenericDAOImpl<Tutorando, Long> implements TutorandoDAO {

}
