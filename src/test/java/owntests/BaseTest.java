package owntests;

import org.testng.annotations.BeforeMethod;

import java.util.Properties;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest extends BaseClass {

    static Properties properties = readPropertyFile();

   @BeforeMethod
    public void openPage() {
        System.out.println("before");
        open(properties.getProperty("baseUrl"));
    }
}
