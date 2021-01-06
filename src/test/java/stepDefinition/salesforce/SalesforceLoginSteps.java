package stepDefinition.salesforce;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObject.application.Salesforce;

public class SalesforceLoginSteps {
    private Salesforce salesforce = new Salesforce();

    @Given("^I login as an \"([^\"]*)\" user$")
    public void loginPageCredentials(String salesForceUser) throws Throwable {
        salesforce.goTo();
        salesforce.getLoginPage().enterUserPassword(salesForceUser);
    }

    @When("^the salesforce page is displayed$")
    public void setSalesforcePageDisplayed() {
        salesforce.getHomePage().IsSearchBarVisible();
    }

}
