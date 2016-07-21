/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.util;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Stélio Moiane
 *
 *         Suporte de datas : Java8 e JPA
 *
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(final LocalDate localDate) {
		return localDate == null ? null : Date.valueOf(localDate);
	}

	@Override
	public LocalDate convertToEntityAttribute(final Date date) {
		return date == null ? null : date.toLocalDate();
	}
}
