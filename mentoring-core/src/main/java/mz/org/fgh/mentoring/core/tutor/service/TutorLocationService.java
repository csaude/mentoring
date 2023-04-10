/**
 *
 */
package mz.org.fgh.mentoring.core.tutor.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.model.TutorLocation;

/**
 * @author Stélio Moiane
 *
 */
public interface TutorLocationService {

	List<TutorLocation> allocateTutorLocations(UserContext userContext, Tutor tutor, List<HealthFacility> locations) throws BusinessException;

	List<TutorLocation> findByTutorId(Long id)  throws BusinessException;

}
