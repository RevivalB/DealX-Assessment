package dealx.pagefactory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingCartSummary extends HomePage {
    private WebDriver webDriver;
    private WebElement matchingItem;
    private int elementNumber = 1;

    @FindBy(xpath = "//*[starts-with(@id, 'product_price')]")
    private WebElement unitPrice;

    @FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[5]/div/a[2]/span/i")
    private WebElement quantity;

    @FindBy(xpath = "//*[@id=\"total_product\"]")
    private WebElement totalPrice;

    public ShoppingCartSummary(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getTotalPrice() {
        return totalPrice;
    }

    public WebElement getUnitPrice() {
        return unitPrice;
    }

    public WebElement getQuantity() {
        return quantity;
    }

    public String getUnitPriceValue(){
        return unitPrice.getText();
    }

    public void  inputNumberofItems(String numberOfItems){
        try {
            int itemsNumber = Integer.parseInt(numberOfItems) - 1;
            do {
                quantity.click();
            } while (--itemsNumber > 0);
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTotalPriceValue(){
        return totalPrice.getText();
    }

}