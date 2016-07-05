package mz.org.fgh.mentoring.core.tutor.validator;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

import org.springframework.stereotype.Component;


@Component(TutorValidator.NAME)
public class TutorValidatorImpl implements TutorValidator {

	public void validateTutor(Tutor tutor) throws BusinessException{
	
		if(tutor.getName() ==  null){
			throw new BusinessException("O Nome do tutor nao pode ser nullo ");
		}
	}
}
