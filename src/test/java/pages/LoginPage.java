package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By loginInput = By.id("user-name");
    private final By passInput = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By errorMsg = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
// супер используем когда наследуемся от другой странички
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    // используем один общий логин и вызываем методы.
// если нужно использовать только ввод логина, используем уже fillInLogin и так далее
    public void login(String loginName, String password) {
        fillInLogin(loginName);
        fillPassword(password);
        pressLoginBtn();
    }

    public void fillInLogin(String loginName) {
        driver.findElement(loginInput).sendKeys(loginName);
    }

    public void fillPassword(String password) {
        driver.findElement(passInput).sendKeys(password);
    }

    public void pressLoginBtn() {
        driver.findElement(loginBtn).click();
    }

    public String checkErrorMsg() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));
        return driver.findElement(errorMsg).getText();
    }
}
