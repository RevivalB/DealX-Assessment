package dealx.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    private WebDriver webDriver;
    Actions actions;

    @FindBy(linkText = "Sign in")
    private WebElement signin;

    @FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[1]/a")
    private WebElement women;

    @FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[2]/a")
    private WebElement dresses;

    @FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[3]/a")
    private WebElement tshirt;

    @FindBy(xpath = "//*[@id=\"search_query_top\"]")
    private WebElement search;

    @FindBy(xpath = "//*[@id=\"searchbox\"]/button")
    private WebElement searchBtn;

    @FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[.]/ul/li[.]/a")
    private List <WebElement> womenSubCategories;

    @FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[.]/a")
    private List <WebElement> dressesSubCategories;

    @FindBy(xpath = "/html/body/h1")
    private WebElement pageNotFound;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getPageNotFound() {
        return pageNotFound;
    }

    public List<WebElement> getWomenSubCategories() {
        return womenSubCategories;
    }

    public List<WebElement> getDressesSubCategories() {
        return dressesSubCategories;
    }

    public WebElement getSearchBtn() {
        return searchBtn;
    }

    public WebElement getSearch() {
        return search;
    }

    public WebElement getSignin() {
        return signin;
    }

    public WebElement getWomen() {
        return women;
    }

    public WebElement getDresses() {
        return dresses;
    }

    public WebElement getTshirt() {
        return tshirt;
    }

    public SigninPage clickSignin(){
        signin.click();
        return new SigninPage(webDriver);
    }

    public String pageNotFound(){
        try{
            return pageNotFound.getText();
        }catch (Exception e){
            return "";
        }

    }
    public void clickWomen(){
        women.click();
    }

    public void clickDresses(){
        dresses.click();
    }

    public void clickTshirt(){
        tshirt.click();
    }

    public void inputSearchText(String inputText){
        search.clear();
        search.sendKeys(inputText);

    }

    public ResultsPage clickSearchBtn(){
        searchBtn.click();
        return new ResultsPage(webDriver);
    }

    public void clickWomenSubCategoryByText(String targetSubCategory){
        for (WebElement element : womenSubCategories) {
            String elementText = element.getAttribute("title");
            if (elementText.equals(targetSubCategory)) {
                try {
                    actions.moveToElement(element).build().perform();
                    element.click();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

        }

    }
    public void clickDressesSubCategoryByText(String targetSubCategory){
        for (WebElement element : dressesSubCategories) {
            String elementText = element.getAttribute("title");
            if (elementText.equals(targetSubCategory)) {
                try {
                    actions.moveToElement(element).build().perform();
                    element.click();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

        }

    }

    public void hoverOverWomen(){
        actions.moveToElement(women).build().perform();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void hoverOverDresses(){
        actions.moveToElement(dresses).build().perform();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
