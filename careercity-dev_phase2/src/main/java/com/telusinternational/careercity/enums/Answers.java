package com.telusinternational.careercity.enums;

import java.nio.ByteBuffer;

public enum Answers {

    PROD_TECH_ANALYST(
            "010001111001000000000000000000000000000000000000000000000000011010001100000000000000000000000000000000000000000000000");

    public byte[] answerBytes = new byte[117];

    Answers(String answerBytesAsString) {

        long answersAsLong = Long.parseLong(answerBytesAsString);
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(answersAsLong);

        this.answerBytes = buffer.array();

    }

    public byte[] getAnswerBytes() {

        return this.answerBytes;

    }

}
