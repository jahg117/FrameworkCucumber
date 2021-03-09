package stepDefinition.generalSteps;

import base.functions.CommonFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.log4j.Logger;
import pageObject.ApplicationInstance;

public class OpenMenuOptionSteps extends ApplicationInstance {
    @And("^I select the \"([^\"]*)\" menu option$")
    public void clickMenuOption(String menuOption) throws Exception {
        accessServices.getAccessServicesHomePage().selectMenuOption(menuOption);

    }
}
