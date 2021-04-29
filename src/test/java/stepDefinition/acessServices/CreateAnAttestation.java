package stepDefinition.acessServices;

import io.cucumber.java.en.And;
import org.testng.Assert;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.CommonData;

public class CreateAnAttestation extends ApplicationInstance {
    private CommonData commonData;

    public CreateAnAttestation(CommonData commonData) throws Exception {
        this.commonData = commonData;
    }

    @And("^I select the Attestation tab option to click on new consent")
    public void newAttestationConsent() throws Exception {
        Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(), "The product enrollment page was not displayed");
        accessServices.getProductEnrollmentPage().createNewAttestationConsent();
    }

    @And("^I validate the attestation details are displayed")
    public void attestationDetailsDisplayed() throws Exception {
        String patientName = commonData.patient.getPatientName();
        String productEnrollment = commonData.productEnrollment.getProductEnrollment();
        Assert.assertTrue(accessServices.getNewConsentAttestationPage().isConsentFormDisplayed(), "The consent form page was not displayed");
        Assert.assertTrue(accessServices.getNewConsentAttestationPage().isPrimaryCompanyDisplayed(), "The primary company field was not displayed");
        Assert.assertTrue(accessServices.getNewConsentAttestationPage().isConsentSourceDisplayed(), "The consent source field was not displayed");
        Assert.assertTrue(accessServices.getNewConsentAttestationPage().isConsentStatusDisplayed(), "The consent status field was not displayed");
        Assert.assertTrue(accessServices.getNewConsentAttestationPage().isConsentDateDisplayed(), "The consent date field was not displayed");
        Assert.assertEquals(accessServices.getNewConsentAttestationPage().getPatientName(), patientName, "The patient name is not matching");
        Assert.assertEquals(accessServices.getNewConsentAttestationPage().getProductEnrollment(), productEnrollment, "The product enrollment is not matching");
    }

    @And("^I fill the mandatory fields from the consent form")
    public void fillConsentForm() throws Exception {
        Assert.assertTrue(accessServices.getNewConsentAttestationPage().isConsentFormDisplayed(), "The consent form page was not displayed");
        accessServices.getNewConsentAttestationPage().fillConsentMandatoryFields();
    }

    @And("^I fill the DSI FLSP mandatory fields from the consent form")
    public void fillFSIFLSPForm() throws Exception {
        Assert.assertTrue(accessServices.getNewConsentAttestationPage().isConsentFormDisplayed(), "The consent form page was not displayed");
        accessServices.getNewConsentAttestationPage().fillDSIFLSPMandatoryFields();
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
    public void consentDetailsDisplayed() throws Exception {
        String address = commonData.patient.getAddress();
        String city = commonData.patient.getCity();
        String patientName = commonData.patient.getPatientName();
        String consentType = commonData.consentType.getConsentType();
        Assert.assertTrue(accessServices.getConsentPage().isConsentPageDisplayed(), "The consent page was not displayed");
        Assert.assertTrue(accessServices.getConsentPage().isConsentDetailDisplayed(address), "The address is not matching");
        Assert.assertTrue(accessServices.getConsentPage().isConsentDetailDisplayed(patientName), "The patient name is not matching");
        Assert.assertTrue(accessServices.getConsentPage().isConsentDetailDisplayed(city), "The city is not matching");
        Assert.assertTrue(accessServices.getConsentPage().isConsentDetailDisplayed(consentType), "The consent type is not matching");
    }

    @And("^I verify the DSI consent details displayed")
    public void consentDetailsDSIDisplayed() throws Exception {
        String patientName = commonData.patient.getPatientName();
        String providerFirstName = commonData.careTeamMember.getFirstName();
        String providerLastName = commonData.careTeamMember.getLastName();
        String address = commonData.careTeamMember.getAddress();
        String city = commonData.careTeamMember.getCity();
        String state = commonData.careTeamMember.getState();
        String zipcode = commonData.careTeamMember.getZipcode();
        Assert.assertTrue(accessServices.getConsentPage().isConsentPageDisplayed(), "The consent page was not displayed");
        Assert.assertTrue(accessServices.getConsentPage().isConsentDetailDisplayed(patientName), "The patient name is not matching");
        Assert.assertTrue(accessServices.getConsentPage().isConsentDetailDisplayed(providerFirstName), "The provider first name is not matching");
        Assert.assertTrue(accessServices.getConsentPage().isConsentDetailDisplayed(providerLastName), "The provider last name is not matching");
        Assert.assertTrue(accessServices.getConsentPage().isConsentDetailDisplayed(address), "The address is not matching");
        Assert.assertTrue(accessServices.getConsentPage().isConsentDetailDisplayed(city), "The city is not matching");
        Assert.assertTrue(accessServices.getConsentPage().isConsentDetailDisplayed(state), "The state is not matching");
        Assert.assertTrue(accessServices.getConsentPage().isConsentDetailDisplayed(zipcode), "The city is not matching");
    }

    @And("^I select an existing HCP")
    public void selectHCP() throws Exception {
        accessServices.getNewDSIFLSPAttestationPage().isNewDSIFLSAttestationPageDisplayed();
        accessServices.getNewDSIFLSPAttestationPage().selectHCP();
        accessServices.getNewDSIFLSPAttestationPage().selectHCPAddress();
        accessServices.getNewDSIFLSPAttestationPage().clickSaveButton();
    }
}
