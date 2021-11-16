package pageObject.pages.accessServices.cases;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Values;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CasePage extends CommonFunctions {
    private Logger logger = Logger.getLogger(CommonFunctions.class);

    @FindBy(xpath = "(//img[@title='Case']/following::*[@name='Edit'])[last()]")
    private WebElement button_edit;

    @FindBy(xpath = "//img[@title='Case']/following::*//button[contains(@name,'Child')]")
    private WebElement button_childCase;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Case Requested By']/../..//span[contains(@class,'field-value')]")
    private WebElement input_caseRequestedBy;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Enrolled Patient']/../..//span[contains(@class,'field-value')]")
    private WebElement input_enrolledPatient;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Product Enrollment']/../..//span[contains(@class,'field-value')]")
    private WebElement input_productEnrollment;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Date of Birth']/../..//span[contains(@class,'field-value')]")
    private WebElement input_dateOfBirth;

    @FindBy(xpath = "(//img[@title='Case']/following::*[./text()='Channel']/../..//span[contains(@class,'field-value')])[last()]")
    private WebElement input_channel;

    @FindBy(xpath = "(//img[@title='Case']/following::*[./text()='Case Status']/../..//span[contains(@class,'field-value')])[last()]")
    private WebElement input_caseStatus;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Status']/../..//span[contains(@class,'field-value')]")
    private WebElement input_status;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Product']/../..//span[contains(@class,'field-value')]")
    private WebElement input_product;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Name']/../..//span[contains(@class,'field-value')]")
    private WebElement input_patientName;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Case Number']/../..//span[contains(@class,'field-value')]")
    private WebElement input_caseNumber;

    @FindBy(xpath = "//button[contains(@class,'search-button')]")
    private WebElement searchBarSF;

    @FindBy(xpath = "//input[starts-with(@placeholder,'Search.')]")
    private WebElement secondSearchBar;

    @FindBy(xpath = "//img[@title='Case'][1]")
    private WebElement imgCase;

    @FindBy(xpath = "//li[@title='Case Team']")
    private WebElement tabCaseTeamRV;

    @FindBy(xpath = "//*[@title='New Case Team']")
    private WebElement buttonNewCaseTeam;

    @FindBy(xpath = "//*[@data-label='Member Role']/..//span[normalize-space(text())='Prescribing Facility']")
    private WebElement columnMemberRoleRV;

    @FindBy(xpath = "//button[normalize-space(text())='Reverification']")
    private WebElement buttonReverification;

    @FindBy(xpath = "//td[@data-label='PEP ID']")
    private List<WebElement> columnPEPIDS;

    @FindBy(xpath = "//div[@data-aura-class='cACS_Case_PatientsCmp']/..//tr[not(@class='slds-line-height_reset')]")
    private List<WebElement> patientRows;

    @FindBy(xpath = "//td[@data-label='Next treatment date']")
    private List<WebElement> buttonRowNextTreatmentDate;

    @FindBy(xpath = "//td[@data-label='Next treatment date']/..//*[@data-key='edit']")
    private List<WebElement> buttonEditNextTreatmentDate;

    @FindBy(xpath = "//*[@name='dt-inline-edit-datetime']")
    private List<WebElement> buttonDateAndHour;

    @FindBy(xpath = "//button[normalize-space(text())='Save']")
    private WebElement buttonSaveRV;

    @FindBy(xpath = "//b[normalize-space(text())='Case Patients']/following::table[1]")
    private WebElement casePatientTable;

    @FindBy(xpath = "//button[normalize-space(text())='Create RV Cases']")
    private WebElement buttonCreateRVCases;

    @FindBy(xpath = "//*[normalize-space(text())='No patients selected']")
    private WebElement messageCreateRVCases;

    @FindBy(xpath = "//*[@title='Case Patient: Record ID ']")
    private WebElement columnCasePatientRecordID;

    @FindBy(xpath = "//b[normalize-space(text())='Case Patients']/following::table[1]//tr//td[2]")
    private List<WebElement> checkboxCasePatientsList;

    @FindBy(xpath = "//*[normalize-space(text())='RV Cases were created.']")
    private WebElement messageSuccessfullyCreateRVCases;

    @FindBy(xpath = "//*[normalize-space(text())='An error ocurred while creating RV Cases']")
    private WebElement messageErrorCreateRVCases;

    @FindBy(xpath = "//*[normalize-space(text())='RV Cases were created.']|//*[normalize-space(text())='An error ocurred while creating RV Cases']")
    private WebElement messagesStatusCreateRVCases;

    @FindBy(xpath = "//img[@title='Account'][1]")
    private WebElement imgAccount;

    @FindBy(xpath = "//h2//a[normalize-space(text())='Case']/following::*[starts-with(@class,'test-id__field-value')][3]")
    private WebElement caseChannelRV;

    @FindBy(xpath = "//img[@title='Account']/following::a[1]")
    private List<WebElement> buttonAccountPEPID;

    @FindBy(xpath = "//span[starts-with(text(),'Show more results for')]|//input[starts-with(@placeholder,'Search ')][not (@placeholder='Search this list...')]")
    private WebElement buttonShowMoreResultsFor;


    public boolean isCasePageDisplayed() throws Exception {
        return waitForElementVisibility(button_edit, mediumWait());
    }

    public void clickChildCaseButton() throws Exception {
        clickAndMoveToElementClickable(button_childCase, mediumWait());
    }

    public String getCaseNumber() throws Exception {
        return waitForElementVisibility(input_caseNumber, shortWait()) ? getWebElementText(input_caseNumber).split("\n")[0] : "";
    }

    public String getCaseStatus() throws Exception {
        if (waitForElementVisibility(input_caseStatus, shortWait())) {
            return getWebElementText(input_caseStatus).split("\n")[0];
        } else {
            if (waitForElementVisibility(input_status, shortWait())) {
                return getWebElementText(input_status).split("\n")[0];
            } else {
                return "";
            }
        }
    }

    public String getStatus() throws Exception {
        return waitForElementVisibility(input_status, shortWait()) ? getWebElementText(input_status).split("\n")[0] : "";
    }

    public String getCaseRequestedBy() throws Exception {
        return waitForElementVisibility(input_caseRequestedBy, shortWait()) ? getWebElementText(input_caseRequestedBy) : "";
    }

    public String getEnrolledPatient() throws Exception {
        return waitForElementVisibility(input_enrolledPatient, mediumWait()) ? getWebElementText(input_enrolledPatient).split("\n")[0] : "";
    }

    public String getProductEnrollment() throws Exception {
        return waitForElementVisibility(input_productEnrollment, shortWait()) ? getWebElementText(input_productEnrollment) : "";
    }

    public String getDateOfBirth() throws Exception {
        return waitForElementVisibility(input_dateOfBirth, shortWait()) ? getWebElementText(input_dateOfBirth) : "";
    }

    public String getChannel() throws Exception {
        return waitForElementVisibility(input_channel, shortWait()) ? getWebElementText(input_channel).split("\n")[0] : "";
    }

    public String getProduct() throws Exception {
        return waitForElementVisibility(input_product, shortWait()) ? getWebElementText(input_product) : "";
    }

    public String getPatientName() throws Exception {
        return waitForElementVisibility(input_patientName, mediumWait()) ? getWebElementText(input_patientName).split("\n")[0] : "";
    }

    public String getRVCaseNumber() throws Exception {//TS02JR
        //return waitForElementVisibility(input_patientName, mediumWait()) ? getWebElementText(input_patientName).split("\n")[0] : "";
        String caseNumberRV = "";
        return caseNumberRV;
    }

    public void searchCaseOnSF(String rvCaseNumber) throws Exception {//TBD from where the rv case number will be retreive
        //rvCaseNumber = "00024986";//TS02JR
        rvCaseNumber = "00531274";

        try {
            if (waitForElementClickable(searchBarSF, shortWait())) {
                scrollMethodToWebElement(searchBarSF);
                if (!searchBarSF.isDisplayed()) {
                    scrollMethodToWebElement(searchBarSF);
                }
                if (isClickableElementEnabled(searchBarSF, shortWait())) {
                    searchBarSF.click();
                } else {
                    scrollMethodToWebElement(searchBarSF);
                    searchBarSF.click();
                }
            }
        } catch (Exception e) {
            clickMethodsWebElement(searchBarSF);
        }
        sendKeysElementClickable(searchBarSF, rvCaseNumber, shortWait());
        try {
            if (waitForElementVisibility(imgCase, shortWait())) {
                if (!imgCase.isDisplayed()) {
                    scrollMethodToWebElement(searchBarSF);
                }
                if (isClickableElementEnabled(imgCase, shortWait())) {
                    imgCase.click();
                } else {
                    scrollMethodToWebElement(searchBarSF);
                    imgCase.click();
                }
            }
        } catch (Exception e) {
            clickMethodsWebElement(imgCase);
        }
        getRVCaseValues();
    }

    public void getRVCaseValues() throws Exception {
        if (waitForElementVisibility(caseChannelRV, shortWait())) {
            scrollToElement(caseChannelRV);
            Values.globalStringValues = caseChannelRV.getText();
        }
    }

    public void clickOnAddCaseTeam() throws Exception {
        try {
            if (waitForElementVisibility(tabCaseTeamRV, shortWait())) {
                if (!tabCaseTeamRV.isDisplayed()) {
                    scrollMethodToWebElement(tabCaseTeamRV);
                }
                if (isClickableElementEnabled(tabCaseTeamRV, shortWait())) {
                    tabCaseTeamRV.click();
                } else {
                    scrollMethodToWebElement(searchBarSF);
                    tabCaseTeamRV.click();
                }
            }
        } catch (Exception e) {
            clickMethodsWebElement(tabCaseTeamRV);
        }
        try {
            if (waitForElementVisibility(buttonNewCaseTeam, shortWait())) {
                if (!buttonNewCaseTeam.isDisplayed()) {
                    scrollMethodToWebElement(buttonNewCaseTeam);
                }
                if (isClickableElementEnabled(buttonNewCaseTeam, shortWait())) {
                    buttonNewCaseTeam.click();
                } else {
                    scrollMethodToWebElement(searchBarSF);
                    buttonNewCaseTeam.click();
                }
            }
        } catch (Exception e) {
            clickMethodsWebElement(buttonNewCaseTeam);
        }
    }

    public boolean validateAddedCaseTeamRV(String memberRoleCaseTeamRV) throws Exception {
        By columnMemberRoleRV = By.xpath("//*[@data-label='Member Role']/..//span[normalize-space(text())='" + memberRoleCaseTeamRV + "']");
        boolean statusOperation = false;
        if (waitForElementToBeClickableBy(columnMemberRoleRV, shortWait())) {
            if (getWebElement(columnMemberRoleRV).getText().trim().equalsIgnoreCase(memberRoleCaseTeamRV.trim())) {
                statusOperation = true;
            }
        }
        return statusOperation;
    }

    public void clickOnReverification() throws Exception {
        try {
            if (waitForElementVisibility(buttonReverification, shortWait())) {
                if (!buttonReverification.isDisplayed()) {
                    scrollMethodToWebElement(buttonReverification);
                }
                if (isClickableElementEnabled(buttonReverification, shortWait())) {
                    buttonReverification.click();
                } else {
                    scrollMethodToWebElement(searchBarSF);
                    buttonReverification.click();
                }
            }
        } catch (Exception e) {
            clickMethodsWebElement(buttonReverification);
        }
    }

    public boolean validatePEPID(List<String> patientList) throws Exception {
        switchToDefaultContentFrame();
        int counterPepID = 0;
        boolean statusOperation = false;
        String atrValue = "";
        do {
            atrValue = casePatientTable.getAttribute(Values.ATR_ARIAROWCOUNT);
        } while (atrValue.equalsIgnoreCase("1"));
        if (!casePatientTable.isDisplayed() || casePatientTable.getAttribute(Values.ATR_ARIAROWCOUNT).equalsIgnoreCase("1")) {
            waitForElementClickable(columnPEPIDS.get(0), shortWait());
        }
        for (String pepID : patientList) {
            for (int i = 0; i < columnPEPIDS.size(); i++) {
                if (!columnPEPIDS.get(i).isDisplayed()) {
                    waitForElementClickable(columnPEPIDS.get(i), shortWait());
                } else if (pepID.trim().equalsIgnoreCase(columnPEPIDS.get(i).getText().trim())) {
                    counterPepID++;
                    logger.info(Values.TXT_RVPEPIDMATCHED + pepID);
                    break;
                }
            }
        }
        if (counterPepID == patientList.size()) {
            statusOperation = true;
        }
        return statusOperation;
    }


    public List<String> editNextTreatmentDate(String nextTreatmentDate, List<String> patientList) throws Exception {
        boolean ntdNotRequired = false;
        List<String> patientsValidationList = new ArrayList<>();
        List<String> noApplySecondPatient = List.of(nextTreatmentDate.split(Values.TXT_UNDERSCORE));
        List<String> dateAndHour = new ArrayList<>();
        if (noApplySecondPatient.size() >= 2) {
            ntdNotRequired = true;
            dateAndHour = List.of(noApplySecondPatient.get(0).split(Values.REGEX_COMMA));
        } else {
            dateAndHour = List.of(nextTreatmentDate.split(Values.REGEX_COMMA));
        }
        int counterSelection = 0;
        clickAndMoveMethodsWebElement(columnCasePatientRecordID);
        for (WebElement patientRow : patientRows) {
            if (counterSelection == 1 && ntdNotRequired) {
                break;
            }
            for (int i = 0; i < (patientList.size() - 1); i++) {
                if (patientRow.getText().trim().contains(patientList.get(i))) {
                    patientsValidationList.add(patientList.get(i) + "|E");
                    clickAndMoveMethodsWebElement(buttonRowNextTreatmentDate.get(counterSelection));
                    if (waitForElementClickable(buttonEditNextTreatmentDate.get(counterSelection), shortWait())) {
                        clickAndMoveMethodsWebElement(buttonEditNextTreatmentDate.get(counterSelection));
                    }
                    if (waitForElementClickable(buttonDateAndHour.get(0), shortWait())) {
                        sendKeysAndMoveToElement(buttonDateAndHour.get(0), dateAndHour.get(0));
                    }
                    if (waitForElementClickable(buttonDateAndHour.get(1), shortWait())) {
                        buttonDateAndHour.get(1).click();
                        buttonDateAndHour.get(1).clear();
                        sendKeysAndMoveToElement(buttonDateAndHour.get(1), dateAndHour.get(1));
                        buttonDateAndHour.get(1).sendKeys(Keys.TAB);
                        clickAndMoveMethodsWebElement(buttonSaveRV);
                    }
                    break;
                } else {
                    patientsValidationList.add(patientList.get(i) + "|NE");
                }
            }
            counterSelection++;
        }
        return patientsValidationList;
    }

    public boolean validateCreateRVCasesMessage() throws Exception {
        boolean statusOperation = false;
        if (waitForElementClickable(buttonCreateRVCases, shortWait())) {
            clickElementJS(buttonCreateRVCases);
            if (waitForElementVisibility(messageCreateRVCases, shortWait())) {
                statusOperation = true;
            }
        }
        return statusOperation;
    }

    public boolean selectAndCreateRVCases() throws Exception {
        boolean statusOperation = false;
        if (waitForElementClickable(buttonCreateRVCases, shortWait())) {
            for (WebElement checkbox : checkboxCasePatientsList) {
                clickAndMoveMethodsWebElement(checkbox);
            }
            clickAndMoveMethodsWebElement(buttonCreateRVCases);
            statusOperation = ValidateCreationOfRVCases();
        }
        return statusOperation;
    }

    public boolean ValidateCreationOfRVCases() throws Exception {
        boolean statusOperation = false;
        String statusMessageValue = "";
        if (waitForElementVisibility(messagesStatusCreateRVCases, shortWait())) {
            statusMessageValue = messagesStatusCreateRVCases.getText().trim();
        }
        switch (statusMessageValue.trim()) {
            case Values.TXT_SUCCESSFULLCREATIONRVCASES:
                logger.info(Values.TXT_SUCCESSFULLCREATIONRVCASES);
                statusOperation = true;
                break;
            case Values.TXT_ERRORCREATINGRVCASES:
                logger.info(Values.TXT_ERRORCREATINGRVCASES);
                break;
            default:
                logger.info(Values.TXT_NOMESSAGEDISPLAYED);
                break;
        }
        return statusOperation;
    }

    public void searchPatientIDSF(String patientID) throws Exception {
        try {
            if (waitForElementClickable(searchBarSF, shortWait())) {
                clickMethod(searchBarSF);
                sendKeysAndMoveToElementClickable(secondSearchBar, patientID, shortWait());
                if (waitForElementClickable(buttonShowMoreResultsFor, shortWait())) {
                    buttonShowMoreResultsFor.click();
                    if (buttonAccountPEPID.size() >= 1) {
                        clickAndMoveToElementClickable(getVisibleElements(getVisibleElements(buttonAccountPEPID)).get(0), shortWait());
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}