package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new RuntimeException("config.properties not found in resources");
            }
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static String getBaseUrl() {
        return props.getProperty("base.url", "https://demoqa.com/");
    }

    public static String getBrowser() {
        return props.getProperty("browser", "chrome");
    }

    public static int getTimeout() {
        return Integer.parseInt(props.getProperty("timeout", "10"));
    }
}
