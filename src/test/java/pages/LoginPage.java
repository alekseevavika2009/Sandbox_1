package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import user.User;

public class LoginPage extends BasePage {

    private final By loginInput = By.xpath("//input[@data-test='username']");
    private final By passInput = By.xpath("//input[@data-test='password']");
    private final By loginBtn = By.cssSelector("#login-button");
    private final By errorMsg = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие URL страницы")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Логинимся под кредами пользователя")
    public void login(User user) {
        fillInLogin(user.getEmail());
        fillPassword(user.getPassword());
        pressLoginBtn();
    }

    @Step("Вводим логин = {user.email}")
    public void fillInLogin(String loginName) {
        driver.findElement(loginInput).sendKeys(loginName);
    }

    @Step("Вводим пароль = *****")
    public void fillPassword(String password) {
        driver.findElement(passInput).sendKeys(password);
    }

    @Step("Кликаем кнопку Логин")
    public void pressLoginBtn() {
        driver.findElement(loginBtn).click();
    }

    @Step("Проверяем явные ожидания, текст сообщения об ошибке")
    public String checkErrorMsg() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));
        return driver.findElement(errorMsg).getText();
    }
}