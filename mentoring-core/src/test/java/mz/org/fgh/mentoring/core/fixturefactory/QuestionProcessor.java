/**
 *
 */
package mz.org.fgh.mentoring.core.fixturefactory;

import br.com.six2six.fixturefactory.processor.Processor;
import mz.co.mozview.frameworks.core.util.UuidFactory;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.question.model.Question;

/**
 * @author Stélio Moiane
 *
 */
public class QuestionProcessor implements Processor {

	@Override
	public void execute(final Object object) {

		if (object instanceof FormQuestion) {
			final FormQuestion formQuestion = (FormQuestion) object;
			formQuestion.getQuestion().setQuestion(formQuestion.getQuestion().getQuestion() + UuidFactory.generate());
			return;
		}

		final Question question = (Question) object;
		question.setQuestion(question.getQuestion() + UuidFactory.generate());
	}
}
