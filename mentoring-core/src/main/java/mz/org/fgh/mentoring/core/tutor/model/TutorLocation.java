/**
 *
 */
package mz.org.fgh.mentoring.core.tutor.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.tutor.dao.TutorDAO;
import mz.org.fgh.mentoring.core.tutor.dao.TutorLocationDAO;

/**
 * @author Stélio Moiane
 *
 */

@NamedQueries({ @NamedQuery(name = TutorLocationDAO.QUERY_NAME.findByTutorId, query = TutorLocationDAO.QUERY.findByTutorId) })
@Entity
@Table(name = "TUTOR_LOCATIONS")
public class TutorLocation extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TUTOR_ID", nullable = false)
	private Tutor tutor;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOCATION_ID", nullable = false)
	private HealthFacility location;

	public TutorLocation() {
	}

	public Tutor getTutor() {
		return this.tutor;
	}

	public void setTutor(final Tutor tutor) {
		this.tutor = tutor;
	}

	public HealthFacility getLocation() {
		return this.location;
	}

	public void setLocation(final HealthFacility location) {
		this.location = location;
	}
}
