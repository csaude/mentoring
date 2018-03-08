/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.answer.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Stélio Moiane
 *
 */
@Entity
@DiscriminatorValue(BooleanAnswer.NAME)
public class BooleanAnswer extends Answer {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "BOOLEAN";

	@Column(name = "BOOLEAN_VALUE")
	private Boolean booleanValue;

	public Boolean getBooleanValue() {
		return this.booleanValue;
	}

	@Override
	public void setValue(final String value) {
		this.booleanValue = Boolean.valueOf(value);
	}

	@Override
	public String getValue() {
		return String.valueOf(this.booleanValue);
	}
}
