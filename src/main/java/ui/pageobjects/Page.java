package ui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class Page {
    WebDriver driver;
    WebDriverWait wait;

    public Page(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofMillis(15000));
    }

    //abstraction methods
    public abstract String getPageTitle();

    public abstract String getPageHeader(By locator);

    public abstract WebElement getElement(By locator);
    public abstract void waitForElementPresent(By locator);
    public abstract void waitForTitle(String title);

    private Map<Class<? extends BasePage>, BasePage> pageInstances = new HashMap<>();

    public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
        BasePage existingInstance = pageInstances.get(pageClass);
        if (existingInstance != null) {
            return pageClass.cast(existingInstance);
        } else {
            try {
                TPage newInstance = pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
                pageInstances.put(pageClass, newInstance);
                return newInstance;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
