package testrunners;

import config.Setup;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;

public class LoginTestRunner extends Setup {
    Login login;
    @Test(priority = 1,description="can  log in with valid number and OTP")
    public void onlyDoLogin() throws InterruptedException {
        login = new Login(driver);
        String actual=login.doLogin();
        String expected = "Sign Out";
        Assert.assertEquals(actual,expected);
        System.out.println();
    }
}
