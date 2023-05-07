package com.telusinternational.careercity.enums;

public enum FieldOfStudy {

    ACC("Accounting / Finance"), BM("Business Management"), IT("Information Technology"), OTHERS("Others");

    private String field;

    FieldOfStudy(String field) {

        this.field = field;

    }

    public String getFieldName() {

        return field;

    }

}
