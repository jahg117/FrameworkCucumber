package pageObject.application;

import base.driverInitialize.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObject.pages.accessServices.AccessServicesHomePage;
import pageObject.pages.accessServices.CustomerLookupPage;
import pageObject.pages.accessServices.NewAccountPage;
import pageObject.pages.accessServices.NewPatientConsumerCaregiverPage;

public class AccessServices {
    private WebDriver driver;

    private AccessServicesHomePage accessServicesHomePage;
    private CustomerLookupPage customerLookupPage;
    private NewAccountPage newAccountPage;
    private NewPatientConsumerCaregiverPage newPatientConsumerCaregiverPage;

    public AccessServices(){
        this.driver = DriverFactory.getDriver();
        accessServicesHomePage = PageFactory.initElements(driver, AccessServicesHomePage.class);
        customerLookupPage = PageFactory.initElements(driver, CustomerLookupPage.class);
        newAccountPage = PageFactory.initElements(driver, NewAccountPage.class);
        newPatientConsumerCaregiverPage = PageFactory.initElements(driver, NewPatientConsumerCaregiverPage.class);
    }

    public AccessServicesHomePage getAccessServicesHomePage() {
        return accessServicesHomePage;
    }

    public CustomerLookupPage getCustomerLookupPage() {
        return customerLookupPage;
    }

    public NewAccountPage getNewAccountPage() {
        return newAccountPage;
    }

    public NewPatientConsumerCaregiverPage getNewPatientConsumerCaregiverPage() {
        return newPatientConsumerCaregiverPage;
    }
}
