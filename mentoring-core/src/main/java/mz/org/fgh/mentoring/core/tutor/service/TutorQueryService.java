/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutor.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface TutorQueryService {

	String NAME = "mz.org.fgh.mentoring.core.tutor.service.TutorQueryService";

	List<Tutor> findAllTutors(final UserContext userContext) throws BusinessException;

}
