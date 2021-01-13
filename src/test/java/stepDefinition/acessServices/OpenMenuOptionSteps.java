package stepDefinition.acessServices;

import io.cucumber.java.en.And;
import pageObject.ApplicationInstance;

public class OpenMenuOptionSteps extends ApplicationInstance {

    @And("^I select the \"([^\"]*)\" menu option$")
    public void clickMenuOption(String menuOption) throws Exception{
        accessServices.getAccessServicesHomePage().selectMenuOption(menuOption);
        salesforce.getHomePage().closeOpenTabs();
    }
}
