package stepDefinition.acessServices;

import io.cucumber.java.en.And;
import org.apache.tools.ant.types.selectors.SelectSelector;
import pageObject.ApplicationInstance;

import java.util.HashMap;

public class CreateCase extends ApplicationInstance {

    @And("^I click on new Case")
    public void clickNewCase() throws Exception {
        accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
        String productEnrollment = accessServices.getProductEnrollmentPage().getProductEnrollmentNumber();
        accessServices.getProductEnrollmentPage().clickOnNewCase();
    }

    @And("^I select the case type \"([^\"]*)\"$")
    public void selectCaseType(String caseOption) throws Exception {
        accessServices.getNewCasePage().isNewCaseFormDisplayed();
        accessServices.getNewCasePage().selectCaseOption(caseOption);
        accessServices.getNewCasePage().clickContinueButton();
    }

    @And("^I fill the new case mandatory fields \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void fillNewCaseMandatoryFields(String channel, String caseStatus, String caseSubType, String discussTopic, String cardNumber) throws Exception {
        HashMap<String, String> caseForm = new HashMap<>();
        caseForm.put("Channel", channel);
        caseForm.put("CaseStatus", caseStatus);
        caseForm.put("CaseSubType", caseSubType);
        caseForm.put("DiscussTopic", discussTopic);
        caseForm.put("CardNumber", cardNumber);
        accessServices.getCaseInformationPage().isCaseOptionPageDisplayed();
        accessServices.getCaseInformationPage().fillCaseInformationForm(caseForm);
        accessServices.getCaseInformationPage().clickSaveButton();
        if(accessServices.getUpdateCaseContactWizardPage().isCaseContactWizardPageDisplayed())
            accessServices.getSubTabsPage().closeLastSubTab();
        accessServices.getCasePage().isCasePageDisplayed();
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
       accessServices.getCaseInformationPage().fillCaseInformationForm();
       accessServices.getCaseInformationPage().clickSaveButton();
       accessServices.getUpdateCaseContactWizardPage().isCaseContactWizardPageDisplayed();
       accessServices.getSubTabsPage().closeLastSubTab();
    }
}
