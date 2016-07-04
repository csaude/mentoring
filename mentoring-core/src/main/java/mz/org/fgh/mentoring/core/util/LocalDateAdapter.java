/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.org.fgh.mentoring.core.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Stélio Moiane
 *
 *         To support serialization
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	@Override
	public LocalDate unmarshal(final String value) throws Exception {
		return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}

	@Override
	public String marshal(final LocalDate value) throws Exception {
		final LocalDate date = value;

		return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}
}
