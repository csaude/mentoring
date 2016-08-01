/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.org.fgh.mentoring.core.form.model.Form;

import org.springframework.stereotype.Repository;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(FormDAO.NAME)
public class FormDAOImpl extends GenericDAOImpl<Form, Long> implements FormDAO {

	@Override
	public Form fetchByFormId(final Long formId) {
		return findSingleByNamedQuery(FormDAO.QUERY_NAME.fetchByFormId,
				new ParamBuilder().add("formId", formId).process());
	}

}
