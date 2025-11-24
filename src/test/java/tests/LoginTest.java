package tests;

import io.qameta.allure.*;
import net.bytebuddy.build.Plugin;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import user.UserFactory;
import utils.AllureUtils;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {
    @Epic("Модуль логина интернет-магазина")
    @Feature("Авторизация")
    @Story("Какое-то название")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Имя Фамилия @yandex.ru или твой тег")
    @TmsLink("Sandbox_1")

    @Test()
    public void correctLogin() {
        System.out.println("CorrectLogin Tests are running in thread: " + Thread.currentThread().getId());
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        assertTrue(productsPage.isPageOpen());
        //AllureUtils.takeScreenshot(driver);
        assertEquals(productsPage.getTitleText(), "ProductsS");
    }

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                {UserFactory.withLockedUserPermission(), "Epic sadface: Sorry, this user has been locked out."}
                /* {"", "secret_sauce", "Epic sadface: Username is required"},
                 {"standard_user", "", "Epic sadface: Password is required"}*/
        };
    }

    @Test(dataProvider = "loginData")
    public void incorrectLogin(User user, String errorMessage) {
        System.out.println("InCorrectLogin Tests are running in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(user);
        assertEquals(loginPage.checkErrorMsg(), errorMessage);
    }
}