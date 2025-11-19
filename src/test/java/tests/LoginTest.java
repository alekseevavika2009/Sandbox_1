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
    @DataProvider ()


    @Test
    public void incorrectLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertEquals(loginPage.checkErrorMsg(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void incorrectPassword() {
        loginPage.open();
        loginPage.login("standard_user", "111");
        assertEquals(loginPage.checkErrorMsg(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void emptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.checkErrorMsg(), "Epic sadface: Password is required");
    }

    @Test
    public void emptyLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.checkErrorMsg(), "Epic sadface: Username is required");
    }
}
