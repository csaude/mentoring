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
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.location.service.CabinetQueryService;
import mz.org.fgh.mentoring.core.location.service.CabinetService;

/**
 * @author Stélio Moiane
 *
 */
public class AddCabinetsClientTest extends AbstractSpringTest {

	@Inject
	private FileReaderService fileReaderService;

	@Inject
	private CabinetService cabinetService;

	@Inject
	private CabinetQueryService cabinetQueryService;

	private AddCabinetsClient client;

	@Override
	public void setUp() throws BusinessException {
		this.client = new AddCabinetsClient();
		this.client.setCabinetService(this.cabinetService);
		this.client.setCabinetQueryService(this.cabinetQueryService);
		this.client.setFileReaderService(this.fileReaderService);
	}

	@Test
	public void shouldAddCabinets() throws BusinessException {
		final List<GenericObject> lines = this.fileReaderService.readfile("cabinets.xlsx");
		final int records = this.client.process(this.client);

		assertEquals(lines.size(), records);
	}
}
