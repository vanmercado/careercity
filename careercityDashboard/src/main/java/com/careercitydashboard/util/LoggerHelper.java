package com.careercitydashboard.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggerHelper {

    public static void main(String[] args) {

        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userLogs = "logging test user:martin";
            String user = "martin";
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://35.197.83.15:3306/careercity?useSSL=false";
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, "wakanda", "w4k4nd4");

            String query = " insert into history_logs (log_msg)" + " values (?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, userLogs);

            ps.execute();

            con.close();

        }
        catch (Exception e) {

            e.printStackTrace();

        }

    }

};