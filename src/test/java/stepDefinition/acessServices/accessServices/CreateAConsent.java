package stepDefinition.acessServices.accessServices;

import io.cucumber.java.en.Given;
import pageObject.ApplicationInstance;

public class CreateAConsent extends ApplicationInstance {

    @Given("^A External ID i search a CPC at Customer Lookup$")
    public void searchCPCByID() throws Exception {
        accessServices.getCustomerLookupPage().searchCPCByID();
    }

}
