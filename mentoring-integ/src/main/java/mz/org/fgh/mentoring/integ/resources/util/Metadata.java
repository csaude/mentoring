/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.util;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.form.model.FormTarget;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.location.model.Cabinet;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.setting.model.Setting;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Metadata {

	private List<HealthFacility> healthFacilities;

	private List<Career> careers;

	private List<FormQuestion> formQuestions;

	private List<Tutored> tutoreds;

	private List<Cabinet> cabinets;

	private List<Setting> settings;

	private List<FormTarget> formTargets;

	public Metadata() {
	}

	public Metadata(final List<HealthFacility> healthFacilities, final List<Career> careers,
			final List<FormQuestion> formQuestions, final List<Tutored> tutoreds, final List<Cabinet> cabinets,
			final List<Setting> settings, final List<FormTarget> formTargets) {
		this.healthFacilities = healthFacilities;
		this.careers = careers;
		this.formQuestions = formQuestions;
		this.tutoreds = tutoreds;
		this.cabinets = cabinets;
		this.settings = settings;
		this.formTargets = formTargets;
	}

	public Metadata(List<Cabinet> cabinets) {
		this.cabinets = cabinets;
	}

	public List<HealthFacility> getHealthFacilities() {
		return this.healthFacilities;
	}

	public List<Career> getCareers() {
		return this.careers;
	}

	public List<FormQuestion> getFormQuestion() {
		return this.formQuestions;
	}

	public List<Tutored> getTutored() {
		return this.tutoreds;
	}

	public List<Cabinet> getCabinets() {
		return this.cabinets;
	}

	public List<Setting> getSettings() {
		return settings;
	}

	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}

	public List<FormTarget> getFormTargets() {
		return this.formTargets;
	}
}
