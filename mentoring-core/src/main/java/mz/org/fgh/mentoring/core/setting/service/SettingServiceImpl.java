package mz.org.fgh.mentoring.core.setting.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.util.PropertyValues;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.setting.dao.PartnerSettingDAO;
import mz.org.fgh.mentoring.core.setting.model.PartnerSetting;
import mz.org.fgh.mentoring.core.setting.model.Setting;
import mz.org.fgh.mentoring.core.tutor.dao.TutorDAO;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/***
 * 
 * @author Jose Julai Ritsure
 *
 */
@Service(SettingService.NAME)
public class SettingServiceImpl implements SettingService {

	@Inject
	private PartnerSettingDAO partnerSettingDAO;

	@Inject
	private TutorDAO tutorDAO;

	@Inject
	private PropertyValues propertyValues;

	@Override
	public Setting findSettingByDesignation(final UserContext userContext, String designation)
			throws BusinessException {

		if (!StringUtils.hasLength(designation)) {
			throw new RuntimeException("Indique a descrição da configuração que pretende pesquisar!");
		}

		final Tutor tutor = tutorDAO.findByUuid(userContext.getUuid());

		Setting setting = null;

		try {
			setting = partnerSettingDAO
					.findByDesignation(designation, tutor.getPartner().getId(), LifeCycleStatus.ACTIVE).getSetting();
		} catch (final NoResultException ex) {
			throw new BusinessException(this.propertyValues.getPropValues("no.result.found"));
		}

		return setting;
	}

	@Override
	public List<Setting> findSettingByTutor(UserContext userContext) throws BusinessException {
		
		final Tutor tutor = tutorDAO.findByUuid(userContext.getUuid());

		List<Setting> settings = new ArrayList<>(0);

		try {
			List<PartnerSetting> partnerSettings = partnerSettingDAO.findByPartnerId(tutor.getPartner().getId(),
					LifeCycleStatus.ACTIVE);
			
			if(partnerSettings.isEmpty()) {
				return new ArrayList<>(0);
			}
			
			for (PartnerSetting partnerSetting : partnerSettings) {
				Setting setting = partnerSetting.getSetting();
				settings.add(setting);
			}
			
		} catch (final NoResultException ex) {
			throw new BusinessException(this.propertyValues.getPropValues("no.result.found"));
		}

		return settings;
	}

}
