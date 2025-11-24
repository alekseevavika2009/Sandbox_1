package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private final By title = By.xpath("//*[@data-test='title']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public ArrayList<String> getProductNames() {
        List<WebElement> allProducts = driver.findElements(By.cssSelector(".inventory_item_name"));
        ArrayList<String> names = new ArrayList<>();
        for (WebElement product : allProducts) {
            names.add(product.getText());
        }

        return names;
    }

    public void wailPageLoaded() {
        driver.findElement(title).isDisplayed();
    }
}