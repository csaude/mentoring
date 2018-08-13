/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import java.util.List;
import java.util.logging.Logger;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.files.FileReaderService;
import mz.co.mozview.frameworks.core.util.GenericObject;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.model.CareerType;
import mz.org.fgh.mentoring.core.career.service.CareerQueryService;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.model.FormTarget;
import mz.org.fgh.mentoring.core.form.service.FormQueryService;
import mz.org.fgh.mentoring.core.form.service.FormTargetService;

/**
 * @author Stélio Moiane
 *
 */
public class FormTargetClient extends ClientConfig<FormTargetClient> {

	private int records;

	private FileReaderService fileReaderService;

	private FormQueryService formQueryService;

	private CareerQueryService careerQueryService;

	private FormTargetService formTargetService;

	private static Logger logger = Logger.getLogger(FormTargetClient.class.getName());

	@Override
	public int process(final FormTargetClient client) throws BusinessException {

		final List<GenericObject> formTargets = this.fileReaderService.readfile("form-targets.xlsx");

		formTargets.forEach(formTargetObject -> {
			this.addFormTarget(formTargetObject);
		});

		return this.records;
	}

	private void addFormTarget(final GenericObject formTargetObject) {

		final String formName = (String) formTargetObject.getValue("form_name");
		final CareerType careerType = CareerType.valueOf((String) formTargetObject.getValue("career_type"));
		final String position = (String) formTargetObject.getValue("position");
		final Double target = (Double) formTargetObject.getValue("target");

		final FormTarget formTarget = new FormTarget();
		formTarget.setForm(this.getForm(formName));
		formTarget.setCareer(this.getCareer(careerType, position));
		formTarget.setTarget(target.intValue());

		this.formTargetService.createFormTarget(this.getUserContext(), formTarget);
		this.records++;
	}

	private Career getCareer(final CareerType careerType, final String position) {

		try {
			return this.careerQueryService.findCareerByTypeAndPosition(careerType, position);
		}
		catch (final BusinessException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Form getForm(final String formName) {
		try {
			final List<Form> forms = this.formQueryService.findBySelectedFilter(this.getUserContext(), null, formName,
			        null);
			return forms.get(0);
		}
		catch (final BusinessException e) {

			e.printStackTrace();
		}
		return null;
	}

	public void setCareerQueryService(final CareerQueryService careerQueryService) {
		this.careerQueryService = careerQueryService;
	}

	public void setFormQueryService(final FormQueryService formQueryService) {
		this.formQueryService = formQueryService;
	}

	public void setFileReaderService(final FileReaderService fileReaderService) {
		this.fileReaderService = fileReaderService;
	}

	public void setFormTargetService(final FormTargetService formTargetService) {
		this.formTargetService = formTargetService;
	}

	public static void main(final String[] args) throws BusinessException {
		logger.info("The Client is Starting to execute ........");

		final FormTargetClient client = new FormTargetClient();
		client.setup();

		client.setFileReaderService(client.getBean(FileReaderService.class));
		client.setCareerQueryService(client.getBean(CareerQueryService.class));
		client.setFormQueryService(client.getBean(FormQueryService.class));
		client.setFormTargetService(client.getBean(FormTargetService.class));

		client.process(client);

		client.close();

		logger.info("The Client was executed with success ........");
	}
}
