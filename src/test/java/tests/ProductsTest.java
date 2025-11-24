package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {

    @Test
    public void checkGoodsAdded() {
        final String goodsName = "Test.allTheThings() T-Shirt (Red)";
        System.out.println("Products Tests are running in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.isPageOpen();
        productsPage.addToCart(0);
        productsPage.addToCart(goodsName);
        productsPage.switchToCart();
        assertTrue(cartPage.getProductNames().contains(goodsName));
        assertEquals(cartPage.getProductNames().size(), 2);
        assertFalse(cartPage.getProductNames().isEmpty());
    }
}