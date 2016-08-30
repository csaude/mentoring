/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Stélio Moiane
 *
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

	private static final String PATTERN = "dd-MM-yyyy HH:mm:ss";

	@Override
	public String marshal(final LocalDateTime value) throws Exception {
		return value.format(DateTimeFormatter.ofPattern(PATTERN));
	}

	@Override
	public LocalDateTime unmarshal(final String value) throws Exception {
		return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(PATTERN));
	}
}
