package testrunners;

import config.Setup;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Home;

public class HomeTestRunner extends Setup {
    @Test
    public void feedScroll() throws InterruptedException {
        Home home = new Home(driver);
        String actualFeed = home.surfingHome();
        String expectedFeed = "Feed";
        Assert.assertEquals(actualFeed,expectedFeed);
        Assert.assertTrue(actualFeed.contains(expectedFeed));
    }
}
