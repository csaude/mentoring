/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.quetion.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.form.quetion.model.FormQuetion;

import org.springframework.stereotype.Repository;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(FormQuetionDAO.NAME)
public class FormQuetionDAOImpl extends GenericDAOImpl<FormQuetion, Long> implements FormQuetionDAO {

}
