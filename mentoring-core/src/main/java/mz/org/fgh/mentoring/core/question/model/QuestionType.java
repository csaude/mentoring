/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.question.model;

import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.model.BooleanAnswer;
import mz.org.fgh.mentoring.core.answer.model.TextAnswer;

/**
 * @author Stélio Moiane
 *
 */
public enum QuestionType {

	TEXT(new TextAnswer()),

	BOOLEAN(new BooleanAnswer()),

	NUMERIC(null),

	DECIMAL(null),

	CURRENCY(null);

	private final Answer answer;

	private QuestionType(final Answer answer) {
		this.answer = answer;
	}

	public Answer getAnswer() {
		return this.answer;
	}
}
