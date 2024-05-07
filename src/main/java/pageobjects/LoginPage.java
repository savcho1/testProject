package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //page locators

    private By emailId = By.id("username");
    private By password = By.id("password");
    private By loginButton = By.id("loginBtn");

    //getters
    public WebElement getPassword() {
        return getElement(password);
    }

    public WebElement getEmailId() {
        return getElement(emailId);
    }

    public WebElement getLoginButton() {
        return getElement(loginButton);
    }

    public String getLoginPageTitle(){
        return getPageTitle();
    }

    public HomePage doLogin(String email, String password){
        getEmailId().sendKeys(email);
        getPassword().sendKeys(password);
        getLoginButton().click();
        return getInstance(HomePage.class);
    }
}
