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

public class CreatePayerInsurance extends ApplicationInstance {

    CommonFunctions commonFunctions = new CommonFunctions();



    @Then("I select the {string} and i fill the insurance form with {string} or {string}")
    public void iClickOnPayerTabFromAndSelectPersonAccountPage(String insuranceType, String dataPMI, String dataPBM) throws Exception {
        List<String> insuranceTypeList = commonFunctions.splitRegex(insuranceType, Values.REGEX_COMMA);
        List<String> dataPMIList = commonFunctions.splitRegex(dataPMI = dataPMI.replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.REPLACETO_EMPTY), Values.REGEX_COMMA);
        List<String> dataPBMList = commonFunctions.splitRegex(dataPBM = dataPBM.replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.REPLACETO_EMPTY), Values.REGEX_COMMA);
        for (int i = 0; i <= insuranceTypeList.size() - 1; i++) {
            accessServices.getPersonAccountPage().clickNewPatientInsurances();
            accessServices.getNewPatientInsurance().selectInsuranceType(insuranceTypeList.get(i).trim());
            switch (insuranceTypeList.get(i).trim().toLowerCase()) {
                case "nopi":
                    accessServices.getNewPatientInsuranceNoPI().clickOnSaveNoPI();
                    break;
                case "pmi":
                    accessServices.getNewPatientInsurancePMI().fillPMIForm(dataPMIList);
                    break;
                case "pbm":
                    accessServices.getNewPatientInsurancePBM().fillPBMForm(dataPBMList);
                    break;
            }
            Assert.assertTrue(!accessServices.getNewPatientInsurance().getPatientInsuranceNumber().isEmpty(), "Insurance Created");
            commonFunctions.closeLastSubTabSF(commonFunctions.mediumWait());
        }
        accessServices.getPersonAccountPage().clickOnProgramEnrollments();
    }

    @Then("I click on Payer tab from PersonAccountPage")
    public void iClickOnPayerTabFromAccountPage() throws Exception {
        accessServices.getPersonAccountPage().clickPayerTab();
    }
}
