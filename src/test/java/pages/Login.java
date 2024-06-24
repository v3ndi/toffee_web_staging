package pages;


import config.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class Login {
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

    public Login (WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public String doLogin () throws InterruptedException {
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
        return dropDownMenus.get(5).getText();
    }
}