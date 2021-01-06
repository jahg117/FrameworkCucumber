package stepDefinition.salesforce;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObject.ApplicationInstance;

public class SalesforceSearchAppSteps extends ApplicationInstance {

    @Then("^I search the \"([^\"]*)\" app$")
    public void the_AppPage(String appName) throws Throwable {
        boolean page = salesforce.getAppLauncherPage().searchAppName(appName);
        Assert.assertTrue(page, appName+" page was not displayed");
    }
}
