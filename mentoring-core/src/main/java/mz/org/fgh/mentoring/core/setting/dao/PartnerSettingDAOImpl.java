package mz.org.fgh.mentoring.core.setting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.setting.model.PartnerSetting;

@Repository(PartnerSettingDAO.NAME)
public class PartnerSettingDAOImpl extends GenericDAOImpl<PartnerSetting, Long> implements PartnerSettingDAO {

	@Override
	public PartnerSetting findByDesignation(String designation, Long partnerId, final LifeCycleStatus lifeCycleStatus) {
		return this.findSingleByNamedQuery(PartnerSettingDAO.QUERY_NAME.findByDesignation,
				new ParamBuilder().add("designation", designation).add("partnerId", partnerId)
						.add("lifeCycleStatus", lifeCycleStatus).process());
	}

	@Override
	public List<PartnerSetting> findByPartnerId(Long partnerId, LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(PartnerSettingDAO.QUERY_NAME.findByPartnerId,
				new ParamBuilder().add("partnerId", partnerId).add("lifeCycleStatus", lifeCycleStatus).process());
	}

}
