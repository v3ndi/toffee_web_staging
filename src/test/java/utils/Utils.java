package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Utils {

    public static void scroll(WebDriver driver, int w, int h) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(" + w + "," + h + ")");
    }

    public static void scroll(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static boolean scrollAndSearchForElement(WebDriver driver, By by, int index) {
        int attempts = 0;
        while (attempts < 5) {
            try {
                List<WebElement> elements = driver.findElements(by);
                if (elements.size() > index && elements.get(index).isDisplayed()) {
                    scrollToElement(driver, elements.get(index));
                    return true;
                } else {
                    scroll(driver);
                    Thread.sleep(3000); // wait for 3 seconds
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            attempts++;
        }
        return false;
    }

    public static void scrollToElementWithTimeout(WebDriver driver, List<WebElement> elements, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        boolean elementFound = false;
        long endTime = System.currentTimeMillis() + timeout * 1000;

        while (!elementFound && System.currentTimeMillis() < endTime) {
            try {
                for (WebElement element : elements) {
                    if (element.isDisplayed()) {
                        elementFound = true;
                        scrollToElement(driver, element); // Scroll to the element
                        break;
                    }
                }
            } catch (NoSuchElementException ignored) {
            }

            if (!elementFound) {
                scroll(driver, 0, 500);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // Properly handle the interruption
                }
            }
        }

        if (!elementFound) {
            throw new TimeoutException("Element not found within the timeout period");
        }
    }

    public static void scrollToElementWithTimeout(WebDriver driver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        boolean elementFound = false;
        long endTime = System.currentTimeMillis() + timeout * 1000;

        while (!elementFound && System.currentTimeMillis() < endTime) {
            try {
                if (element.isDisplayed()) {
                    elementFound = true;
                    scrollToElement(driver, element); // Scroll to the element
                    break;
                }
            } catch (NoSuchElementException ignored) {
            }

            if (!elementFound) {
                scroll(driver, 0, 500);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // Properly handle the interruption
                }
            }
        }

        if (!elementFound) {
            throw new TimeoutException("Element not found within the timeout period");
        }
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void scrollUntilElementFound(WebDriver driver, By by, int minElements) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        while (true) {
            try {
                List<WebElement> elements = driver.findElements(by);
                if (elements.size() >= minElements) {
                    System.out.println("Found at least " + minElements + " elements. Stopping scroll.");
                    break;
                }
                System.out.println("Not enough elements found. Scrolling down.");
                scroll(driver);
                Thread.sleep(5000); // Short delay to allow content to load
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                System.out.println("Timed out waiting for elements. Scrolling down.");
                scroll(driver);
            }
        }
    }
}
