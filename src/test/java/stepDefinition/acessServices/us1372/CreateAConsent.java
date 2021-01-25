package stepDefinition.acessServices.us1372;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObject.ApplicationInstance;

public class CreateAConsent extends ApplicationInstance {

    @Given("^A External ID i search a CPC at Customer Lookup$")
    public void searchCPCByID() throws Exception {
        accessServices.getCustomerLookupPage().searchCPCByID();
    }

}
