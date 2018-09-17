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
import mz.org.fgh.mentoring.core.location.model.Province;
import mz.org.fgh.mentoring.core.location.service.DistrictQueryService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityQueryService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;

/**
 * @author Stélio Moiane
 *
 */
public class AddHealthFacilityClient extends ClientConfig<AddHealthFacilityClient> {

	private HealthFacilityService healthFacilityService;

	private FileReaderService fileReaderService;

	private static Logger logger = Logger.getLogger(AddHealthFacilityClient.class.getName());

	private DistrictService districtService;

	private DistrictQueryService districtQueryService;

	private HealthFacilityQueryService healthFacilityQueryService;

	public static void main(final String[] args) throws BusinessException {
		logger.info("The Client is Starting to execute ........");

		final AddHealthFacilityClient client = new AddHealthFacilityClient();
		client.setup();

		client.setFileReaderService(client.getBean(FileReaderService.class));
		client.setHealthFacilityService(client.getBean(HealthFacilityService.class));
		client.setDistrictService(client.getBean(DistrictService.class));
		client.setDistrictQueryService(client.getBean(DistrictQueryService.class));
		client.setHealthFacilityQueryService(client.getBean(HealthFacilityQueryService.class));

		client.process(client);

		client.close();

		logger.info("The Client was executed with success ........");
	}

	@Override
	public int process(final AddHealthFacilityClient client) throws BusinessException {

		int records = 0;

		final List<GenericObject> healthFacilities = this.fileReaderService
		        .readfile("add-health-facilities_20181029.xlsx");

		for (final GenericObject genericObject : healthFacilities) {

			final String provinceName = (String) genericObject.getValue("Province");
			final String districtName = (String) genericObject.getValue("District");
			final String healthFacilityName = (String) genericObject.getValue("Health_Facility");

			final Province province = Province.valueOf(provinceName);

			District foundDistrict = this.districtQueryService.findDistrictByProvinceAndName(this.getUserContext(),
			        province, districtName);

			if (foundDistrict == null) {
				foundDistrict = this.createNewDistrict(districtName, province);
			}

			final HealthFacility foundfHealthFacility = this.healthFacilityQueryService
			        .findHealthFacilityByDistrictAndName(foundDistrict, healthFacilityName);

			if (foundfHealthFacility == null) {
				this.createNewHealthFacility(healthFacilityName, foundDistrict);
			}

			records++;
		}

		logger.info(records + " Health Facility (ies) was (were) Created with success...");

		return records;
	}

	private void createNewHealthFacility(final String healthFacilityName, final District foundDistrict)
	        throws BusinessException {
		final HealthFacility healthFacility = new HealthFacility();
		healthFacility.setDistrict(foundDistrict);
		healthFacility.setHealthFacility(healthFacilityName);

		this.healthFacilityService.createHealthFacility(this.getUserContext(), healthFacility);
	}

	private District createNewDistrict(final String districtName, final Province province) throws BusinessException {
		District foundDistrict;
		final District districtToCreate = new District();
		districtToCreate.setProvince(province);
		districtToCreate.setDistrict(districtName);

		foundDistrict = this.districtService.createDistrict(this.getUserContext(), districtToCreate);
		return foundDistrict;
	}

	public void setHealthFacilityService(final HealthFacilityService healthFacilityService) {
		this.healthFacilityService = healthFacilityService;
	}

	public void setFileReaderService(final FileReaderService fileReaderService) {
		this.fileReaderService = fileReaderService;
	}

	public void setDistrictService(final DistrictService districtService) {
		this.districtService = districtService;
	}

	public void setDistrictQueryService(final DistrictQueryService districtQueryService) {
		this.districtQueryService = districtQueryService;
	}

	public void setHealthFacilityQueryService(final HealthFacilityQueryService healthFacilityQueryService) {
		this.healthFacilityQueryService = healthFacilityQueryService;
	}
}
