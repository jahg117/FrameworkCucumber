package stepDefinition.acessServices;

import base.functions.CommonFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.testng.Assert;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.Case;
import stepDefinition.shareData.CommonData;
import stepDefinition.shareData.Interaction;
import stepDefinition.shareData.Product;
import utils.JsonFiles;
import utils.Values;

import java.util.HashMap;
import java.util.List;

public class CreateReverificationSearch extends ApplicationInstance {

    private CommonData commonData;
    CommonFunctions commonFunctions = new CommonFunctions();
    private Logger logger = Logger.getLogger(CommonFunctions.class);

    public CreateReverificationSearch(CommonData commonData) {
        this.commonData = commonData;
    }

    @Then("I click on Reverification tab from RV Case")
    public void iClickOnReverificationTabFromRVCase() throws Exception {
        accessServices.getCasePage().clickOnReverification();
    }

    @Then("I create a search to select patients and associate them to RV case using the following {string}")
    public void iOpenTheRVCase(String filterOptions) throws Exception {

        if (accessServices.getReverificationSearch().isReverificationScreenDisplay()) {
            accessServices.getReverificationSearch().searchFiltersRV(filterOptions);
        }
    }

    @Given("The {string} i select from the table the patients to be associate to case")
    public void iSelectPatientsToAssociateToCase(String ammountOfPatients) throws Exception {
        Values.globalStringList.addAll(accessServices.getReverificationSearch().associatePatientsToRVCase(ammountOfPatients));
    }
}
