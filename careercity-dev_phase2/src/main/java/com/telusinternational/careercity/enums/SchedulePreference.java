package com.telusinternational.careercity.enums;

public enum SchedulePreference {

    DAY("Morning / Mid Shift"), MID("Mid Shift / Graveyard Shift"), NIGHT("Graveyard / Morning Shift"),
    NO_WEEKENDS("Weekends off"), ANY("Any");

    private String schedule;

    SchedulePreference(String schedule) {

        this.schedule = schedule;

    }

    public String getSchdule() {

        return schedule;

    }

}
