package com.telusinternational.careercity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer QUESTION_ID;

    private String QUESTION;

    public Integer getQUESTION_ID() {

        return QUESTION_ID;

    }

    public void setQUESTION_ID(Integer qUESTION_ID) {

        QUESTION_ID = qUESTION_ID;

    }

    public String getQUESTION() {

        return QUESTION;

    }

    public void setQUESTION(String qUESTION) {

        QUESTION = qUESTION;

    }

    @Override
    public String toString() {

        return "Question [QUESTION_ID=" + QUESTION_ID + ", QUESTION=" + QUESTION + "]";

    }

}
