package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.util.List;

public class PremiumPage {
    public WebDriver driver;

    @FindBy(className = "dropdown")
    WebElement dropDown;

    @FindBy(className = "dropdown-item")
    List<WebElement> dropDownMenus;

    @FindBy(className = "form-control")
    List<WebElement> phoneNumberPopBox;

    @FindBy(className = "verifyBt")
    List<WebElement> getOtpButton;

    @FindBy(className = "verifySubmitButton")
    WebElement verifyButton;

    @FindBy(className = "premium-content")
    List<WebElement> premiumPackTitle;

    @FindBy(className = "content-list-btn")
    List<WebElement> premiumPackButtons;

    @FindBy(className = "modal-title")
    List<WebElement> belowChoosePaymentMethod;

    public PremiumPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String loginAndPremiumPage() throws InterruptedException {
        dropDown.click();
        Thread.sleep(1000);
        dropDownMenus.get(5).click();
        Thread.sleep(1000);
        phoneNumberPopBox.get(2).sendKeys("01958160967");
        Thread.sleep(1000);
        getOtpButton.get(1).click();
        Thread.sleep(15000);
        verifyButton.click();
        Thread.sleep(4000);
        dropDown.click();
        dropDownMenus.get(2).click();
        Thread.sleep(7000);

        Utils.scrollToElementWithTimeout(driver, premiumPackButtons, 10);
        Utils.waitForElementToBeClickable(driver, premiumPackButtons.get(7), 10);

        Thread.sleep(500);
        premiumPackTitle.get(7).click();
        Thread.sleep(3000);
        return belowChoosePaymentMethod.get(5).getText();
    }

    public String lastPacks() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElements(By.className("premium-btn-close")).get(0).click();
        dropDown.click();
        dropDownMenus.get(2).click();

        Thread.sleep(3000);
        Utils.scrollToElementWithTimeout(driver, premiumPackButtons, 10);
        Utils.waitForElementToBeClickable(driver, premiumPackButtons.get(7), 10);
        Thread.sleep(500);
        premiumPackTitle.get(7).click();
        Thread.sleep(2000);
        driver.findElement(By.id("SSL")).click();
        return driver.findElement(By.className("choose-a-plane-subtitle")).getText();
    }
}
