package stepDefinition.acessServices;

import base.functions.CommonFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.testng.Assert;
import pageObject.ApplicationInstance;
import utils.FileReading;
import utils.Values;
import java.util.List;
import java.util.Locale;

public class CreateAddress extends ApplicationInstance {

    CommonFunctions commonFunctions = new CommonFunctions();

    @Then("I add another address with state = MN")
    public void iAddAnotherAddressWithStateMN() throws Exception {
        accessServices.getPersonAccountPage().clickOnNewAddress();
        accessServices.getNewAccountAddressPage().searchAddressWithStateMN();
    }
}
