/**
 *
 */
package mz.org.fgh.mentoring.core.form.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.form.model.FormTarget;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
public interface FormTargetQueryService {

	List<FormTarget> findFormTargetByTutor(final Tutor tutor) throws BusinessException;
}
