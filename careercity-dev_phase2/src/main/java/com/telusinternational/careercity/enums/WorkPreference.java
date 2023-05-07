package com.telusinternational.careercity.enums;

public enum WorkPreference {

    CS("Customer Service"), TECH_SUPP("Technical Support"), BACK_OFFICE("Back Office"), IT("Information Technology"),
    SUPPORT("Support Function");

    private String work;

    WorkPreference(String work) {

        this.work = work;

    }

    public String getWorkName() {

        return this.work;

    }

}
