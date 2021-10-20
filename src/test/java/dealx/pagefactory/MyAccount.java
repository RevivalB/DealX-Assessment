package dealx.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccount extends HomePage {
    @FindBy(xpath = "//*[@id=\"center_column\"]/h1")
    private WebElement myaccountspageheader;

    public MyAccount(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        Wait wait = new WebDriverWait(webDriver, 10000);
        wait.until(ExpectedConditions.visibilityOf(myaccountspageheader));
    }

    public WebElement getMyaccountspageheader() {
        return myaccountspageheader;
    }

    public String getMyAccountHeaderValue(){
        return myaccountspageheader.getText();
    }
}

