package stepDefinition.acessServices;

import base.functions.CommonFunctions;
import com.codoid.products.exception.FilloException;
import io.cucumber.java.en.And;
import io.cucumber.java.ht.E;
import org.apache.log4j.Logger;
import org.testng.Assert;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.CommonData;
import stepDefinition.shareData.Patient;
import utils.ExcelFiles;

import java.lang.reflect.InvocationTargetException;

public class SearchPatient extends ApplicationInstance {

    private CommonData commonData;
    private Logger logger = Logger.getLogger(CommonFunctions.class);

    public SearchPatient(CommonData commonData) {
        this.commonData = commonData;
    }

    @And("^I validate the patient ID is displayed$")
    public void validatePatientIDDisplayed() throws Exception {
        String pepID = accessServices.getPersonAccountPage().getPEPId();
        commonData.patient = new Patient(pepID);
    }

    @And("^I save the displayed patient ID$")
    public void savePatientIntoExcelFile() throws Exception {
        ExcelFiles excelFiles = new ExcelFiles();
        String pepID = accessServices.getPersonAccountPage().getPEPId();
        excelFiles.savePEPId(pepID);
        commonData.patient = new Patient(pepID);
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


