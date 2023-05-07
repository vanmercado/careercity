package com.telusinternational.careercity.enums;

public enum EducationalBackground {

    COLLEGE_1("At least 1 year in college"), COLLEGE_2("At least 2 years in college"),
    VOCATIONAL("Vocational course graduate"), COLLEGE_GRAD("College graduate of any 4-year");

    private String description;

    EducationalBackground(String description) {

        this.description = description;

    }

    public String getDescription() {

        return description;

    }

}
