package com.careercitydashboard.util;

import org.springframework.beans.factory.annotation.Value;

public class SqlPrintOut {

    @Value("${spring.queries.user-query}")
    private static String userRole;

    public static void main(String[] args) {

        System.out.println(userRole);

    }

}
