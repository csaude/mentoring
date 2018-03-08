/*
 * Friends in Global Health - FGH © 2016
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
@DiscriminatorValue(TextAnswer.NAME)
public class TextAnswer extends Answer {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "TEXT";

	@Column(name = "TEXT_VALUE", length = 180)
	private String textValue;

	@Override
	public void setValue(final String value) {
		this.textValue = value;
	}

	@Override
	public String getValue() {
		return this.textValue;
	}
}
