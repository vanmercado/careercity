package com.careercitydashboard.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static void main(String[] args) {

        String password = "qwerty1234";
        BCryptPasswordEncoder passwordEncode = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncode.encode(password);

        System.out.println(hashedPassword);

    }

}
