package dealx.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SigninPage {
    private WebDriver webDriver;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "passwd")
    private WebElement password;

    @FindBy(css = "#SubmitLogin > span")
    private WebElement submitbtn;

    public SigninPage(WebDriver webDriver)  {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        Wait wait = new WebDriverWait(webDriver, 10000);
        wait.until(ExpectedConditions.visibilityOf(email));
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getSubmitbtn() {
        return submitbtn;
    }

    public void inputEmail(String emailInput){
        email.sendKeys(emailInput);
    }

    public void inputPassword(String passwordInput){
        password.sendKeys(passwordInput);
    }

    public MyAccount clickSubmit(){
        submitbtn.click();
        return new MyAccount(webDriver);
    }
}
