package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        //правда ли что кнопка видна
        assertTrue(productsPage.isPageOpen());
        //текст продукты
        assertEquals(productsPage.getTitleText(), "Products");
    }

    @DataProvider(name = "invalidUser")
    public Object[][] loginData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"standard_user", "111", "Epic sadface: Username and password do not match any user in this service"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"}
        };
    }

    @Test(dataProvider = "invalidUser")
    public void incorrectLogin(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.checkErrorMsg(), errorMessage);
    }
}
