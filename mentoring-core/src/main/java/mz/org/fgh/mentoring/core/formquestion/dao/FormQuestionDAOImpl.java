/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.formquestion.dao;

import java.util.List;

import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

/**
 * @author Eusebio Jose Maposse
 * @author Stélio Moiane
 *
 */
@Repository(FormQuestionDAO.NAME)
public class FormQuestionDAOImpl extends GenericDAOImpl<FormQuestion, Long> implements FormQuestionDAO {

	@Override
	public List<FormQuestion> findAllByFormId(final Long formId) {
		return this.findByNamedQuery(FormQuestionDAO.QUERY_NAME.findAllByFormId,
		        new ParamBuilder().add("formId", formId).process());
	}

	@Override
	public FormQuestion findByFormIdAndQuestionId(final Long formId, final Long questionId) {
		return this.findSingleByNamedQuery(FormQuestionDAO.QUERY_NAME.findByFormIdAndQuestionId,
		        new ParamBuilder().add("formId", formId).add("questionId", questionId).process());
	}

	@Override
	public List<FormQuestion> fetchByTutor(final LifeCycleStatus lifeCycleStatus, final Tutor tutor) {
		return this.findByNamedQuery(FormQuestionDAO.QUERY_NAME.fetchByTutor,
		        new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus)
								  .add("tutorUuid", tutor.getUuid())
								  .add("partnerUUID", tutor.getPartner().getUuid())
								  .add("MISAUUUUID", "398f0ffeb8fe11edafa10242ac120002").process());
	}

	@Override
	public List<FormQuestion> fetchByForm(final Form form, final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(FormQuestionDAO.QUERY_NAME.fetchByForm,
		        new ParamBuilder().add("formId", form.getId()).add("lifeCycleStatus", lifeCycleStatus).process());
	}
}
