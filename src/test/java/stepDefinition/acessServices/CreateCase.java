package stepDefinition.acessServices;

import io.cucumber.java.en.And;
import pageObject.ApplicationInstance;

public class CreateCase extends ApplicationInstance {

    @And("^I click on new Case")
    public void clickNewCase() throws Exception {
        accessServices.getPersonAccountPage().clickNewCase();
    }
}
