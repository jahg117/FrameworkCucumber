package stepDefinition.salesforce;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.application.Salesforce;
import utils.FileReading;

public class SalesforceMethodsTest {
    private Salesforce salesforceScreen;
    private FileReading fileReading = new FileReading();

    @Given("^Login page in SF$")
    public void login_page() {
        salesforceScreen = new Salesforce();
        salesforceScreen.goTo();
    }

    @When("^Enter an Username and Password in SF$")
    public void enter_an_username_and_password() throws Throwable {
        salesforceScreen.getLoginPage().EnterUserPassword("test.admin@astrazeneca.net.azusacs01.intacs","Test@123");
        //Assert.assertTrue(false);
    }

    @Then("^The HomePage is displayed in SF$")
    public void the_homepage() throws Throwable {
        salesforceScreen.getLoginPage().ClickLogin();
        salesforceScreen.getHomePage().IsSearchBarVisible();
    }

    @Then("^The Correct AppPage is displayed$")
    public void the_AppPage() throws Throwable {
        salesforceScreen.getAppLauncherPage().searchAppName(fileReading.getField("appNameSF"));
    }
}