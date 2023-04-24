package mz.org.fgh.mentoring.core.setting.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.setting.model.PartnerSetting;

/***
 * 
 * @author Jose Julai Ritsure
 *
 */
public interface PartnerSettingDAO extends GenericDAO<PartnerSetting, Long> {

	String NAME = "mz.org.fgh.mentoring.core.setting.dao.PartnerSettingDAO";

	class QUERY {
		public static final String findByDesignation = "SELECT ps FROM PartnerSetting ps "
				+ " INNER JOIN FETCH ps.setting s INNER JOIN FETCH ps.partner p "
				+ " WHERE s.designation = :designation AND p.id = :partnerId AND ps.lifeCycleStatus = :lifeCycleStatus "
				+ " AND s.lifeCycleStatus = :lifeCycleStatus AND p.lifeCycleStatus = :lifeCycleStatus";

		public static final String findByPartnerId = "SELECT ps FROM PartnerSetting ps "
				+ " INNER JOIN FETCH ps.setting s INNER JOIN FETCH ps.partner p "
				+ " WHERE p.id = :partnerId AND ps.lifeCycleStatus = :lifeCycleStatus "
				+ " AND s.lifeCycleStatus = :lifeCycleStatus AND p.lifeCycleStatus = :lifeCycleStatus";
	}

	class QUERY_NAME {
		public static final String findByDesignation = "PartnerSetting.findByDesignation";
		public static final String findByPartnerId = "PartnerSetting.findByPartnerId";
	}

	public abstract PartnerSetting findByDesignation(final String designation, final Long partnerId,
			final LifeCycleStatus lifeCycleStatus);

	public abstract List<PartnerSetting> findByPartnerId(final Long partnerId, final LifeCycleStatus lifeCycleStatus);

}
