package stepDefinition.salesforce;

import base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.pages.salesforce.HomePage;
import selenium.pages.salesforce.LoginPage;

public class SalesforceLogin extends Base {

    @Given("Login page")
    public void login_page() {
        CurrentPage = GetInstance(LoginPage.class);
    }

    @When("^Enter an Username and Password$")
    public void enter_an_username_and_password() throws Throwable {
        CurrentPage.As(LoginPage.class).EnterUserPassword(fileReading.getField("User"), fileReading.getField("Password"));
    }

    @Then("^The HomePage is displayed$")
    public void the_homepage() throws Throwable {
        CurrentPage = CurrentPage.As(LoginPage.class).ClickLogin();
        CurrentPage.As(HomePage.class).IsSearchBarVisible();
    }
}
