package org.example.utils;

import org.example.config.ApplicationConfig;
import org.example.dao.LabelEntity;
import org.example.dao.PostEntity;
import org.example.dao.PostStatusEntity;
import org.example.dao.WriterEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashMap;

public class SessionManager {
    private static final SessionFactory sessionFactory = createSession();

    private SessionManager() {
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeConnection() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    private static SessionFactory createSession() {
        try {
            return new MetadataSources(
                    new StandardServiceRegistryBuilder()
                            .applySettings(createSettings())
                            .build())
                    .addAnnotatedClass(LabelEntity.class)
                    .addAnnotatedClass(PostEntity.class)
                    .addAnnotatedClass(WriterEntity.class)
                    .addAnnotatedClass(PostStatusEntity.class)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Error creating SessionFactory: " + e.getMessage());
        }

    }

    private static HashMap<String, Object> createSettings() {
        HashMap<String, Object> settings = new HashMap<>();
        settings.put("hibernate.connection.url", PropertyUtils.getProperty(ApplicationConfig.DB_URL_PROP_KEY));
        settings.put("hibernate.connection.username", PropertyUtils.getProperty(ApplicationConfig.DB_USERNAME_PROP_KEY));
        settings.put("hibernate.connection.password", PropertyUtils.getProperty(ApplicationConfig.DB_PASSWORD_PROP_KEY));
        settings.put("hibernate.transaction.jta.platform", "");
        settings.put("hibernate.transaction.coordinator_class", "jdbc");
        return settings;
    }
}
