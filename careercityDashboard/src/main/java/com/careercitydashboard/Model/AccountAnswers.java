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
@Table(name = "ft_account_answers")
public class AccountAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer QUESTION_ANSWER_ID;

    private Integer ACCOUNT_ID;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "QUESTION_ID")
    private Questions question;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    public Integer getACCOUNT_ID() {

        return ACCOUNT_ID;

    }

    public void setACCOUNT_ID(Integer aCCOUNT_ID) {

        ACCOUNT_ID = aCCOUNT_ID;

    }

    public Integer getQUESTION_ANSWER_ID() {

        return QUESTION_ANSWER_ID;

    }

    public void setQUESTION_ANSWER_ID(Integer qUESTION_ANSWER_ID) {

        QUESTION_ANSWER_ID = qUESTION_ANSWER_ID;

    }

    public Answer getAnswer() {

        return answer;

    }

    public void setAnswer(Answer answer) {

        this.answer = answer;

    }

    public Questions getQuestion() {

        return question;

    }

    public void setQuestion(Questions question) {

        this.question = question;

    }

    @Override
    public String toString() {

        return "AccountAnswers [QUESTION_ANSWER_ID=" + QUESTION_ANSWER_ID + ", ACCOUNT_ID=" + ACCOUNT_ID + ", question="
                + question + ", answer=" + answer + "]";

    }

}