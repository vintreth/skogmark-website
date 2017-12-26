package ru.skogmark.go.gen;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import ru.skogmark.go.gen.core.migration.MigrationManager;

import javax.security.auth.message.config.AuthConfigFactory;

import static java.util.Objects.requireNonNull;

//todo make security
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class WisdomGeneratorApplication {
    private static final Logger log = LoggerFactory.getLogger(WisdomGeneratorApplication.class);

    public static void main(String[] args) {
        try {
            if (AuthConfigFactory.getFactory() == null) {
                AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
            }
            ApplicationContext applicationContext = SpringApplication.run(WisdomGeneratorApplication.class, args);
            MigrationManager migrationManager = applicationContext.getBean(MigrationManager.class);
            requireNonNull(migrationManager, "MigrationManager is missed");
            migrationManager.applyMigrations();
        } catch (Exception e) {
            log.error("Exception caught during application runtime", e);
            throw e;
        }
    }
}
