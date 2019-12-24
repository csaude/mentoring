/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.mentorship.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.location.model.Cabinet;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.util.LocalDateAdapter;
import mz.org.fgh.mentoring.integ.resources.mentorship.MentorshipHelper;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SessionDTO {

	private Session session;

	private List<MentorshipHelper> mentorships;

	private String districtUuid;

	private String healthFacilityUuid;

	private String formUuid;

	private String programmaticAreaUuid;

	private String startDate;

	private String endDate;

	private LocalDateAdapter localDateAdapter;

	private String tutorUuid;

	private String cabinetUuid;

	public SessionDTO() {
	}

	public SessionDTO(final String districtUuid, final String healthFacilityUuid, final String formUuid,
	        final String programmaticAreaUuid, final String tutorUuid, final String cabinetUuid, final String startDate,
	        final String endDate) {

		this.districtUuid = districtUuid;
		this.healthFacilityUuid = healthFacilityUuid;
		this.formUuid = formUuid;
		this.programmaticAreaUuid = programmaticAreaUuid;
		this.tutorUuid = tutorUuid;
		this.cabinetUuid = cabinetUuid;
		this.startDate = startDate;
		this.endDate = endDate;

		this.localDateAdapter = new LocalDateAdapter();
	}

	public SessionDTO(final String tutorUuid, final String formUuid, final String startDate, final String endDate) {
		this.tutorUuid = tutorUuid;
		this.formUuid = formUuid;
		this.startDate = startDate;
		this.endDate = endDate;

		this.localDateAdapter = new LocalDateAdapter();
	}
	
	public SessionDTO(final String tutorUuid, final String startDate, final String endDate) {
        this.tutorUuid = tutorUuid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.localDateAdapter = new LocalDateAdapter();
    }

	public SessionDTO(final String startDate, final String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		
		this.localDateAdapter = new LocalDateAdapter();
	}

	public Session getSession() {
		return this.session;
	}

	public List<MentorshipHelper> getMentorships() {
		return Collections.unmodifiableList(this.mentorships);
	}

	public void setSession(final Session session) {
		this.session = session;
	}

	public void addMentorships(final MentorshipHelper mentorshipHelper) {

		if (this.mentorships == null) {
			this.mentorships = new ArrayList<>();
		}

		this.mentorships.add(mentorshipHelper);
	}

	public District getDistrict() {

		if (this.districtUuid == null) {
			return null;
		}

		final District district = new District();
		district.setUuid(this.districtUuid);
		return district;
	}

	public HealthFacility getHealthFacility() {

		if (this.healthFacilityUuid == null) {
			return null;
		}

		final HealthFacility healthFacility = new HealthFacility();
		healthFacility.setUuid(this.healthFacilityUuid);
		return healthFacility;
	}

	public Form getForm() {

		if (this.formUuid == null) {
			return null;
		}

		final Form form = new Form();
		form.setUuid(this.formUuid);
		return form;
	}

	public ProgrammaticArea getProgrammaticArea() {

		if (this.programmaticAreaUuid == null) {
			return null;
		}

		final ProgrammaticArea programmaticArea = new ProgrammaticArea();
		programmaticArea.setUuid(this.programmaticAreaUuid);
		return programmaticArea;
	}

	public LocalDate getStartDate() {

		if (this.startDate == null) {
			return null;
		}

		return this.localDateAdapter.unmarshal(this.startDate);
	}

	public LocalDate getEndDate() {

		if (this.endDate == null) {
			return null;
		}

		return this.localDateAdapter.unmarshal(this.endDate);
	}

	public Tutor getTutor() {

		if (this.tutorUuid == null) {
			return null;
		}

		final Tutor tutor = new Tutor();
		tutor.setUuid(this.tutorUuid);

		return tutor;
	}

	public Cabinet getCabinet() {

		if (this.cabinetUuid == null) {
			return null;
		}

		final Cabinet cabinet = new Cabinet();
		cabinet.setUuid(this.cabinetUuid);

		return cabinet;
	}
}
