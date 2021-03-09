package pageObject.application;

import base.driverInitialize.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObject.pages.accessServices.*;
import pageObject.pages.accessServices.Cases.*;

public class AccessServices {
    private WebDriver driver;

    private AccessServicesHomePage accessServicesHomePage;
    private CustomerLookupPage customerLookupPage;
    private NewAccountPage newAccountPage;
    private NewPatientConsumerCaregiverPage newPatientConsumerCaregiverPage;
    private PersonAccountPage personAccountPage;
    private CreateNewEnrollmentPage createNewEnrollmentPage;
    private ProductEnrollmentPage productEnrollmentPage;
    private NewConsentWizardPage newConsentWizardPage;
    private AccountsRecentlyViewedPage accountsRecentlyViewedPage;
    private AccountsPage accountsPage;
    private NewConsentPage newConsentPage;
    private NewConsentAttestationPage newConsentAttestationPage;
    private NewProviderPage newProviderPage;
    private ConsentPage consentPage;
    private NewDSIFLSPAttestationPage newDSIFLSPAttestationPage;
    private SubTabsPage subTabsPage;
    private ProductEnrollmentsTablePage productEnrollmentsTablePage;
    private NewCasePage newCasePage;
    private CasePage casePage;
    private CaseInformationPage caseInformationPage;
    private NewCaseOptionsPage newCaseOptionsPage;
    private CasesListPage casesListPage;
    private NewHCAWizardPage newHCAWizardPage;
    private NewHCPWizardPage newHCPWizardPage;
    private NewCPCWizardPage newCPCWizardPage;
    private NewEmployeeWizardPage newEmployeeWizardPage;
    private ProductsPage productsPage;
    private UpdateCaseContactWizardPage updateCaseContactWizardPage;
    private GlobalCommonGeneralStepsPage globalCommonGeneralSteps;

    public AccessServices(){
        this.driver = DriverFactory.getDriver();
        accessServicesHomePage = PageFactory.initElements(driver, AccessServicesHomePage.class);
        customerLookupPage = PageFactory.initElements(driver, CustomerLookupPage.class);
        newAccountPage = PageFactory.initElements(driver, NewAccountPage.class);
        newPatientConsumerCaregiverPage = PageFactory.initElements(driver, NewPatientConsumerCaregiverPage.class);
        personAccountPage = PageFactory.initElements(driver, PersonAccountPage.class);
        createNewEnrollmentPage = PageFactory.initElements(driver, CreateNewEnrollmentPage.class);
        productEnrollmentPage = PageFactory.initElements(driver, ProductEnrollmentPage.class);
        newConsentWizardPage = PageFactory.initElements(driver, NewConsentWizardPage.class);
        accountsRecentlyViewedPage = PageFactory.initElements(driver, AccountsRecentlyViewedPage.class);
        accountsPage = PageFactory.initElements(driver, AccountsPage.class);
        newConsentPage = PageFactory.initElements(driver, NewConsentPage.class);
        newConsentAttestationPage = PageFactory.initElements(driver, NewConsentAttestationPage.class);
        newProviderPage = PageFactory.initElements(driver, NewProviderPage.class);
        consentPage = PageFactory.initElements(driver, ConsentPage.class);
        newDSIFLSPAttestationPage = PageFactory.initElements(driver, NewDSIFLSPAttestationPage.class);
        subTabsPage = PageFactory.initElements(driver, SubTabsPage.class);
        productEnrollmentsTablePage = PageFactory.initElements(driver, ProductEnrollmentsTablePage.class);
        newCasePage = PageFactory.initElements(driver, NewCasePage.class);
        casePage = PageFactory.initElements(driver, CasePage.class);
        caseInformationPage = PageFactory.initElements(driver, CaseInformationPage.class);
        newCaseOptionsPage = PageFactory.initElements(driver, NewCaseOptionsPage.class);
        casesListPage = PageFactory.initElements(driver, CasesListPage.class);
        newHCAWizardPage = PageFactory.initElements(driver, NewHCAWizardPage.class);
        newHCPWizardPage = PageFactory.initElements(driver, NewHCPWizardPage.class);
        newCPCWizardPage = PageFactory.initElements(driver, NewCPCWizardPage.class);
        newEmployeeWizardPage = PageFactory.initElements(driver, NewEmployeeWizardPage.class);
        productsPage = PageFactory.initElements(driver, ProductsPage.class);
        updateCaseContactWizardPage = PageFactory.initElements(driver, UpdateCaseContactWizardPage.class);
        globalCommonGeneralSteps = PageFactory.initElements(driver, GlobalCommonGeneralStepsPage.class);
    }

    public AccessServicesHomePage getAccessServicesHomePage() {
        return accessServicesHomePage;
    }
    public CustomerLookupPage getCustomerLookupPage() {
        return customerLookupPage;
    }
    public NewAccountPage getNewAccountPage() { return newAccountPage; }
    public NewPatientConsumerCaregiverPage getNewPatientConsumerCaregiverPage() { return newPatientConsumerCaregiverPage; }
    public PersonAccountPage getPersonAccountPage() { return personAccountPage; }
    public CreateNewEnrollmentPage getCreateNewEnrollmentPage() { return  createNewEnrollmentPage; }
    public ProductEnrollmentPage getProductEnrollmentPage() { return  productEnrollmentPage; }
    public NewConsentWizardPage getNewConsentWizard() { return  newConsentWizardPage; }
    public AccountsRecentlyViewedPage getAccountsRecentlyViewedPage() { return  accountsRecentlyViewedPage; }
    public AccountsPage getAccountsPage() { return accountsPage; }
    public NewConsentPage getNewConsentPage() { return  newConsentPage; }
    public NewConsentAttestationPage getNewConsentAttestationPage() { return newConsentAttestationPage; }
    public NewProviderPage getNewProviderPage() { return newProviderPage; }
    public ConsentPage getConsentPage() { return consentPage; }
    public NewDSIFLSPAttestationPage getNewDSIFLSPAttestationPage() { return newDSIFLSPAttestationPage; }
    public SubTabsPage getSubTabsPage() { return subTabsPage; }
    public ProductEnrollmentsTablePage getProductEnrollmentsTablePage() { return productEnrollmentsTablePage; }
    public NewCasePage getNewCasePage() { return newCasePage; }
    public CasePage getCasePage() { return casePage; }
    public CaseInformationPage getCaseInformationPage() { return caseInformationPage; }
    public CasesListPage getCasesListPage() { return casesListPage; }
    public NewCaseOptionsPage getNewCaseOptionsPage() { return newCaseOptionsPage; }
    public NewHCAWizardPage getNewHCAWizardPage() { return newHCAWizardPage; }
    public NewHCPWizardPage getNewHCPWizardPage() { return newHCPWizardPage; }
    public NewCPCWizardPage getNewCPCWizardPage() { return newCPCWizardPage; }
    public NewEmployeeWizardPage getNewEmployeeWizardPage() { return newEmployeeWizardPage; }
    public ProductsPage getProductsPage() { return productsPage; }
    public UpdateCaseContactWizardPage getUpdateCaseContactWizardPage() { return updateCaseContactWizardPage; }
    public GlobalCommonGeneralStepsPage getGlobalCommonGeneralStepsPage() { return globalCommonGeneralSteps; }
}
