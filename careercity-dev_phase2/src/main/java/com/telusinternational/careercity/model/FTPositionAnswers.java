package com.telusinternational.careercity.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ft_position_answers")
public class FTPositionAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer QUESTION_ANSWER_ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSITION_ID")
    private Position position;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    public Integer getQUESTION_ANSWER_ID() {

        return QUESTION_ANSWER_ID;

    }

    public void setQUESTION_ANSWER_ID(Integer qUESTION_ANSWER_ID) {

        QUESTION_ANSWER_ID = qUESTION_ANSWER_ID;

    }

    public Position getPosition() {

        return position;

    }

    public void setPosition(Position account) {

        this.position = account;

    }

    public Question getQuestion() {

        return question;

    }

    public void setQuestion(Question question) {

        this.question = question;

    }

    public Answer getAnswer() {

        return answer;

    }

    public void setAnswer(Answer answer) {

        this.answer = answer;

    }

    @Override
    public String toString() {

        return "FTQuestionAnswers [QUESTION_ANSWER_ID=" + QUESTION_ANSWER_ID + ", question=" + question + ", answer="
                + answer + "]";

    }

}
