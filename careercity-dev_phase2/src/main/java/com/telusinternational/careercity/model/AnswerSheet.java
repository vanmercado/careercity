package com.telusinternational.careercity.model;

import java.util.List;

/*
 * 
 * Non-persistent model
 * 
 */

public class AnswerSheet {

    private List<Answer> q1; // Preferred site location (max 2)

    private List<Answer> q2; // Preferred Type of Work (CCO)

    private List<Answer> q3; // Preferred Type of Work (Digital Service)

    private List<Answer> q4; // Preferred Type of Work (Support)

    private List<Answer> q5; // Preferred Work Schedule (max 2)

    private List<Answer> q6; // Educational Background

    private List<Answer> q7; // Field of Study

    private List<Answer> q8; // Years of BPO Experience

    private List<Answer> q9; // Type of BPO Experience

    private List<Answer> q10; // Additional Skills (General)

    private List<Answer> q11; // Additional Skills (Basic Troubleshooting)

    private List<Answer> q12; // Additional Skills (Information Technology)

    public List<Answer> getQ1() {

        return q1;

    }

    public void setQ1(List<Answer> q1) {

        this.q1 = q1;

    }

    public List<Answer> getQ2() {

        return q2;

    }

    public void setQ2(List<Answer> q2) {

        this.q2 = q2;

    }

    public List<Answer> getQ3() {

        return q3;

    }

    public void setQ3(List<Answer> q3) {

        this.q3 = q3;

    }

    public List<Answer> getQ4() {

        return q4;

    }

    public void setQ4(List<Answer> q4) {

        this.q4 = q4;

    }

    public List<Answer> getQ5() {

        return q5;

    }

    public void setQ5(List<Answer> q5) {

        this.q5 = q5;

    }

    public List<Answer> getQ6() {

        return q6;

    }

    public void setQ6(List<Answer> q6) {

        this.q6 = q6;

    }

    public List<Answer> getQ7() {

        return q7;

    }

    public void setQ7(List<Answer> q7) {

        this.q7 = q7;

    }

    public List<Answer> getQ8() {

        return q8;

    }

    public void setQ8(List<Answer> q8) {

        this.q8 = q8;

    }

    public List<Answer> getQ9() {

        return q9;

    }

    public void setQ9(List<Answer> q9) {

        this.q9 = q9;

    }

    public List<Answer> getQ10() {

        return q10;

    }

    public void setQ10(List<Answer> q10) {

        this.q10 = q10;

    }

    public List<Answer> getQ11() {

        return q11;

    }

    public void setQ11(List<Answer> q11) {

        this.q11 = q11;

    }

    public List<Answer> getQ12() {

        return q12;

    }

    public void setQ12(List<Answer> q12) {

        this.q12 = q12;

    }

    @Override
    public String toString() {

        return "AnswerSheet [q1=" + q1 + ", q2=" + q2 + ", q3=" + q3 + ", q4=" + q4 + ", q5=" + q5 + ", q6=" + q6
                + ", q7=" + q7 + ", q8=" + q8 + ", q9=" + q9 + ", q10=" + q10 + ", q11=" + q11 + ", q12=" + q12
                + ", getQ1()=" + getQ1() + ", getQ2()=" + getQ2() + ", getQ3()=" + getQ3() + ", getQ4()=" + getQ4()
                + ", getQ5()=" + getQ5() + ", getQ6()=" + getQ6() + ", getQ7()=" + getQ7() + ", getQ8()=" + getQ8()
                + ", getQ9()=" + getQ9() + ", getQ10()=" + getQ10() + ", getQ11()=" + getQ11() + ", getQ12()="
                + getQ12() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";

    }

}