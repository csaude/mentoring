/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutored.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

import org.springframework.stereotype.Repository;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(TutoredDAO.NAME)
public class TutoredDAOImpl extends GenericDAOImpl<Tutored, Long> implements TutoredDAO {

}
