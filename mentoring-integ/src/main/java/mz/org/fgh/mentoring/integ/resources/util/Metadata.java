/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.util;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Metadata {

	private List<HealthFacility> healthFacility;

	private List<Career> career;

	private List<FormQuestion> formQuestion;

	private List<Tutored> tutored;

	public Metadata() {
	}

	public Metadata(final List<HealthFacility> healthFacilities, final List<Career> careers,
			final List<FormQuestion> formQuestions, final List<Tutored> tutoreds) {
		this.healthFacility = healthFacilities;
		this.career = careers;
		this.formQuestion = formQuestions;
		this.tutored = tutoreds;
	}

	public List<HealthFacility> getHealthFacilities() {
		return this.healthFacility;
	}

	public List<Career> getCareers() {
		return this.career;
	}

	public List<FormQuestion> getFormQuestion() {
		return this.formQuestion;
	}

	public List<Tutored> getTutored() {
		return this.tutored;
	}
}
