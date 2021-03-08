package stepDefinition.acessServices;

import io.cucumber.java.en.And;
import org.apache.tools.ant.types.selectors.SelectSelector;
import org.testng.Assert;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.*;

import java.util.HashMap;

public class CreateCase extends ApplicationInstance {

    private CommonData commonData;

    public CreateCase(CommonData commonData){
        this.commonData = commonData;
    }

    @And("^I click on new Case from the product enrollment page")
    public void clickNewCase() throws Exception {
        accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
        accessServices.getProductEnrollmentPage().clickOnNewCase();
    }

    @And("^I click on new Case from the person account page")
    public void clickNewCasePersonAccount() throws Exception {
        accessServices.getPersonAccountPage().clickNewCase();
    }

    @And("^I select the case type option \"([^\"]*)\"$")
    public void selectCaseTypeOptionsPage(String caseOption) throws Exception {
        accessServices.getNewCaseOptionsPage().isFormCaseOptionsPageDisplayed();
        accessServices.getNewCaseOptionsPage().selectCaseOption("Interaction");
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
        HashMap<String, String> caseFormInformation = accessServices.getCaseInformationPage().fillCaseInformationForm(caseForm);
        String product = accessServices.getCaseInformationPage().clickSaveButton();
        accessServices.getUpdateCaseContactWizardPage().closeCaseContactWizardPage();
        commonData.caseForm = new Case(caseFormInformation);
        commonData.product = new Product(product);
    }

    @And("^I fill the new interaction mandatory fields \"([^\"]*)\" \"([^\"]*)\"$")
    public void fillNewInteractionMandatoryFields(String channel, String caseStatus) throws Exception {
        HashMap<String, String> interaction = new HashMap<>();
        interaction.put("Channel", channel);
        interaction.put("CaseStatus", caseStatus);
        accessServices.getCaseInformationPage().isCaseOptionPageDisplayed();
        HashMap<String, String> interactionForm = accessServices.getCaseInformationPage().fillCaseInteractionForm(interaction);
        accessServices.getCaseInformationPage().clickSaveInteraction();
        commonData.interaction = new Interaction(interactionForm);
    }

    @And("^I validate the correct case interaction information displayed$")
    public void validateCaseInteractionInformation() throws Exception {
        accessServices.getCasePage().isCasePageDisplayed();
        Assert.assertEquals(accessServices.getCasePage().getPatientName(), commonData.patient.getPatientName(), "The patient name is not matching");
        Assert.assertEquals(accessServices.getCasePage().getCaseStatus(), commonData.interaction.getCaseStatus(), "The case status is not matching");
        Assert.assertEquals(accessServices.getCasePage().getChannel(), commonData.interaction.getChannel(), "The channel is not matching");
    }

    @And("^I validate the correct case information is displayed$")
    public void validateCaseInformation() throws Exception {
        accessServices.getCasePage().isCasePageDisplayed();
        Assert.assertEquals(accessServices.getCasePage().getEnrolledPatient(), commonData.patient.getPatientName(), "The enrolled patient is not matching");
        Assert.assertEquals(accessServices.getCasePage().getCaseStatus(), commonData.caseForm.getCaseStatus(), "The case status is not matching");
        Assert.assertEquals(accessServices.getCasePage().getProductEnrollment(), commonData.productEnrollment.getProductEnrollment(), "The product enrollment is not matching");
        Assert.assertEquals(accessServices.getCasePage().getChannel(), commonData.caseForm.getChannel(), "The channel is not matching");
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
       accessServices.getUpdateCaseContactWizardPage().closeCaseContactWizardPage();
    }
}
