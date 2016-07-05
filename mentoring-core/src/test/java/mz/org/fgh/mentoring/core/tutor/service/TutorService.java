/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutor.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
public interface TutorService {

	String NAME = "mz.org.fgh.mentoring.core.tutor.service.TutorService";

	Tutor createTutor(final UserContext userContext, final Tutor tutor) throws BusinessException;

}
