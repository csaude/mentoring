package mz.org.fgh.mentoring.core.tutor.validator;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

public interface TutorValidator {

	public final String NAME = "mz.org.fgh.mentoring.core.tutor.validator.TutorValidator";
	public void validateTutor(Tutor tutor) throws BusinessException;
}
