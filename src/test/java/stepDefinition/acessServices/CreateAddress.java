package stepDefinition.acessServices;

import io.cucumber.java.en.Then;
import pageObject.ApplicationInstance;

public class CreateAddress extends ApplicationInstance {

    @Then("I add another address with state = MN")
    public void iAddAnotherAddressWithStateMN() throws Exception {
        accessServices.getPersonAccountPage().clickOnNewAddress();
        accessServices.getNewAccountAddressPage().searchAddressWithStateMN();
    }
}
