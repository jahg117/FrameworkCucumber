package stepDefinition.salesforce;

import base.functions.CommonFunctions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObject.ApplicationInstance;

import java.util.List;
import java.util.Map;

public class SalesforceLoginSteps extends ApplicationInstance {

    @Given("^I login as an \"([^\"]*)\" user$")
    public void loginPageCredentials(String salesForceUser) throws Throwable {
        salesforce.goTo();
        salesforce.getLoginPage().enterUserPassword(salesForceUser);
    }

    @Given("^I login according to the account selected from table$")
    public void loginPageCredentials(DataTable dataTable) throws Throwable {
        List<Map<String, String>> userAccountList = dataTable.asMaps(String.class, String.class);
        String salesForceUser = salesforce.getLoginPage().selectUserFromList(userAccountList);
        salesforce.goTo();
        salesforce.getLoginPage().enterUserPassword(salesForceUser);
    }

    @When("^the salesforce page is displayed$")
    public void setSalesforcePageDisplayed() throws Exception {
        Assert.assertTrue(salesforce.getHomePage().isSalesforcePageVisible(), "The salesforce page is not displayed");
        salesforce.getHomePage().closeOpenTabs();
    }

    @Then("^I search the \"([^\"]*)\" app$")
    public void the_AppPage(String appName) throws Throwable {
        boolean page = salesforce.getAppLauncherPage().searchAppName(appName);
        Assert.assertTrue(page, appName + " page was not displayed");
    }

    @And("Close all the Tabs")
    public void closeTabs() throws Exception {
        salesforce.getHomePage().closeOpenTabs();
    }
}
