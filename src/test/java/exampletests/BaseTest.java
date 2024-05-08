package exampletests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.reporters.jq.Main;
import pageobjects.BasePage;
import pageobjects.Page;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {

    ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public Page page;
    static Properties properties = readPropertyFile();

    @BeforeMethod
    @Parameters(value= {"browser"})
    public void setUpBrowser(String browser){
        if(browser.equals("chrome")){
            ChromeOptions options = new ChromeOptions();
          //  options.setBinary("/usr/local/bin/chromedriver");
            options.setBinary("/src/maim/resources/chromedriver");
            driver.set(new ChromeDriver(options));
//            WebDriverManager.chromedriver().setup();
//            driver.set(new ChromeDriver());
        }
        else if(browser.equals("ff")){
//            WebDriverManager.firefoxdriver().setup();
//            driver.set(new FirefoxDriver());
            FirefoxOptions options = new FirefoxOptions();
            //options.setBinary("/usr/local/bin/geckodriver");
            options.setBinary("/src/maim/resources/geckodriver");
            driver.set(new FirefoxDriver(options));
        }
        else {
            System.out.println("No browsers to set up");
        }

        driver.get().navigate().to(properties.getProperty("baseUrl"));

        page = new BasePage(driver.get());

    }

    @AfterMethod
    public void tearDown(){
        driver.get().quit();
    }

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
