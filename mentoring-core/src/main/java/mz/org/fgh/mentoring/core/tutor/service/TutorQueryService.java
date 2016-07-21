/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutor.service;

import java.util.List;

import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface TutorQueryService {

	String NAME = "mz.org.fgh.mentoring.core.tutor.service.TutorQueryService";

	public List<Tutor> findAll();

}
