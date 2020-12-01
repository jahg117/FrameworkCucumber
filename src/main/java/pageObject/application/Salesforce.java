package pageObject.application;

import base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObject.pages.salesforce.HomePage;
import pageObject.pages.salesforce.LoginPage;

public class Salesforce {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    public Salesforce() {
        this.driver = DriverFactory.getDriver();
        this.loginPage = PageFactory.initElements(driver, LoginPage.class);
        this.homePage = PageFactory.initElements(driver, HomePage.class);
    }

    public LoginPage getLoginPage(){
        return loginPage;
    }

    public HomePage getHomePage(){
        return homePage;
    }

    public void goTo() {
        driver.get("https://test.salesforce.com/");
    }
}
