package stepDefinition.salesforce;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObject.application.Salesforce;

public class SalesforceSearchAppSteps {
    private Salesforce salesforce = new Salesforce();

    @Then("^I search the \"([^\"]*)\" app$")
    public void the_AppPage(String appName) throws Throwable {
        boolean page = salesforce.getAppLauncherPage().searchAppName(appName);
        Assert.assertTrue(page, appName+" page was not displayed");
    }
}
