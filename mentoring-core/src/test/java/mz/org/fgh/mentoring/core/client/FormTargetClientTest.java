/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.files.FileReaderService;
import mz.co.mozview.frameworks.core.util.GenericObject;
import mz.org.fgh.mentoring.core.career.service.CareerQueryService;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.form.FormBuilder;
import mz.org.fgh.mentoring.core.form.service.FormQueryService;
import mz.org.fgh.mentoring.core.form.service.FormTargetService;

/**
 * @author Stélio Moiane
 *
 */
public class FormTargetClientTest extends AbstractSpringTest {

	@Inject
	private FileReaderService fileReaderService;

	@Inject
	private CareerQueryService careerQueryService;

	@Inject
	private FormQueryService formQueryService;

	@Inject
	private CareerService careerService;

	@Inject
	private FormTargetService formTargetService;

	@Inject
	private FormBuilder formBuilder;

	private FormTargetClient client;

	@Override
	public void setUp() throws BusinessException {

		this.client = new FormTargetClient();
		this.client.setFileReaderService(this.fileReaderService);
		this.client.setCareerQueryService(this.careerQueryService);
		this.client.setFormQueryService(this.formQueryService);
		this.client.setFormTargetService(this.formTargetService);

		final CarrerClient carrerClient = new CarrerClient();
		carrerClient.setFileReaderService(this.fileReaderService);
		carrerClient.setCareerQueryService(this.careerQueryService);
		carrerClient.setCareerService(this.careerService);
		carrerClient.process(carrerClient);

		this.formBuilder.withName("TABELA DE VERIFICACAO DAS COMPETENCIAS EM ATIP").build();
	}

	@Test
	public void shouldAddFormTargets() throws BusinessException {

		final int records = this.client.process(this.client);
		final List<GenericObject> careers = this.fileReaderService.readfile("form-targets.xlsx");
		Assert.assertEquals(careers.size(), records);
	}
}
