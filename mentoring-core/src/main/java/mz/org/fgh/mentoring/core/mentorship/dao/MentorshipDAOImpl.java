/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.mentorship.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

import org.springframework.stereotype.Repository;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(MentorshipDAO.NAME)
public class MentorshipDAOImpl extends GenericDAOImpl<Mentorship, Long> implements MentorshipDAO {

}
