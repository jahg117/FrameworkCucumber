package stepDefinition.acessServices;

import base.functions.CommonFunctions;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.CommonData;
import utils.JsonFiles;
import utils.Values;

public class CreateSelenium4Test extends ApplicationInstance {
    private CommonData commonData;
    private Logger logger = Logger.getLogger(CommonFunctions.class);
    private static String executionFlag = Values.REPLACETO_EMPTY;
    JsonFiles jsonFiles = new JsonFiles();
    CommonFunctions commonFunctions = new CommonFunctions();


    public CreateSelenium4Test(CommonData commonData) throws Exception {
        this.commonData = commonData;
    }


    @Then("I wait to take a screenshot from salesforce Logo")
    public void takeLogoScreenShot() throws Exception {
        accessServices.getNewS4FunctionalitiesPage().takeSS();

    }
}
