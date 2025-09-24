package org.example.utils;

import org.example.config.ApplicationConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public final class PropertyUtils {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertyUtils() {
    }

    private static void loadProperties() {
        try (var inputStream = PropertyUtils.class.getClassLoader()
                .getResourceAsStream(ApplicationConfig.PROPERTY_FILE_NAME);
        ) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

}
