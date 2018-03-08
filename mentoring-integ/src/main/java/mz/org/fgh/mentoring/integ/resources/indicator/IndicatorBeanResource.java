/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.indicator;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IndicatorBeanResource {

	private UserContext userContext;

	private List<IndicatorHelper> indicators;

	private List<String> indicatorsUuids;

	public UserContext getUserContext() {
		return this.userContext;
	}

	public void setUserContext(final UserContext userContext) {
		this.userContext = userContext;
	}

	public List<IndicatorHelper> getIndicators() {
		return this.indicators;
	}

	public void setIndicators(final List<IndicatorHelper> indicators) {
		this.indicators = indicators;
	}

	public void addIndicatorUuid(final String indicatorUuid) {
		if (this.indicatorsUuids == null) {
			this.indicatorsUuids = new ArrayList<>();
		}
		this.indicatorsUuids.add(indicatorUuid);
	}
}
