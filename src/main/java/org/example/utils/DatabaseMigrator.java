package org.example.utils;

import org.example.config.ApplicationConfig;
import org.flywaydb.core.Flyway;

public class DatabaseMigrator {
    public void runMigrations() {
        try {
            Flyway flyway = Flyway.configure()
                    .dataSource(
                            PropertyUtils.getProperty(ApplicationConfig.DB_URL_PROP_KEY),
                            PropertyUtils.getProperty(ApplicationConfig.DB_USERNAME_PROP_KEY),
                            PropertyUtils.getProperty(ApplicationConfig.DB_PASSWORD_PROP_KEY)
                    )
                    .locations("classpath:db/migration")
                    .baselineOnMigrate(true)
                    .validateMigrationNaming(true)
                    .validateOnMigrate(true)
                    .load();

            flyway.migrate();

            System.out.println("Migrations complete");
        } catch (Exception e) {
            System.err.println("Migration error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
