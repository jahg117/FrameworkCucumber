package stepDefinition.acessServices;

import io.cucumber.java.en.And;
import pageObject.ApplicationInstance;

public class Tabs extends ApplicationInstance {

    @And("^I close the last sub tab")
    public void productEnrollmentDisplayed() throws Exception {
        accessServices.getSubTabsPage().closeLastSubTab();
    }
}
