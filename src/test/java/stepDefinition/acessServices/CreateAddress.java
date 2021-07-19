package stepDefinition.acessServices;

import io.cucumber.java.en.Then;
import pageObject.ApplicationInstance;

public class CreateAddress extends ApplicationInstance {

    @Then("I add any state in the default address except MN and MD")
    public void iAddAnyStateInTheDefaultAddressExceptMNAndMD() throws Exception {
        accessServices.getPersonAccountPage().clickFirstAddressLine1();
        accessServices.getNewAccountAddressPage().addStateInDefaultAddress();
        accessServices.getSubTabsPage().closeSubTab(1);
        accessServices.getSubTabsPage().closeLastSubTab();
    }

    @Then("I add another address with state = MN")
    public void iAddAnotherAddressWithStateMN() throws Exception {
        accessServices.getPersonAccountPage().clickOnNewAddress();
        accessServices.getNewAccountAddressPage().searchAddressWithStateMN();
        accessServices.getSubTabsPage().closeLastSubTab();
    }

    @Then("I add another address with state = MD")
    public void iAddAnotherAddressWithStateMD() throws Exception {
        accessServices.getPersonAccountPage().clickOnNewAddress();
        accessServices.getNewAccountAddressPage().searchAddressWithStateMD();
        accessServices.getSubTabsPage().closeLastSubTab();
    }
}
