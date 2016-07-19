/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutor;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
@Service(TutorResource.NAME)
@Path("tutors")
public class TutorResourceImpl implements TutorResource {

	@Override
	public JResponse<Tutor> createTutor(final Tutor tutor) throws BusinessException {

		// persintence logic

		return JResponse.ok(tutor).build();
	}

}
