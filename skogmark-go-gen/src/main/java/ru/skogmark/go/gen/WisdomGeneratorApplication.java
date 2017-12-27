package ru.skogmark.go.gen;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

import javax.security.auth.message.config.AuthConfigFactory;

//todo make security
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class WisdomGeneratorApplication {
    private static final Logger log = LoggerFactory.getLogger(WisdomGeneratorApplication.class);

    public static void main(String[] args) {
        try {
            if (AuthConfigFactory.getFactory() == null) {
                AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
            }
            SpringApplication.run(WisdomGeneratorApplication.class, args);
        } catch (Exception e) {
            log.error("Exception caught during application runtime", e);
            throw e;
        }
    }
}
