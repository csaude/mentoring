/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import java.util.List;
import java.util.logging.Logger;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.files.FileReaderService;
import mz.co.mozview.frameworks.core.util.GenericObject;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;

/**
 * @author Stélio Moiane
 *
 */
public class AddHealthFacilityClient extends ClientConfig<AddHealthFacilityClient> {

	private HealthFacilityService healthFacilityService;

	private FileReaderService fileReaderService;

	private static Logger logger = Logger.getLogger(AddHealthFacilityClient.class.getName());

	public static void main(final String[] args) throws BusinessException {
		logger.info("The Client is Starting to execute ........");

		final AddHealthFacilityClient client = new AddHealthFacilityClient();
		client.setup();
		client.setFileReaderService(client.getBean(FileReaderService.class));
		client.setHealthFacilityService(client.getBean(HealthFacilityService.class));

		client.process(client);

		logger.info("The Client was executed with success ........");
	}

	@Override
	public int process(final AddHealthFacilityClient client) throws BusinessException {

		int records = 0;
		final List<GenericObject> healthFacilities = this.fileReaderService
		        .readfile("add-health-facilities_20171129.xlsx");

		for (final GenericObject genericObject : healthFacilities) {

			final Double districtId = (Double) genericObject.getValue("District_Id");
			final String healthFacilityName = (String) genericObject.getValue("Health_Facility");

			final District district = new District();
			district.setId(districtId.longValue());

			final HealthFacility healthFacility = new HealthFacility();
			healthFacility.setDistrict(district);
			healthFacility.setHealthFacility(healthFacilityName);

			this.healthFacilityService.createHealthFacility(this.getUserContext(), healthFacility);
			records++;
		}

		logger.info(records + " Health Facility (ies) was (were) Created with success...");

		return records;
	}

	public void setHealthFacilityService(final HealthFacilityService healthFacilityService) {
		this.healthFacilityService = healthFacilityService;
	}

	public void setFileReaderService(final FileReaderService fileReaderService) {
		this.fileReaderService = fileReaderService;
	}
}
