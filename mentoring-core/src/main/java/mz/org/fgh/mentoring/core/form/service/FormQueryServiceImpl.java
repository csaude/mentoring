/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.service;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.dao.FormDAO;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.indicator.model.SampleQuestion;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(FormQueryService.NAME)
public class FormQueryServiceImpl implements FormQueryService {

	@Inject
	private FormDAO formDAO;

	@Override
	public Form fetchByForm(final UserContext userContext, final Form form) throws BusinessException {
		return this.formDAO.fetchByFormId(form.getId());
	}

	@Override
	public List<Form> findBySelectedFilter(final UserContext userContext, final String code, final String name,
	        final String programmaticAreaCode) {
		return this.formDAO.findBySelectedFilter(code, name, programmaticAreaCode, LifeCycleStatus.ACTIVE);
	}

	@Override
	public Form findFormByUuid(final UserContext userContext, final String uuid) throws BusinessException {
		return this.formDAO.findByUuid(uuid);
	}

	@Override
	public List<Form> findSampleIndicatorForms() throws BusinessException {

		return this.formDAO.findSampleIndicators(Arrays.asList(SampleQuestion.NUMBER_OF_COLLECTED_SAMPLES.getValue(),
		        SampleQuestion.NUMBER_OF_REJECTED_SAMPLES.getValue(),
		        SampleQuestion.NUMBER_OF_TRANSPORTED_SAMPLES.getValue(),
		        SampleQuestion.NUMBER_OF_RECEIVED_SAMPLES.getValue()), LifeCycleStatus.ACTIVE);
	}
}
