package mz.org.fgh.mentoring.integ.resources.form;

import mz.org.fgh.mentoring.core.question.model.Question;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Willa aka Baba Imu on 3/9/18.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionSequence {
    private Integer sequence;
    private Question question;

    public Integer getSequence() {
        return sequence;
    }

    public Question getQuestion() {
        return question;
    }
}
