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
        String currentUrl = browser.getCurrentUrl();
        assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
        //isDisplayed - проверяем виден ли элемент на странице
        boolean isProductsDisplayed = browser.findElement(By.xpath("//span[text()='Products']")).isDisplayed();
        //assertEquals - подтверждаем, что фактическое и ожидаемое значения равны
        assertEquals(isProductsDisplayed, true, "Products title should be displayed after login");
    }

    @Test
    public void checkAuthorizationFieldsDigit() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(5));
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.id("user-name")).sendKeys("111");
        browser.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("secret_sauce");
        browser.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
        String errorText = browser.findElement(By.xpath("//h3[@data-test='error']")).getText();
        assertEquals(errorText, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void checkAuthorizationWithEmptyPassword() {
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.id("user-name")).sendKeys("standard_user");
        browser.findElement(By.id("login-button")).click();
        String errorText = browser.findElement(By.xpath("//h3[@data-test='error']")).getText();
        assertEquals(errorText, "Epic sadface: Password is required");
    }
}
