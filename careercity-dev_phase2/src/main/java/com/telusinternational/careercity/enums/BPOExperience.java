package com.telusinternational.careercity.enums;

public enum BPOExperience {

    BPO_EXP_1("Less than 6 months"), BPO_EXP_2("6 months to less than a year"), BPO_EXP_3("1 to 2 years"),
    BPO_EXP_4("More than 3 years");

    private String experience;

    BPOExperience(String experience) {

        this.experience = experience;

    }

    public String getFieldName() {

        return experience;

    }

}
