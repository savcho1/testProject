package utils;

import org.testng.reporters.jq.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UtilsHelper {

    public static Properties readPropertyFile(){
        Properties properties = new Properties();
        try {
            ClassLoader classLoader = Main.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("config.properties");

            // Load properties from the input stream
            properties.load(inputStream);

            // Get the value of the base URL property
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
