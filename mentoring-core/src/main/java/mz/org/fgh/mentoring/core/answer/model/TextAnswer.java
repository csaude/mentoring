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
@DiscriminatorValue(TextAnswer.NAME)
public class TextAnswer extends Answer {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "TEXT";

	@Column(name = "TEXT_VALUE", length = 180)
	private String textValue;

	public String getTextValue() {
		return this.textValue;
	}

	public void setTextValue(final String textValue) {
		this.textValue = textValue;
	}
}
