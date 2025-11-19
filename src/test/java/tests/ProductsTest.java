package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductsTest extends BaseTest {

    @Test
    public void shoppingCartContainer() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isPageOpen();
        productsPage.addToCart(0);
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.switchToCart();
        // найти каунтер, взять оттуда текст.посмотреть, что он должен
        // соответствовать ожидаемому кол-ву товара, которое мы положили в корзину
        // проверяем по названию
        assertTrue(cartPage.getProductNames().contains("Sauce Labs Backpack"));
        // проверяем размер коллекции
        assertEquals(cartPage.getProductNames().size(), 2);
        // проверяем, что коллекция не пустая
        assertFalse(cartPage.getProductNames().isEmpty());

    }
}
