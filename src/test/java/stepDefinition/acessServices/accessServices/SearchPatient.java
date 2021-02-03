package stepDefinition.acessServices.accessServices;

import io.cucumber.java.en.And;
import org.testng.Assert;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.CommonData;

public class SearchPatient extends ApplicationInstance {

    private CommonData commonData;

    public SearchPatient(CommonData commonData){
        this.commonData = commonData;
    }

    @And("^I validate the patient account was created$")
    public void validatePatientAccountCreated() throws Exception {
        String account = accessServices.getAccountsPage().isAccountCreated(commonData.patient.getPatientName());
        Assert.assertEquals(commonData.patient.getPatientName(), account, "The patient account created was not found");
    }
}
