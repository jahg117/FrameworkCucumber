package stepDefinition.acessServices;

import base.functions.CommonFunctions;
import io.cucumber.java.en.And;
import org.apache.log4j.Logger;
import org.testng.Assert;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.CommonData;

import java.lang.reflect.InvocationTargetException;

public class SearchPatient extends ApplicationInstance {

    private CommonData commonData;
    private Logger logger = Logger.getLogger(CommonFunctions.class);

    public SearchPatient(CommonData commonData) {
        this.commonData = commonData;
    }

    @And("^I validate the patient account was created$")
    public void validatePatientAccountCreated() throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    String account = accessServices.getAccountsPage().isAccountCreated(commonData.patient.getPatientName());
                    Assert.assertEquals(commonData.patient.getPatientName(), account, "The patient account created was not found");
                } else {
                    logger.info("Does not required to be executed Since Flag Contains : " + commonData.globalShareData.getExecutionFlag().trim());
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            String account = accessServices.getAccountsPage().isAccountCreated(commonData.patient.getPatientName());
            Assert.assertEquals(commonData.patient.getPatientName(), account, "The patient account created was not found");
        }
    }

    @And("I search the patient at Recently View to check the Valid PAF column from Accounts Page")
    public void searchPatientInView() throws Exception {
        try{
            if (commonData.globalShareData.getExecutionFlag()!=null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    String account = accessServices.getAccountsPage().isAccountCreated(commonData.patient.getPatientName());
                    Assert.assertEquals(commonData.patient.getPatientName(), account, "The patient account created was not found");
                } else {
                    logger.info("Does not required to be executed Since Flag Contains : " + commonData.globalShareData.getExecutionFlag().trim());
                }
            }
        }catch (InvocationTargetException|NullPointerException e) {
            String account = accessServices.getAccountsPage().isAccountCreated(commonData.patient.getPatientName());
            Assert.assertEquals(commonData.patient.getPatientName(), account, "The patient account created was not found");
        }
    }
}


