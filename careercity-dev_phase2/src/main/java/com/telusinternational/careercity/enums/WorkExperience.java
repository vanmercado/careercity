package com.telusinternational.careercity.enums;

public enum WorkExperience {

    // BPO
    CS_VOICE("Customer service (Voice)", "BPO"), CS_NON_VOICE("Customer service (Non-voice)", "BPO"),
    UPSELLING("Inbound sales/Upselling", "BPO"), OUTBOUND_SALES("Outbound sales", "BPO"),
    TECH_SUPP("Technical support", "BPO"), LEAD_GEN("Lead generation", "BPO"), FINANCIAL("Financial account", "BPO"),
    BACK_OFFICE("Back Office", "BPO"), OMNI("Omnichannel", "BPO"),
    // Other
    IT("Information Technology", "Other"), HR("Human Resources / Administration", "Other"),
    ACCOUNTING("Accounting / Finance / Banking", "Other"), HOSPITALITY("Hospitality / Food Service", "Other"),
    TELECOM("Telecommunication", "Other");

    private String job;
    private String category;

    WorkExperience(String job, String category) {

        this.job = job;
        this.category = category;

    }

    public String getJobName() {

        return this.job;

    }

    public String getCategory() {

        return this.category;

    }

}
