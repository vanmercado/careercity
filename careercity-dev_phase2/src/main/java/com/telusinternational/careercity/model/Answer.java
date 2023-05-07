package com.telusinternational.careercity.model;

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

    private Integer QUESTION_ID;

    private String ANSWER;

    public Integer getANSWER_ID() {

        return ANSWER_ID;

    }

    public void setANSWER_ID(Integer aNSWER_ID) {

        ANSWER_ID = aNSWER_ID;

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

    @Override
    public String toString() {

        return "Answer [ANSWER_ID=" + ANSWER_ID + ", QUESTION_ID=" + QUESTION_ID + ", ANSWER=" + ANSWER + "]";

    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((ANSWER == null) ? 0 : ANSWER.hashCode());
        return result;

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Answer other = (Answer) obj;

        if (ANSWER == null) {

            if (other.ANSWER != null)
                return false;

        }
        else if (!ANSWER.equals(other.ANSWER))
            return false;

        return true;

    }

}
