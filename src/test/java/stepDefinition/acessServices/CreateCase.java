package stepDefinition.acessServices;

import io.cucumber.java.en.And;
import pageObject.ApplicationInstance;

public class CreateCase extends ApplicationInstance {

    @And("^I click on new Case")
    public void clickNewCase() throws Exception {
        accessServices.getProductEnrollmentPage().clickOnNewCase();
    }

    @And("^I create a/an \"([^\"]*)\" case$")
    public void createCaseType(String caseOption) throws Exception {
       accessServices.getPersonAccountPage().clickNewProductEnrollment();
       String product = accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm("fasenra");
       accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
       accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
       String productEnrollment = accessServices.getProductEnrollmentPage().getProductEnrollmentNumber();
       accessServices.getProductEnrollmentPage().clickOnNewCase();
       accessServices.getNewCasePage().isNewCaseFormDisplayed();
       accessServices.getNewCasePage().selectCaseOption(caseOption);
       accessServices.getNewCasePage().clickContinueButton();
       accessServices.getCaseInformationPage().isCaseOptionPageDisplayed();
       accessServices.getCaseInformationPage().fillRandomMandatoryFields(caseOption, product);
       accessServices.getCaseInformationPage().clickSaveButton();
       accessServices.getUpdateCaseContactWizardPage().isCaseContactWizardPageDisplayed();
       accessServices.getSubTabsPage().closeLastSubTab();
    }
}
