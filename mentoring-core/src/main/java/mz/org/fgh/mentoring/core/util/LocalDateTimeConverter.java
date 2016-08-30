/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Stélio Moiane
 *
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(final LocalDateTime localDateTime) {
		return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(final Timestamp timestamp) {
		return timestamp == null ? null : timestamp.toLocalDateTime();
	}
}
