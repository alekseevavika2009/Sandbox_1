package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    private static final String ADD_TO_CART =
            "//*[text()='%s']//ancestor::div[@class='inventory_item']//child::button[text()='Add to cart']";
    private final By title = By.xpath("//*[@data-test='title']");
    private final By addToCart = By.xpath("//*[text()='Add to cart']");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидаем прогрузку страницы с товаром")
    public boolean isPageOpen() {
        return driver.findElement(title).isDisplayed();
    }

    @Step("Проверяем текст названия страницы")
    public String getTitleText() {
        return driver.findElement(title).getText();
    }

    @Step("Добавляем товар в корзину")
    public void addToCart(final String goodsName) {
        //  By addToCart = By.xpath(String.format(ADD_TO_CART, "Sauce Labs Backpack"));
        By addToCartt = By.xpath(ADD_TO_CART.formatted(goodsName));
        driver.findElement(addToCartt).click();
    }

    @Step("Добавляем товар в корзину")
    public void addToCart(final int index) {
        driver.findElements(addToCart).get(index).click();
    }

    @Step("Переходим в корзину")
    public void switchToCart() {
        driver.findElement(cartLink).click();
    }
}