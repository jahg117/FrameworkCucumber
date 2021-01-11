package stepDefinition.salesforce;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
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

    @Then("^I search the \"([^\"]*)\" app$")
    public void the_AppPage(String appName) throws Throwable {
        boolean page = salesforce.getAppLauncherPage().searchAppName(appName);
        Assert.assertTrue(page, appName+" page was not displayed");
        //salesforce.getHomePage().closeOpenTabs();
    }

}
