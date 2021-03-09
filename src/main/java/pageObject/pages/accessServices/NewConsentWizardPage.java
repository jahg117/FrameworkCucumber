package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;

import java.util.List;

public class NewConsentWizardPage extends CommonFunctions {

    @FindBy(xpath = "(//iframe[@title='accessibility title'])[last()]")
    private WebElement iframe_pageInformation;

    @FindBy(xpath = "//*[@class='actionBtns']/input[@value='Next']")
    private WebElement button_next;

    @FindBy(xpath = "//*[@class='requiredDiv']/following::select[@id][1]")
    private WebElement dropdown_consentStatus;

    @FindBy(xpath = "//*[@class='requiredDiv']/following::input[@id][1]")
    private WebElement datePicker_consentDateManual;

    @FindBy(xpath = "//*[@class='dateFormat']/a")
    private WebElement datePicker_consentDateCurrent;

    @FindBy(xpath = "//select[contains(@id,'consentSource')]")
    private WebElement dropdown_consentSource;

    @FindBy(xpath = "//*[@class='consentProvidedBy'][contains(@id,'patient')]")
    private WebElement checkbox_consentSelf;

    @FindBy(xpath = "//*[@class='consentProvidedBy'][contains(@id,'caregiver')]")
    private WebElement checkbox_consentLAF;

    @FindBy(xpath = "//*[@class='consentProvidedBy']")
    private List<WebElement> checkbox_consentByList;

    @FindBy(xpath = "//input[@type='checkbox']")
    private List<WebElement> checkbox_addressesCheckBoxes;

    @FindBy(xpath = "//input[@value='Save']")
    private WebElement button_addressSave;

    /**
     * Method used fill the required data to successfully create the type of consent selected
     *
     * @param consentStatus it contains the status of the type o consnet i.e. "Active"
     * @param consentDate   this can be future date, it can be an specific date with the format MM/DD/YEAR i.e. 1/25/2077 or can be current date only if comes empty
     * @param consentSource it can be an specific option i.e. "Fax" or can be random selection only if comes empty
     * @param consentAuth   it can be an specific option i.e. "LAR" or can be random selection only if comes empty
     * @throws Exception
     * @author J.Ruano
     */
    public void fillConsentForm(String consentStatus, String consentDate, String consentSource, String consentAuth) throws Exception {
        int requiredValues = 4;
        int valueCounter = 1;
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 20);
        do {
            switch (valueCounter) {
                case 1:
                    if (!consentStatus.trim().isEmpty() && !consentStatus.trim().equalsIgnoreCase("RND")) {
                        selectAndMoveDropdownByText(dropdown_consentStatus, consentStatus, 20);
                    } else {
                        selectDropDownRandomOptionNone(dropdown_consentStatus, 10);
                    }
                    valueCounter++;
                    break;

                case 2:
                    if (!consentDate.trim().isEmpty() && !consentDate.trim().equalsIgnoreCase("RND")) {
                        sendKeysAndMoveToElementClickable(datePicker_consentDateManual, consentDate, 10);
                        datePicker_consentDateManual.sendKeys(Keys.ESCAPE);
                    } else {
                        clickAndMoveToElementClickable(datePicker_consentDateCurrent, 10);
                    }
                    valueCounter++;
                    break;

                case 3:
                    if (!consentSource.trim().isEmpty()&& !consentSource.trim().equalsIgnoreCase("RND")) {
                        selectAndMoveDropdownByText(dropdown_consentSource, consentSource, 20);
                    } else {
                        selectDropDownRandomOptionNone(dropdown_consentSource, 10);
                    }
                    valueCounter++;
                    break;

                case 4:
                    if (!consentAuth.trim().isEmpty() && consentAuth.trim().equalsIgnoreCase("Self")) {
                        clickAndMoveToElementClickable(checkbox_consentSelf, 10);
                    } else if (consentAuth.trim().equalsIgnoreCase("LAR")) {
                        clickAndMoveToElementClickable(checkbox_consentLAF, 10);
                    } else {
                        clickAndMoveToElementClickable(getRandomWebElementFromList(checkbox_consentByList, 10), 10);
                    }
                    valueCounter++;
                    break;
            }
        }
        while (valueCounter <= requiredValues);
        clickAndMoveToElementVisible(button_next, 15);
    }

    /**
     * Method used to select an already existing Address randomly or an specif address by index
     *
     * @param randomAddress if this is true it will be selecting a random address previously created this has first priority even if you put an idxCheckbox to be selected
     * @param idxCheckbox   it contains an integer number to select from the list
     * @throws Exception
     */
    public void selectConsentAddress(boolean randomAddress, int idxCheckbox) throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 20);
        List<WebElement> visibleWebElements = getVisibleElements(checkbox_addressesCheckBoxes);
        if (randomAddress == true) {
            clickAndMoveToElementClickable(getRandomWebElementFromList(visibleWebElements, 10), 10);
        } else {
            clickAndMoveToElementClickable(visibleWebElements.get(idxCheckbox), 10);
        }
        clickAndMoveToElementClickable(button_addressSave, 10);
    }
}