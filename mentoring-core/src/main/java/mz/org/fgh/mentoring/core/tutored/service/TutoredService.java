/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutored.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface TutoredService {

	String NAME = "mz.org.fgh.mentoring.core.tutored.service.TutoredService";

	Tutored createTutored(final UserContext userContext, final Tutored tutored) throws BusinessException;

	Tutored updateTutored(final UserContext userContext, final Tutored tutored) throws BusinessException;
}
