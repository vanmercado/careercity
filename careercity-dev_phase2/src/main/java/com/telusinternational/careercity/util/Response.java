package com.telusinternational.careercity.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class Response {

    private HashMap<Object, Object> map;

    public HashMap<Object, Object> success() {

        map = new HashMap<Object, Object>();

        map.put("success", true);
        map.put("message", "Request succesful");

        return map;

    }

    public HashMap<Object, Object> fail() {

        map = new HashMap<Object, Object>();

        map.put("success", false);
        map.put("message", "Request failed");

        return map;

    }

    public HashMap<Object, Object> success(String message) {

        map = new HashMap<Object, Object>();

        map.put("success", true);
        map.put("message", message);

        return map;

    }

    public HashMap<Object, Object> fail(String message) {

        map = new HashMap<Object, Object>();

        map.put("success", false);
        map.put("message", message);

        return map;

    }

    public HashMap<Object, Object> success(Throwable e) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String sStackTrace = sw.toString(); // stack trace as a string

        map = new HashMap<Object, Object>();

        map.put("success", true);
        map.put("message", sStackTrace);

        return map;

    }

    public HashMap<Object, Object> fail(Throwable e) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String sStackTrace = sw.toString(); // stack trace as a string

        map = new HashMap<Object, Object>();

        map.put("success", false);
        map.put("message", sStackTrace);

        return map;

    }

}
