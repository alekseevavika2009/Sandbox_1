import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import static org.testng.Assert.assertEquals;

public class AuthorizeClass extends BaseTest {

    @Test
    public void checkAuthorizationFields() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(5));
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.id("user-name")).sendKeys("standard_user");
        browser.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("secret_sauce");
        browser.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Products']")));
    }

    @Test
    public void checkAuthorizationFieldsDigit() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(5));
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.id("user-name")).sendKeys("111");
        browser.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("secret_sauce");
        browser.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Products']")));
        String errorText = browser.findElement(By.xpath("//span[text()='Products']")).getText();
        assertEquals(errorText, "Epic sadface: Username and password do not match any user in this service");
    }
}
