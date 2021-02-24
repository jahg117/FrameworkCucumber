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
       if(!caseOption.equalsIgnoreCase("Interaction")){
            accessServices.getPersonAccountPage().clickNewProductEnrollment();
            accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm("AZ");
            accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
            accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
            productEnrollment = accessServices.getProductEnrollmentPage().getProductEnrollmentNumber();
            accessServices.getSubTabsPage().closeSubTab(0);
        }
        clickNewCase();
        accessServices.getNewCasePage().isNewCaseFormDisplayed();
        accessServices.getNewCasePage().selectCaseOption(caseOption);
        accessServices.getNewCasePage().clickNextButton();
        accessServices.getCaseInformationPage().isCaseOptionPageDisplayed();
        accessServices.getCaseInformationPage().fillPatientDetails(caseOption, productEnrollment);
        accessServices.getCaseInformationPage().fillRandomMandatoryFields(caseOption);
        accessServices.getCaseInformationPage().clickSaveButton();
    }
}
