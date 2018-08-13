/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.files.FileReaderService;
import mz.co.mozview.frameworks.core.util.GenericObject;
import mz.org.fgh.mentoring.core.career.service.CareerQueryService;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;

/**
 * @author Stélio Moiane
 *
 */
public class CareerClientTest extends AbstractSpringTest {

	@Inject
	private FileReaderService fileReaderService;

	@Inject
	private CareerQueryService careerQueryService;

	@Inject
	private CareerService careerService;

	private CarrerClient client;

	@Override
	public void setUp() throws BusinessException {
		this.client = new CarrerClient();
		this.client.setFileReaderService(this.fileReaderService);
		this.client.setCareerQueryService(this.careerQueryService);
		this.client.setCareerService(this.careerService);
	}

	@Test
	public void shouldAddCareers() throws BusinessException {
		final int records = this.client.process(this.client);
		final List<GenericObject> careers = this.fileReaderService.readfile("mapping-careers.xlsx");
		assertEquals(careers.size(), records);
	}
}
