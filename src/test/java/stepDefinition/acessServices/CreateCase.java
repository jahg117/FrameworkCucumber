package stepDefinition.acessServices;

import io.cucumber.java.en.And;
import pageObject.ApplicationInstance;

public class CreateCase extends ApplicationInstance {

    @And("^I click on new Case")
    public void clickNewCase() throws Exception {
        accessServices.getPersonAccountPage().clickNewCase();
    }

    @And("^I create a/an \"([^\"]*)\" case$")
    public void createCaseType(String caseOption) throws Exception {
       String productEnrollment = "";
       String product = "";
       if(!caseOption.equalsIgnoreCase("Interaction")){
            accessServices.getPersonAccountPage().clickNewProductEnrollment();
            if(caseOption.equalsIgnoreCase("In-Home Nurse Support")||
               caseOption.equalsIgnoreCase("Nurse Support")||
               caseOption.equalsIgnoreCase("Denied Patient Savings")||
               caseOption.equalsIgnoreCase("Claims Support")||
               caseOption.equalsIgnoreCase("Free Limited Supply Program")||
               caseOption.equalsIgnoreCase("Insurance Authorization")||
               caseOption.equalsIgnoreCase("Patient Savings Program")||
               caseOption.equalsIgnoreCase("Pharmacy Coordination")){
                product = "Fasenra";
            }
            product = accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(product);
            accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
            accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
            productEnrollment = accessServices.getProductEnrollmentPage().getProductEnrollmentNumber();
            accessServices.getPersonAccountPage().clickNewCase();
            accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
            accessServices.getProductEnrollmentPage().clickNewCase();
            //accessServices.getSubTabsPage().closeSubTab(0);
        }
        //clickNewCase();
        accessServices.getNewCasePage().isNewCaseFormDisplayed();
        accessServices.getNewCasePage().selectCaseOption(caseOption);
        accessServices.getNewCasePage().clickNextButton();
        accessServices.getCaseInformationPage().isCaseOptionPageDisplayed();
        accessServices.getCaseInformationPage().fillPatientDetails(caseOption, productEnrollment);
        accessServices.getCaseInformationPage().fillRandomMandatoryFields(caseOption, product);
        accessServices.getCaseInformationPage().clickSaveButton();
    }
}
