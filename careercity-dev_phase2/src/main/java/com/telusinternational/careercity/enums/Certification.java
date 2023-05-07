package com.telusinternational.careercity.enums;

public enum Certification {

    // TIP
    IASP("iAspire", "TIP"), LEAN_SIX("Lean Six Sigma YB", "TIP"),
    // IT
    ITIL("ITIL", "IT");

    private String name;
    private String type;

    Certification(String name, String type) {

        this.name = name;
        this.type = type;

    }

    public String getJobName() {

        return this.name;

    }

    public String getCategory() {

        return this.type;

    }

}
