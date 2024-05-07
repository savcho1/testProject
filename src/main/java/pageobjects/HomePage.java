package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
    private By header = By.id("private-page__title");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getHeader() {
        return getElement(header);
    }

    public String geHomeTitle(){
        return getPageTitle();
    }
}
