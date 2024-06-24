package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.util.List;

public class Home {
    public WebDriver driver;

    @FindBy(tagName = "h4")
    List<WebElement> goingToFeed;

    public Home(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String surfingHome() throws InterruptedException {
        Thread.sleep(10000);
        boolean found = Utils.scrollAndSearchForElement(driver, By.tagName("h4"), 12);
        if (found) {
            return goingToFeed.get(12).getText();
        } else {
            throw new RuntimeException("Element at index 12 not found after scrolling.");
        }
    }
}
