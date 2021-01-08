package stepDefinition.salesforce;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObject.ApplicationInstance;

public class SalesforceLoginSteps extends ApplicationInstance {

    @Given("^I login as an \"([^\"]*)\" user$")
    public void loginPageCredentials(String salesForceUser) throws Throwable {
        salesforce.goTo();
        salesforce.getLoginPage().enterUserPassword(salesForceUser);
    }

    @When("^the salesforce page is displayed$")
    public void setSalesforcePageDisplayed() throws Exception {
        salesforce.getHomePage().isSalesforcePageVisible();
        salesforce.getHomePage().closeOpenTabs();
    }

}
