package ui.exampletests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pageobjects.HomePage;
import ui.pageobjects.LoginPage;

public class LoginTest extends BaseUiTest {

    @Test
    public void verifyLoginPageTitleTest(){
        String title = page.getInstance(LoginPage.class).getLoginPageTitle();
        Assert.assertEquals(title, "HubSpot Login and Sign in");
        logger.info("Hello World!");
    }

    @Test
    public void verifyLoginTest() throws InterruptedException {
        HomePage homePage = page.getInstance(LoginPage.class).doLogin("regela7177@agafx.com", "A1234wsxedcrfv");
        Assert.assertEquals(homePage.geHomeTitle(), "HubSpot Login and Sign in");
    }
}
