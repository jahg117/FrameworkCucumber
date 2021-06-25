package stepDefinition.acessServices;

import base.functions.CommonFunctions;
import io.cucumber.java.en.And;
import org.apache.log4j.Logger;
import org.testng.Assert;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.CommonData;
import stepDefinition.shareData.Patient;
import utils.ExcelFiles;
import utils.Values;

import java.lang.reflect.InvocationTargetException;

public class SearchPatient extends ApplicationInstance {

    private CommonData commonData;
    private final Logger logger = Logger.getLogger(CommonFunctions.class);

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
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    String account = accessServices.getAccountsPage().isAccountCreated(commonData.patient.getPatientNamePDC());
                    Assert.assertEquals(commonData.patient.getPatientNamePDC(), account, Values.TXT_MSGPATIENTFOUND);
                } else {
                    logger.info(Values.TXT_MSGDOESNOTREQUIREDEXECUTE + commonData.globalShareData.getExecutionFlag().trim());
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            String account = accessServices.getAccountsPage().isAccountCreated(commonData.patient.getPatientNamePDC());
            Assert.assertEquals(commonData.patient.getPatientNamePDC(), account, Values.TXT_MSGPATIENTFOUND);
        }
    }

    @And("I search the patient at Recently View to check the Valid PAF column from Accounts Page")
    public void searchPatientInView() throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    String account = accessServices.getAccountsPage().isAccountCreated(commonData.patient.getPatientNamePDC());
                    Assert.assertEquals(commonData.patient.getPatientNamePDC(), account, Values.TXT_MSGPATIENTNOTFOUND);
                } else {
                    logger.info(Values.TXT_MSGDOESNOTREQUIREDEXECUTE + commonData.globalShareData.getExecutionFlag().trim());
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            String account = accessServices.getAccountsPage().isAccountCreated(commonData.patient.getPatientNamePDC());
            Assert.assertEquals(commonData.patient.getPatientNamePDC(), account, Values.TXT_MSGPATIENTNOTFOUND);
        }
    }
}


