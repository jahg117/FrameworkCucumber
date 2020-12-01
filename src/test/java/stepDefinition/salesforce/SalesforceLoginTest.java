package stepDefinition.salesforce;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.application.Salesforce;

public class SalesforceLoginTest {
    private Salesforce salesforceScreen;

    @Given("Login page")
    public void login_page() {
        salesforceScreen = new Salesforce();
        salesforceScreen.goTo();
    }

    @When("^Enter an Username and Password$")
    public void enter_an_username_and_password() throws Throwable {
        salesforceScreen.getLoginPage().EnterUserPassword("edna.moreno@astrazeneca.com.ba.azuspep01.uat","automation2");
    }

    @Then("^The HomePage is displayed$")
    public void the_homepage() throws Throwable {
        salesforceScreen.getLoginPage().ClickLogin();
        salesforceScreen.getHomePage().IsSearchBarVisible();
    }
}
