import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    WebDriver browser;

@BeforeMethod
    public void setUp() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("start-maximized");
    browser = new ChromeDriver(options);
    browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
}

@AfterMethod
    public void closeBrowser() {
    browser.quit();
}
}
