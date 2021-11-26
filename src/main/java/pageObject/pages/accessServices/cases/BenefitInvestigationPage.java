package pageObject.pages.accessServices.cases;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.application.AccessServices;
import pageObject.pages.accessServices.productEnrollment.ProductEnrollmentPage;
import pageObject.pages.salesforce.HomePage;
import utils.Values;

import java.util.ArrayList;
import java.util.List;

public class BenefitInvestigationPage extends CommonFunctions {
    ProductEnrollmentPage productEnrollmentPage = new ProductEnrollmentPage();
    private Logger logger = Logger.getLogger(CommonFunctions.class);

    @FindBy(xpath = "//*[@id='banner']")
    private WebElement bannerReverificationSearch;

    @FindBy(xpath = "//label[normalize-space(text())='Case Address']/following::select")
    private List<WebElement> caseRVFilters;

    @FindBy(xpath = "//img[contains(@src,'right')]")
    private List<WebElement> caseRVArrowFilters;

    @FindBy(xpath = "//*[@value='Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//th/div[normalize-space(text())='A360 Patient ID']/following::input[@type='checkbox'][not(@disabled='disabled')]")
    private List<WebElement> checkboxListRV;

    @FindBy(xpath = "//input[@value='AssociateToCase']")
    private WebElement buttonAssociateToCase;

    @FindBy(xpath = "//iframe[@class='cACS_ReverificationFilter']")
    private WebElement iframeReverificationScreen;

    @FindBy(xpath = "//th/div[normalize-space(text())='A360 Patient ID']/following::td/a")
    private List<WebElement> patientListRV;

    @FindBy(xpath = "//span[normalize-space(text())='Processing...']")
    private WebElement imgProcessing;

    @FindBy(xpath = "//*[starts-with(@data-row-key-value,'500')]")
    private List<WebElement> caseOfEnrolledPatientsTable;

    @FindBy(xpath = "//th[@data-label='Case Number']//a")
    private List<WebElement> colCaseNumberList;

    @FindBy(xpath = "//h2//a[normalize-space(text())='Case']/following::*[starts-with(@class,'test-id__field-value')][5]")
    private List<WebElement> caseStatusBI;

    @FindBy(xpath = "//h2//a[normalize-space(text())='Case']/following::*[starts-with(@class,'test-id__field-value')][6]")
    private List<WebElement> caseSubTypeBI;

    @FindBy(xpath = "//h2//a[normalize-space(text())='Case']/following::*[starts-with(@class,'test-id__field-value')][8]")
    private List<WebElement> caseDateTimeOpenedBI;

    @FindBy(xpath = "//*[starts-with(@title,'PE-')]")
    private WebElement casePatientProductEnrollmentBI;

    @FindBy(xpath = "//h3//span[normalize-space(text())='Enrollment']/following::*[starts-with(@class,'test-id__field-value')][1]//span[not(@class='slds-assistive-text')]")
    private List<WebElement> peProductName;

    @FindBy(xpath = "//h3//span[normalize-space(text())='Enrollment']/following::*[starts-with(@class,'test-id__field-value')][2]//lightning-formatted-text")
    private List<WebElement> peTherapeuticArea;

    @FindBy(xpath = "//h2//a[normalize-space(text())='Case']/following::*[starts-with(@class,'test-id__field-value')][11]")
    private List<WebElement> caseProductName;

    @FindBy(xpath = "//h2//a[normalize-space(text())='Case']/following::*[starts-with(@class,'test-id__field-value')][12]")
    private List<WebElement> caseTherapeuticArea;

    @FindBy(xpath = "//h2//a[normalize-space(text())='Case']/following::*[starts-with(@class,'test-id__field-value')][3]")
    private List<WebElement> caseChannelBI;

    @FindBy(xpath = "//h2//a[normalize-space(text())='Case']/following::*[starts-with(@class,'test-id__field-value')][2]")
    private List<WebElement> caseOwnerBI;

    @FindBy(xpath = "//a[normalize-space(text())='Benefit Investigation - Case on Hold']")
    private List<WebElement> caseTaskBI;

    @FindBy(xpath = "//span[normalize-space(text())='Assigned To']/following::*[starts-with(@class,'test-id__field-value')][1]")
    private List<WebElement> taskAssignedToBI;

    @FindBy(xpath = "//span[normalize-space(text())='Assigned To']/following::*[starts-with(@class,'test-id__field-value')][15]")
    private List<WebElement> taskNextStepsBI;

    @FindBy(xpath = "//span[normalize-space(text())='Assigned To']/following::*[starts-with(@class,'test-id__field-value')][17]")
    private List<WebElement> taskPriorityBI;

    @FindBy(xpath = "//span[normalize-space(text())='Assigned To']/following::*[starts-with(@class,'test-id__field-value')][17]")
    private List<WebElement> taskCaseTypeBI;

    @FindBy(xpath = "//span[normalize-space(text())='Assigned To']/following::*[starts-with(@class,'test-id__field-value')][4]")
    private List<WebElement> taskAccountBI;

    @FindBy(xpath = "//img[@title='Case']/following::a[@title='Show 2 more actions']")
    private WebElement taskPayerNameBI;

    @FindBy(xpath = "//a[@title='Update Case Team Member Wizard']")
    private List<WebElement> updateWizardTab;

    @FindBy(xpath = "//span[@title='Cases of Enrolled Patients']")
    private WebElement tableEnrolledPatient;

    @FindBy(xpath = "//a//*[@title='Member Role']")
    private WebElement colMemberRole;
    HomePage homePage = new HomePage();
    List<WebElement> genericWebElementList = new ArrayList<>();
    List<WebElement> genericWebElementListPE = new ArrayList<>();
    List<WebElement> genericWebElementListTA = new ArrayList<>();


    int globalIdxVal = 0;

    public void validateAndClickBICase(String status, String ntd) throws Exception {
        waitForElementClickable(tableEnrolledPatient, shortWait());
        for (int i = 0; i < caseOfEnrolledPatientsTable.size(); i++) {
            if (caseOfEnrolledPatientsTable.get(i).getText().contains(status.trim())) {
                clickAndMoveToElementClickable(colCaseNumberList.get(i), shortWait());
                if (updateWizardTab.isEmpty()) {
                    if (waitForElementClickable(colMemberRole, shortWait())) {
                        closeLastSubTabSF(shortWait());
                    }
                }
                filterNextTreatmenDayCase(status, ntd);
                break;
            }
        }
    }

    public String filterNextTreatmenDayCase(String status, String ntdDate) throws Exception {
        boolean ntdNotRequired = false;
        List<String> ntdPEPIDList = new ArrayList<>();
        for (int i = 0; i < Values.globalNTDValidationList.size(); i++) {
            ntdPEPIDList = List.of(Values.globalNTDValidationList.get(i).split(Values.REGEX_PIPE));
            int idxNumber = 0;
            if (ntdPEPIDList.size() >= 1) {
                idxNumber = 1;
            }

            switch (ntdPEPIDList.get(idxNumber).toUpperCase().trim()) {
                case "E":
                    executeBICaseDataValidation(status, ntdDate);
                    executeBITaskDataValidation(status, ntdDate);
                    //closeLastSubTabSF(shortWait());
                    break;

                case "NE":
                    executeBICaseDataValidation(status, Values.TXT_NTDNOEDIT);
                    executeBITaskDataValidation(status, Values.TXT_NTDNOEDIT);
                    //closeLastSubTabSF(shortWait());
                    break;

                default:
                    break;
            }
        }
        homePage.closeOpenTabs();
        return "Lol";
    }

    public void executeBICaseDataValidation(String status, String ntd) throws Exception {
        String[] ntdModified = ntd.split(Values.REGEX_UNDERSCORE);
        if (caseStatusBI.isEmpty()) {
            genericWebElementList = getVisibleElements(getWebElementList(By.xpath("//h2//a[normalize-space(text())='Case']/following::*[starts-with(@class,'test-id__field-value')][5]")));
        }else{
            genericWebElementList = getVisibleElements(caseStatusBI);
        }
        scrollToElement(genericWebElementList.get(globalIdxVal));
        if (genericWebElementList.get(globalIdxVal).getText().trim().equalsIgnoreCase(status.trim())) {
            logger.info(Values.TXT_BICORRECTVALUE);
        } else {
            logger.warn(Values.TXT_BICORRECTVALUE);
        }

        genericWebElementList = getVisibleElements(caseSubTypeBI);
        scrollToElement(genericWebElementList.get(globalIdxVal));
        if (genericWebElementList.get(globalIdxVal).getText().trim().equalsIgnoreCase(Values.TXT_CASESUBTYPEVALUE.trim())) {
            logger.info(Values.TXT_BICORRECTVALUE + status);
        } else {
            logger.warn(Values.TXT_BICORRECTVALUE + status);
        }

        genericWebElementList = getVisibleElements(caseDateTimeOpenedBI);
        scrollToElement(genericWebElementList.get(globalIdxVal));
        if (genericWebElementList.get(globalIdxVal).getText().trim().contains(ntdModified[globalIdxVal])) {
            logger.info(Values.TXT_BICORRECTVALUE + ntdModified[globalIdxVal]);
        } else {
            logger.warn(Values.TXT_BICORRECTVALUE + ntdModified[globalIdxVal]);
        }
        clickAndMoveToElementClickable(casePatientProductEnrollmentBI, shortWait());
        //===========================================================GETTING INFO FROM PE TO COMPARE WITH THE BI CASE
        if (peProductName.isEmpty()) {
            genericWebElementListPE = getVisibleElements(getWebElementList(By.xpath("//h3//span[normalize-space(text())=" +
                    "'Enrollment']/following::*[starts-with(@class,'test-id__field-value')][1]//span[not(@class='slds-assistive-text')]")));
        }else{
            genericWebElementListPE = getVisibleElements(peProductName);
        }
        scrollToElement(genericWebElementListPE.get(globalIdxVal));
        String productNameValue = productEnrollmentPage.getProductEnrollmentValues(genericWebElementListPE.get(globalIdxVal));

        genericWebElementListTA = getVisibleElements(peTherapeuticArea);
        scrollToElement(genericWebElementListTA.get(globalIdxVal));
        String therapeuticAreaValue = productEnrollmentPage.getProductEnrollmentValues(genericWebElementListTA.get(globalIdxVal));
        closeLastSubTabSF(shortWait());

        genericWebElementList = getVisibleElements(caseProductName);
        scrollToElement(genericWebElementList.get(globalIdxVal));
        if (productNameValue.trim().equalsIgnoreCase(genericWebElementList.get(globalIdxVal).getText())) {
            logger.info(Values.TXT_BICORRECTVALUE + genericWebElementList.get(globalIdxVal).getText());
        } else {
            logger.warn(Values.TXT_BICORRECTVALUE);
        }

        genericWebElementList = getVisibleElements(caseTherapeuticArea);
        scrollToElement(genericWebElementList.get(globalIdxVal));
        if (therapeuticAreaValue.trim().equalsIgnoreCase(genericWebElementList.get(globalIdxVal).getText())) {
            logger.info(Values.TXT_BICORRECTVALUE + genericWebElementList.get(globalIdxVal).getText());
        } else {
            logger.warn(Values.TXT_BICORRECTVALUE + genericWebElementList.get(globalIdxVal).getText());
        }

        genericWebElementList = getVisibleElements(caseChannelBI);
        scrollToElement(genericWebElementList.get(globalIdxVal));
        if (Values.globalStringValues.trim().equalsIgnoreCase(genericWebElementList.get(globalIdxVal).getText().trim())) {
            logger.info(Values.TXT_BICORRECTVALUE + genericWebElementList.get(globalIdxVal).getText());
        } else {
            logger.warn(Values.TXT_BICORRECTVALUE + genericWebElementList.get(globalIdxVal).getText());
        }

        genericWebElementList = getVisibleElements(caseOwnerBI);
        scrollToElement(genericWebElementList.get(globalIdxVal));
        if (Values.TXT_CASEOWNERVALUE.trim().equalsIgnoreCase(genericWebElementList.get(globalIdxVal).getText().trim())) {
            logger.info(Values.TXT_BICORRECTVALUE + genericWebElementList.get(globalIdxVal).getText());
        } else {
            logger.warn(Values.TXT_BICORRECTVALUE + genericWebElementList.get(globalIdxVal).getText());
        }
    }

    public void executeBITaskDataValidation(String status, String ntd) throws Exception {
        //clickAndMoveMethodsWebElement(taskPayerNameBI);
        if (caseTaskBI.isEmpty()) {
            genericWebElementList = getVisibleElements(getWebElementList(By.xpath("//h2//a[normalize-space(text())='Case']/following::*[starts-with(@class,'test-id__field-value')][5]")));
        }else{
            genericWebElementList = getVisibleElements(caseTaskBI);
        }
        scrollToElement(genericWebElementList.get(globalIdxVal));
        if (waitForElementClickable(genericWebElementList.get(globalIdxVal), shortWait())) {
            clickMethod(genericWebElementList.get(globalIdxVal));
        }

        genericWebElementList = getVisibleElements(taskAssignedToBI);
        scrollToElement(genericWebElementList.get(globalIdxVal));
        if (Values.TXT_CASEOWNERVALUE.trim().equalsIgnoreCase(genericWebElementList.get(globalIdxVal).getText().trim())) {
            logger.info(Values.TXT_BICORRECTVALUE + genericWebElementList.get(globalIdxVal).getText());
        } else {
            logger.warn(Values.TXT_BICORRECTVALUE + genericWebElementList.get(globalIdxVal).getText());
        }

        genericWebElementList = getVisibleElements(taskNextStepsBI);
        scrollToElement(genericWebElementList.get(globalIdxVal));
        if (Values.TXT_TASKBINEXTSTEP.trim().equalsIgnoreCase(genericWebElementList.get(globalIdxVal).getText().trim())) {
            logger.info(Values.TXT_BICORRECTVALUE + genericWebElementList.get(globalIdxVal).getText());
        } else {
            logger.warn(Values.TXT_BICORRECTVALUE + genericWebElementList.get(globalIdxVal).getText());
        }

        genericWebElementList = getVisibleElements(taskPriorityBI);
        scrollToElement(genericWebElementList.get(globalIdxVal));
        if (Values.TXT_TASKPRIORITYNORMAL.trim().equalsIgnoreCase(genericWebElementList.get(globalIdxVal).getText().trim())) {
            logger.info(Values.TXT_BICORRECTVALUE + genericWebElementList.get(globalIdxVal).getText());
        } else {
            logger.warn(Values.TXT_BICORRECTVALUE + genericWebElementList.get(globalIdxVal).getText());
        }

        genericWebElementList = getVisibleElements(taskAccountBI);
        scrollToElement(genericWebElementList.get(globalIdxVal));
        if (Values.globalPayerName.trim().equalsIgnoreCase(genericWebElementList.get(globalIdxVal).getText().trim())) {
            logger.info(Values.TXT_BICORRECTVALUE + genericWebElementList.get(globalIdxVal).getText());
        } else {
            logger.warn(Values.TXT_BICORRECTVALUE + genericWebElementList.get(globalIdxVal).getText());
        }
    }
}