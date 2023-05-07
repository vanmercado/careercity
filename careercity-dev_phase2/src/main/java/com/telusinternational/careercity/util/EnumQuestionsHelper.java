package com.telusinternational.careercity.util;

import java.nio.ByteBuffer;

public class EnumQuestionsHelper {

    public static byte[] answerStringToByte(String answer) {

        long answersAsLong = Long.parseLong(answer);
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(answersAsLong);

        return buffer.array();

    }

}
