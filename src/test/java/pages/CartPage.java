package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private final By productNamesLocator = By.cssSelector(".inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public ArrayList<String> getProductNames() {
        //выкатываем список элементов по локатору (список имен), образуем новый список names
        // далее проходимся по всем элементам в списке. в список добавляем каждое имя оттуда, где мы все это нашли
        List<WebElement> allProducts = driver.findElements(productNamesLocator); //вынести в прекласс
        ArrayList<String> names = new ArrayList<>();
        for (WebElement product : allProducts) {
            names.add(product.getText());
        }
        return names;
    }
}
