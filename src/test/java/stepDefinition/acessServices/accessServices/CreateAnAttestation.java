package stepDefinition.acessServices.accessServices;

import io.cucumber.java.en.And;
import org.testng.Assert;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.CommonData;

public class CreateAnAttestation extends ApplicationInstance {
    private CommonData commonData;

    public CreateAnAttestation(CommonData commonData){
        this.commonData = commonData;
    }

    @And("^I select the Attestation tab option to click on new consent")
    public void newAttestationConsent() throws Exception {
        Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(), "The product enrollment page was not displayed");
        accessServices.getProductEnrollmentPage().createNewAttestationConsent();
    }

    @And("^I validate the attestation details are displayed")
    public void attestationDetailsDisplayed() {
        String patientName = commonData.patient.getPatientName();
        String productEnrollment = commonData.productEnrollment.getProductEnrollment();
        Assert.assertTrue(accessServices.getNewConsentAttestationPage().isConsentFormDisplayed(), "The consent form page was not displayed");
        Assert.assertTrue(accessServices.getNewConsentAttestationPage().isPrimaryCompanyDisplayed(), "The primary company field was not displayed");
        Assert.assertTrue(accessServices.getNewConsentAttestationPage().isConsentSourceDisplayed(),"The consent source field was not displayed");
        Assert.assertTrue(accessServices.getNewConsentAttestationPage().isConsentStatusDisplayed(),"The consent status field was not displayed");
        Assert.assertTrue(accessServices.getNewConsentAttestationPage().isConsentDateDisplayed(),"The consent date field was not displayed");
        Assert.assertEquals(accessServices.getNewConsentAttestationPage().getPatientName(), patientName, "The patient name is not matching");
        Assert.assertEquals(accessServices.getNewConsentAttestationPage().getProductEnrollment(), productEnrollment, "The product enrollment is not matching");
    }

    @And("^I fill the mandatory fields from the consent form")
    public void fillConsentForm() throws Exception {
        Assert.assertTrue(accessServices.getNewConsentAttestationPage().isConsentFormDisplayed(), "The consent form page was not displayed");
        accessServices.getNewConsentAttestationPage().fillConsentMandatoryFields();
    }

    @And("^I verify the provider details are displayed")
    public void providerDetailsDisplayed() throws Exception {
        String address = commonData.patient.getAddress();
        String city = commonData.patient.getCity();
        Assert.assertTrue(accessServices.getNewProviderPage().isProviderPageDisplayed(), "The provider page was not displayed");
        Assert.assertTrue(accessServices.getNewProviderPage().isSppNameDisplayed(), "SPP name was not displayed");
        Assert.assertTrue(accessServices.getNewProviderPage().isSppContactNameDisplayed(), "SPP Contact name was not displayed");
        Assert.assertTrue(accessServices.getNewProviderPage().isTableElementDisplayed(address), "The address is not matching");
        Assert.assertTrue(accessServices.getNewProviderPage().isTableElementDisplayed(city), "The city is not matching");
    }

    @And("^I select an existing address option")
    public void selectExistingAddressOption() throws Exception {
        Assert.assertTrue(accessServices.getNewProviderPage().isProviderPageDisplayed(), "The provider page was not displayed");
        accessServices.getNewProviderPage().selectAddressAndSaveConsent();
    }

    @And("^I verify the consent details displayed")
    public void consentDetailsDisplayed() {
        String address = commonData.patient.getAddress();
        String city = commonData.patient.getCity();
        String patientName = commonData.patient.getPatientName();
        String consentType = commonData.consentType.getConsentType();
        String date = commonData.patient.getDate();
        Assert.assertTrue(accessServices.getConsentPage().isConsentPageDisplayed(), "The consent page was not displayed");
        Assert.assertTrue(accessServices.getConsentPage().isConsentDetailDisplayed(address), "The address is not matching");
        Assert.assertTrue(accessServices.getConsentPage().isConsentDetailDisplayed(patientName), "The patient name is not matching");
        Assert.assertTrue(accessServices.getConsentPage().isConsentDetailDisplayed(city), "The city is not matching");
        Assert.assertTrue(accessServices.getConsentPage().isConsentDetailDisplayed(consentType), "The consent type is not matching");
    }
}
