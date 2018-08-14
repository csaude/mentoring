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
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.model.CareerType;
import mz.org.fgh.mentoring.core.career.service.CareerQueryService;
import mz.org.fgh.mentoring.core.career.service.CareerService;

/**
 * @author Stélio Moiane
 *
 */
public class CarrerClient extends ClientConfig<CarrerClient> {

	private CareerService careerService;

	private CareerQueryService careerQueryService;

	private FileReaderService fileReaderService;

	private int records;

	private static Logger logger = Logger.getLogger(CarrerClient.class.getName());

	@Override
	public int process(final CarrerClient client) throws BusinessException {

		final List<GenericObject> careers = this.fileReaderService.readfile("mapping-careers.xlsx");

		careers.stream().forEach(career -> {
			this.addCareer(career);
		});

		logger.info(this.records + " Careers(s) was (were) Created with success...");
		return this.records;
	}

	private void addCareer(final GenericObject career) {
		final Career careerToAdd = new Career();

		final String carrerType = (String) career.getValue("Tipo");
		final String position = (String) career.getValue("Categoria");

		careerToAdd.setCareerType(CareerType.valueOf(carrerType));
		careerToAdd.setPosition(position);

		try {

			this.careerQueryService.findCareerByTypeAndPosition(careerToAdd.getCareerType(), careerToAdd.getPosition());
		}
		catch (NoResultException | BusinessException e) {
			try {
				this.careerService.createCareer(this.getUserContext(), careerToAdd);
				this.records++;
			}
			catch (final BusinessException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void setCareerQueryService(final CareerQueryService careerQueryService) {
		this.careerQueryService = careerQueryService;
	}

	public void setCareerService(final CareerService careerService) {
		this.careerService = careerService;
	}

	public void setFileReaderService(final FileReaderService fileReaderService) {
		this.fileReaderService = fileReaderService;
	}

	public static void main(final String[] args) throws BusinessException {
		logger.info("The Client is Starting to execute ........");

		final CarrerClient client = new CarrerClient();
		client.setup();

		client.setFileReaderService(client.getBean(FileReaderService.class));
		client.setCareerQueryService(client.getBean(CareerQueryService.class));
		client.setCareerService(client.getBean(CareerService.class));

		client.process(client);

		client.close();

		logger.info("The Client was executed with success ........");
	}
}
