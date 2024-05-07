package owntests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    static Properties properties;

    public static Properties readPropertyFile(){
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("/Users/yuliyafedko/Documents/Savchenko/testProject/src/main/resources/config.properties");
            properties.load(fileInputStream);
            fileInputStream.close();

            // Get the value of the base URL property
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
