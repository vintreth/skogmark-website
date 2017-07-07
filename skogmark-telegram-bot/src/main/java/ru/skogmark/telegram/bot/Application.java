package ru.skogmark.telegram.bot;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.message.config.AuthConfigFactory;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
        SpringApplication.run(Application.class, args);
    }
}