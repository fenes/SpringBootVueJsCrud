package com.feritenesguzel.contacts;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@EnableCaching
@SpringBootApplication
public class ContactApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactApplication.class, args);
    }


}
