package testrunners;

import config.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PremiumPage;
import utils.Utils;

import java.util.List;


public class PremiumPageTestRunner extends Setup {
    @FindBy(className = "content-list-btn")
    List<WebElement> premiumPackButtons;
    PremiumPage premiumPage;
    @Test(priority = 1,description="can  log in with valid number and OTP")
    public void premiumPackPage() throws InterruptedException {
        premiumPage = new PremiumPage(driver);
        String actual=premiumPage.loginAndPremiumPage();
        String expected= "BL promotion MSG - only for test pack";
        Assert.assertEquals(actual,expected);
    }
    @Test(priority = 2,description="can  log in with postpaid number and OTP")
    public void lastPremiumPackPage() throws InterruptedException {
        premiumPage = new PremiumPage(driver);
        String actual = premiumPage.lastPacks();
        String expected = "test SSL ND ATMN";
        Assert.assertEquals(actual,expected);

    }
}
