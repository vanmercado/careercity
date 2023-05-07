package com.telusinternational.careercity;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;*/
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
/*import org.springframework.data.jpa.repository.config.EnableJpaAuditing;*/
/*import org.springframework.scheduling.annotation.EnableScheduling;*/

@SpringBootApplication
@ComponentScan(basePackages = { "com.telusinternational.careercity" })
public class CareercityApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(CareercityApplication.class);

    }

    public static void main(String[] args) {

        SpringApplication.run(CareercityApplication.class, args);

    }

}
