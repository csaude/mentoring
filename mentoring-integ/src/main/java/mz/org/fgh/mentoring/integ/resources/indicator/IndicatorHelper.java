/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.indicator;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.org.fgh.mentoring.core.indicator.model.Indicator;
import mz.org.fgh.mentoring.integ.resources.mentorship.AnswerHelper;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IndicatorHelper {

	private Indicator indicator;

	private List<AnswerHelper> answerHelpers;

	public Indicator getIndicator() {
		if (this.indicator != null) {
			this.indicator.setId(null);
		}
		return this.indicator;
	}

	public void setIndicator(final Indicator indicator) {
		this.indicator = indicator;
	}

	public List<AnswerHelper> getAnswerHelpers() {
		return this.answerHelpers;
	}

	public void setAnswerHelpers(final List<AnswerHelper> answerHelpers) {
		this.answerHelpers = answerHelpers;
	}
}
