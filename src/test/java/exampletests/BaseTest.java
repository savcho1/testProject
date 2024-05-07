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
import pageobjects.BasePage;
import pageobjects.Page;

import java.io.FileInputStream;
import java.io.IOException;
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
            options.setBinary("/usr/local/bin/chromedriver");
            driver.set(new ChromeDriver(options));

            //WebDriverManager.chromedriver().setup();
            //driver.set(new ChromeDriver());
        }
        else if(browser.equals("ff")){
//            WebDriverManager.firefoxdriver().setup();
//            driver.set(new FirefoxDriver());
            FirefoxOptions options = new FirefoxOptions();
            System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
            driver.set(new FirefoxDriver(options));
        }
        else {
            System.out.println("No browsers set up");
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
