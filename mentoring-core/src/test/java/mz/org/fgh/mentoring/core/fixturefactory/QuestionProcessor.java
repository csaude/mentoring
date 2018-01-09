/**
 *
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.processor.Processor;
import mz.co.mozview.frameworks.core.util.UuidFactory;
import mz.org.fgh.mentoring.core.question.model.Question;

/**
 * @author Stélio Moiane
 *
 */
public class QuestionProcessor implements Processor {

	@Override
	public void execute(final Object object) {
		final Question question = (Question) object;
		question.setQuestion(question.getQuestion() + UuidFactory.generate());
	}
}
