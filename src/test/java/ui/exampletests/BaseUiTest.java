package ui.exampletests;

import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.WebEventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import ui.pageobjects.BasePage;
import ui.pageobjects.Page;
import org.openqa.selenium.support.events.EventFiringDecorator;
import utils.UtilsHelper;

import java.util.Properties;

public class BaseUiTest extends BaseTest{

    ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public Page page;
    protected final Logger logger = LogManager.getLogger(getClass());

    @BeforeMethod
    @Parameters(value= {"browser"})
    public void setUpBrowser(String browser){
        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        }
        else if(browser.equals("ff")){
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        }
        else {
            System.out.println("No browsers to set up");
        }

        WebEventListener webEventListener = new WebEventListener();
        EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<>(webEventListener);
        driver.set(decorator.decorate(driver.get()));

        driver.get().navigate().to(properties.getProperty("baseUrl"));

        page = new BasePage(driver.get());

    }

    @AfterMethod
    public void tearDown(){
        driver.get().quit();
    }

}
