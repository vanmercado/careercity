package com.careercitydashboard.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ANSWER_ID;

    private Integer ITEM_NUMBER;

    private Integer QUESTION_ID;

    private String ANSWER;

    public Integer getANSWER_ID() {

        return ANSWER_ID;

    }

    public void setANSWER_ID(Integer aNSWER_ID) {

        ANSWER_ID = aNSWER_ID;

    }

    public Integer getITEM_NUMBER() {

        return ITEM_NUMBER;

    }

    public void setITEM_NUMBER(Integer iTEM_NUMBER) {

        ITEM_NUMBER = iTEM_NUMBER;

    }

    public Integer getQUESTION_ID() {

        return QUESTION_ID;

    }

    public void setQUESTION_ID(Integer qUESTION_ID) {

        QUESTION_ID = qUESTION_ID;

    }

    public String getANSWER() {

        return ANSWER;

    }

    public void setANSWER(String aNSWER) {

        ANSWER = aNSWER;

    }

    public Answer() {

        super();

    }

    @Override
    public String toString() {

        return "Answer [ANSWER_ID=" + ANSWER_ID + ", ITEM_NUMBER=" + ITEM_NUMBER + ", QUESTION_ID=" + QUESTION_ID
                + ", ANSWER=" + ANSWER + "]";

    }

}
