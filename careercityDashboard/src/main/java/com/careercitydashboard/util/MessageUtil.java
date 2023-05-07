package com.careercitydashboard.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:messages.properties")
public class MessageUtil {

    @Autowired
    private Environment env;

    public String getMessage(String key) {

        return env.getProperty(key);

    }

    public String getPersistenceErrorMessage() {

        return getMessage("error.persist");

    }

}
