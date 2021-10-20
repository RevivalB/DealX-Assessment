package dealx.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ResultsPage extends HomePage {
    private WebDriver webDriver;
    private WebElement matchingItem;
    private int elementNumber = 1;

    @FindBy(xpath = "//*[@id=\"center_column\"]/ul/li")
    private List<WebElement> searchResultList;

    @FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")
    private WebElement addItemToCartPopup;

    public ResultsPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public List<WebElement> getSearchResultList() {
        return searchResultList;
    }

    public WebElement getMatchingItemByText(String targetItem) {
        for (WebElement element : searchResultList) {
            String elementText = element.findElement(By.xpath("div/div[2]/h5/a")).getText();
            System.out.println("Item Value: " + elementText);
            if (elementText.equals(targetItem)) {
                matchingItem = element;
                return element;
            }
            elementNumber++;
        }
        return null;
    }

    public void addToCard() throws Exception {
        if (matchingItem != null) {
            actions.moveToElement(matchingItem).build().perform();
            try {
                Thread.sleep(2000);
                WebElement addToCartElement = webDriver.findElement(
                        By.xpath("//*[@id=\"center_column\"]/ul/li[" + elementNumber + "]/div/div[2]/div[2]/a[1]/span"));
                addToCartElement.click();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new Exception("No Matching Item Was Found");
        }
    }

    public ShoppingCartSummary clickProceed() {
        Wait wait = new WebDriverWait(webDriver, 5000);
        wait.until(ExpectedConditions.elementToBeClickable(addItemToCartPopup));
        addItemToCartPopup.click();
        return new ShoppingCartSummary(webDriver);
    }
}
