package com.telusinternational.careercity.enums;

public enum QuestionIDs {

    LOCATION(1), EDUC_BG(2), BPO_EXP(3), OTHER_WORK_EXP(4), PREFERRED_TYPE_OF_WORK(5), PREFERRED_SCHED(6),
    FIELD_OF_STUDY(7), TIP_CERTS(8), IT_CERTS(9), BPO_EXP_YRS(10);

    private Integer id;

    QuestionIDs(Integer id) {

        this.id = id;

    }

    public Integer getId() {

        return this.id;

    }

    public static Integer getIdByName(String enumName) {

        return QuestionIDs.valueOf(enumName).getId();

    }

}
