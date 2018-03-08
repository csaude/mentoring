/**
 *
 */
package mz.org.fgh.mentoring.core.answer.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue(NumericAnswer.NAME)
public class NumericAnswer extends Answer {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "NUMERIC";

	@Column(name = "NUMERIC_VALUE")
	private Integer numericValue;

	@Override
	public void setValue(final String value) {

		if (value == null) {
			return;
		}

		this.numericValue = Integer.valueOf(value);
	}

	@Override
	public String getValue() {
		return String.valueOf(this.numericValue);
	}

	public Integer getNumericValue() {
		return this.numericValue;
	}
}
