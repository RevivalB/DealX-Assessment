package dealx;

import dealx.pagefactory.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class Navigate {
    WebDriver driver;
    private String username = "yves.axel@gmail.com";
    private String password = "Test1234";

    @BeforeClass
    public void setUp() {
        String url = "http://automationpractice.com";
        String browser = "chrome";
        try {
            if (browser.equals("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(); //Launch Chrome
            } else if (browser.equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get(url); //Navigating to the website
            HomePage homePage = new HomePage(driver);
            if(homePage.pageNotFound().equals("Resource Limit Is Reached")){
                driver.quit();
                System.exit(-1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void TC1() {
        String searchItem = "Printed Summer Dress";
        HomePage homePage = new HomePage(driver);

        homePage.inputSearchText(searchItem);
        ResultsPage resultsPage = homePage.clickSearchBtn();
        WebElement matchingItemByText = resultsPage.getMatchingItemByText(searchItem);
        // getMatchingItemByText() gets the WebElement with  a title or text that matches the search text
        //Below we are asserting that the returned WebElement is not Null to confirm that we got the matching item.
        Assert.assertNotNull(matchingItemByText);
    }

    @Test
    public void TC2() {
        HomePage homePage = new HomePage(driver);
        String items = "Blouse,Printed Chiffon Dress,Printed Summer Dress";
        String[] criteria = items.split(",");
        for (String searchItem : criteria) {
            homePage.inputSearchText(searchItem);
            ResultsPage resultsPage = homePage.clickSearchBtn();
            WebElement matchingItemByText = resultsPage.getMatchingItemByText(searchItem);
            // getMatchingItemByText() gets the WebElement with  a title or text that matches the search text
            //Below we are asserting that the returned WebElement is not Null to confirm that we got the matching item.
            Assert.assertNotNull(matchingItemByText);
        }
    }

    @Test(dataProvider = "SearchItems", dataProviderClass = DdealXData.class)
    public void TC3(Object[] data){
        HomePage homePage = new HomePage(driver);
        String searchItem = (String) data[0];

        homePage.inputSearchText(searchItem);
        ResultsPage resultsPage = homePage.clickSearchBtn();
        WebElement matchingItemByText = resultsPage.getMatchingItemByText(searchItem);
        // getMatchingItemByText() gets the WebElement with  a title or text that matches the search text
        //Below we are asserting that the returned WebElement is not Null to confirm that we got the matching item.
        Assert.assertNotNull(matchingItemByText);
    }

    public MyAccount signIn(String email, String password){
        HomePage homePage = new HomePage(driver);
        SigninPage signinPage = homePage.clickSignin();
        signinPage.inputEmail(email);
        signinPage.inputPassword(password);
        return signinPage.clickSubmit();
    }

    @Test
    public void TC4(){
        MyAccount myAccount = signIn(username, password);
        String accountHeaderValue = myAccount.getMyAccountHeaderValue();
        Assert.assertEquals(accountHeaderValue,"MY ACCOUNT");
    }

    @Test
    public void TC5(){
        MyAccount myAccount = signIn(username, password);
        String accountHeaderValue = myAccount.getMyAccountHeaderValue();
        Assert.assertEquals(accountHeaderValue,"MY ACCOUNT");

        myAccount.inputSearchText("Blouse");
        ResultsPage resultsPage = myAccount.clickSearchBtn();

        resultsPage.getMatchingItemByText("Blouse");
        try {
            resultsPage.addToCard();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ShoppingCartSummary shoppingCartSummary = resultsPage.clickProceed();
        String unitPriceValue = shoppingCartSummary.getUnitPriceValue();

        shoppingCartSummary.inputNumberofItems("5");
        String totalPriceValue = shoppingCartSummary.getTotalPriceValue();

        double unitPrice = Double.parseDouble(unitPriceValue.replaceFirst("\\$", "").trim());
        double totalPriceDisplayed = Double.parseDouble(totalPriceValue.replaceFirst("\\$", "").trim());
        int quantity = 5;

        PriceCalculator priceCalculator = new PriceCalculator(unitPrice, quantity);
        double totalPrice = priceCalculator.calculateTotalPrice();
        Assert.assertEquals(totalPriceDisplayed,totalPrice);
    }

    @Test(dataProvider = "PageNavigationData", dataProviderClass = DdealXData.class)
    public void TC6(Object[] data){
        HomePage homePage = new HomePage(driver);
        String category = (String) data[0];
        String subcategory =  (String) data[1];

        if(category.equals("Women")){
            if(!subcategory.isEmpty()){
                homePage.hoverOverWomen();
                homePage.clickWomenSubCategoryByText(subcategory);
            }else{
                homePage.clickWomen();
            }
        }else if(category.trim().equals("Dresses")){
            if(!subcategory.isEmpty()){
                homePage.hoverOverDresses();
                homePage.clickDressesSubCategoryByText(subcategory);
            }else{
                homePage.clickWomen();
            }
        }else if(category.equals("T-Shirts")){
            homePage.clickTshirt();
        }

    }

    @AfterMethod
    public void Report(){
        System.out.println("After Method");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @AfterTest
    public void Quit() {
        driver.quit();
        Reporter.log("Driver closed");
    }
}