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

	TEXT {
		@Override
		public Answer getAnswer() {
			return new TextAnswer();
		}
	},

	BOOLEAN {
		@Override
		public Answer getAnswer() {
			return new BooleanAnswer();
		}
	},

	NUMERIC {
		@Override
		public Answer getAnswer() {
			return null;
		}
	},

	DECIMAL {
		@Override
		public Answer getAnswer() {
			return null;
		}
	},

	CURRENCY {
		@Override
		public Answer getAnswer() {
			return null;
		}
	};

	public abstract Answer getAnswer();
}
