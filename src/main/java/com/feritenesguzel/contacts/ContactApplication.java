package com.feritenesguzel.contacts;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class ContactApplication {
    private static final Logger logger = LoggerFactory.getLogger(ContactApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ContactApplication.class, args);
    }


}
