/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.NoResultException;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.files.FileReaderService;
import mz.co.mozview.frameworks.core.util.GenericObject;
import mz.org.fgh.mentoring.core.location.model.Cabinet;
import mz.org.fgh.mentoring.core.location.service.CabinetQueryService;
import mz.org.fgh.mentoring.core.location.service.CabinetService;

/**
 * @author Stélio Moiane
 *
 */
public class AddCabinetsClient extends ClientConfig<AddCabinetsClient> {

	private static Logger logger = Logger.getLogger(AddCabinetsClient.class.getName());

	private FileReaderService fileReaderService;

	private CabinetService cabinetService;

	private CabinetQueryService cabinetQueryService;

	private int records;

	@Override
	public int process(final AddCabinetsClient client) throws BusinessException {

		final List<GenericObject> cabinets = this.fileReaderService.readfile("cabinets.xlsx");

		cabinets.forEach(cabinet -> {
			this.addCabinet(cabinet);
		});

		logger.info(this.records + " Cabinet(s) was (were) Created with success...");
		return this.records;
	}

	private void addCabinet(final GenericObject cabinet) {

		final String cabinetName = (String) cabinet.getValue("Name");

		try {
			this.cabinetQueryService.findCabinetByName(cabinetName);
		}
		catch (final NoResultException exception) {

			try {
				this.cabinetService.createCabinet(this.getUserContext(), new Cabinet(cabinetName));
				this.records++;
			}
			catch (final BusinessException e) {
				e.printStackTrace();
			}

		}
		catch (final BusinessException e) {
			e.printStackTrace();
		}
	}

	public void setCabinetService(final CabinetService cabinetService) {
		this.cabinetService = cabinetService;
	}

	public void setFileReaderService(final FileReaderService fileReaderService) {
		this.fileReaderService = fileReaderService;
	}

	public void setCabinetQueryService(final CabinetQueryService cabinetQueryService) {
		this.cabinetQueryService = cabinetQueryService;
	}

	public static void main(final String[] args) throws BusinessException {
		logger.info("The Client is Starting to execute ........");

		final AddCabinetsClient client = new AddCabinetsClient();
		client.setup();

		client.setFileReaderService(client.getBean(FileReaderService.class));
		client.setCabinetService(client.getBean(CabinetService.class));
		client.setCabinetQueryService(client.getBean(CabinetQueryService.class));

		client.process(client);

		client.close();

		logger.info("The Client was executed with success ........");
	}
}
