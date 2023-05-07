package com.careercitydashboard.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ft_position_answers")
public class PositionAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer QUESTION_ANSWER_ID;

    private Integer POSITION_ID;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "QUESTION_ID")
    private Questions questions;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    public Questions getQuestions() {

        return questions;

    }

    public void setQuestions(Questions questions) {

        this.questions = questions;

    }

    public Answer getAnswer() {

        return answer;

    }

    public void setAnswer(Answer answer) {

        this.answer = answer;

    }

    public Integer getQUESTION_ANSWER_ID() {

        return QUESTION_ANSWER_ID;

    }

    public void setQUESTION_ANSWER_ID(Integer qUESTION_ANSWER_ID) {

        QUESTION_ANSWER_ID = qUESTION_ANSWER_ID;

    }

    public Integer getPOSITION_ID() {

        return POSITION_ID;

    }

    public void setPOSITION_ID(Integer pOSITION_ID) {

        POSITION_ID = pOSITION_ID;

    }

    @Override
    public String toString() {

        return "PositionAnswers [QUESTION_ANSWER_ID=" + QUESTION_ANSWER_ID + ", POSITION_ID=" + POSITION_ID
                + ", questions=" + questions + ", answer=" + answer + "]";

    }

}
