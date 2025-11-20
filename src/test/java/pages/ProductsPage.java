package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ProductsPage extends BasePage {
    //все константы пишутся перед простыми переменными. '%s' - вместо меняющихся букв
    private static final String ADD_TO_CART =
            "//text()='%s']//ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private final By title = By.xpath("//*[@data-test='title']");
    private final By addToCartButton = By.xpath("//button[text()='Add to cart']");
    private final By cartLink = By.cssSelector(".shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpen() {
        return driver.findElement(title).isDisplayed();
    }

    public String getTitleText() {
        return driver.findElement(title).getText();
    }

    public void addToCart(final String goodsName) {
        // Sauce Labs Backpack - можно потом вынести в тест
        // By addToCart = By.xpath(String.format(ADD_TO_CART, "Sauce Labs Backpack"));
        //goodsName - это текстовый идентификатор товара, который пользователь хочет добавить в корзину
        By addToCart = By.xpath(ADD_TO_CART.formatted(goodsName));
        driver.findElement(addToCart).click();
    }

    public void addToCart(final int index) {
        // находим все кнопки добавить в корзину, далее выбираем индекс
        driver.findElements(addToCartButton).get(index).click();
    }

    public void switchToCart() {
        driver.findElement(cartLink).click();
    }
}
