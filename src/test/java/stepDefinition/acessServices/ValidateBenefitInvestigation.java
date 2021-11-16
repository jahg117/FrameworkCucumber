package stepDefinition.acessServices;

import base.functions.CommonFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import pageObject.ApplicationInstance;
import pageObject.application.AccessServices;
import stepDefinition.shareData.CommonData;
import utils.Values;

public class ValidateBenefitInvestigation extends ApplicationInstance {

    private CommonData commonData;
    CommonFunctions commonFunctions = new CommonFunctions();
    private Logger logger = Logger.getLogger(CommonFunctions.class);

    public ValidateBenefitInvestigation(CommonData commonData) {
        this.commonData = commonData;
    }


    @And("I validate the BI data with the RV cases are with the status {string} {string}")
    public void iValidateTheBIDataWithTheRVCases(String status, String ntd) throws Exception {
        for (String patientID : Values.globalStringList) {
            accessServices.getCasePage().searchPatientIDSF(patientID);
            Values.globalPayerName = accessServices.getPersonAccountPage().getPayerName();
            accessServices.getBenefitInvestigationPage().validateAndClickBICase(status, ntd);
        }

    }
}
