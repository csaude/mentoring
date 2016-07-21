/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutor.service;

import java.util.List;

import javax.inject.Inject;

import mz.org.fgh.mentoring.core.tutor.dao.TutorDAO;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

import org.springframework.stereotype.Service;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(TutorQueryService.NAME)
public class TutorQueryServiceImpl implements TutorQueryService {

	@Inject
	private TutorDAO tutorDAO;

	@Override
	public List<Tutor> findAll() {
		return tutorDAO.findAll();
	}

}
