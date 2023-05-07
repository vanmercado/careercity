package com.telusinternational.careercity.enums;

import com.telusinternational.careercity.util.EnumQuestionsHelper;

public enum LocationPreference {

    ARANETA("Araneta", "10000"), DISCOVERY("Discovery", "01000"), MARKET_MARKET("Market Market", "00100"),
    MCKINLEY_X("McKinley Exchange", "00010"), MCKINLEY_W("McKinley West", "00001");

    private String locationName;
    private String answer;
    private byte[] answerAsBytes;

    LocationPreference(String locationName, String answer) {

        this.locationName = locationName;
        this.answer = answer;
        this.answerAsBytes = EnumQuestionsHelper.answerStringToByte(answer);

    }

    public String getLocationName() {

        return locationName;

    }

    public String getAnswer() {

        return this.answer;

    }

    public byte[] getAnswerAsBytes() {

        return this.answerAsBytes;

    }

}
