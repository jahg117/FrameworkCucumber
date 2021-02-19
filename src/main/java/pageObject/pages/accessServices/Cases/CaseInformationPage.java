package pageObject.pages.accessServices.Cases;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;

import java.util.List;

public class CaseInformationPage extends CommonFunctions {
    @FindBy(xpath = "//button[@title='Save']")
    private WebElement button_save;

    @FindBy(xpath = "(//div[contains(@class,'forceDetailPanelDesktop')])[last()]//*[contains(text(),'New Case')]")
    private WebElement form_caseOptions;

    @FindBy(xpath = "//span[contains(text(),'Case Information')]/following::*[@class='select'][1]")
    private WebElement dropdown_channel;

    @FindBy(xpath = "//*[contains(text(),'Channel')]/following::*[contains(@class,'uiMenuList--short visible')]//a")
    private List<WebElement> list_channelOptions;

    @FindBy(xpath = "//span[contains(text(),'Case Sub-Type')]/following::*[@class='select'][1]")
    private WebElement dropdown_subType;

    @FindBy(xpath = "//*[contains(text(),'Case Sub-Type')]/following::*[contains(@class,'uiMenuList--short visible')]//a")
    private List<WebElement> list_subTypeOptions;

    @FindBy(xpath = "//input[@title='Search Products']")
    private WebElement input_searchProducts;

    @FindBy(xpath = "//div[contains(text(),'Discussion Topic')]/..//span[@class='slds-truncate']")
    private List<WebElement> list_discussTopic;

    @FindBy(xpath = "//div[contains(text(),'Discussion Topic')]/..//*[@data-key='right']")
    private WebElement button_iconRightFlagDiscussionTopic;

    @FindBy(xpath = "//ul[@class='lookup__list  visible']")
    private WebElement list_autocomplete;

    @FindBy(xpath = "//input[@title='Search Product Enrollments']")
    private WebElement input_searchProductEnrollments;

    private By list_productOptions = By.xpath("//ul[@class='lookup__list  visible']//li[contains(@class,'default uiAutocompleteOption')]");

    private JsonFiles file = new JsonFiles();

    public void fillRandomMandatoryFields(String caseOption) throws Exception {
        if(caseOption.equalsIgnoreCase("Interaction")){
            fillInteractionForm();
        }
        if(caseOption.equalsIgnoreCase("Asset Request")||
                caseOption.equalsIgnoreCase("Benefit Investigation")||
                caseOption.equalsIgnoreCase("Claims Support")||
                caseOption.equalsIgnoreCase("Denied Patient Savings")||
                caseOption.equalsIgnoreCase("Free Limited Supply Program")||
                caseOption.equalsIgnoreCase("General Inquiry")||
                caseOption.equalsIgnoreCase("In-Home Nurse Support")){
            fillInteractionForm();
            fillAssetRequestForm();
        }
    }

    public void fillInteractionForm() throws Exception {
        selectRandomDropdownOption(dropdown_channel, list_channelOptions);
    }

    public void fillAssetRequestForm() throws Exception {
        file.setFileName("caseFields");
        selectRandomDropdownOption(dropdown_subType, list_subTypeOptions);
        sendKeysAndMoveToElementVisible(input_searchProducts, file.getRandomFieldArray("Products"), 20);
        waitForElementVisibility(list_autocomplete, 20);
        if(waitForNumberOfElementsToBeMoreThanBy(list_productOptions, 0, 20)){
            clickAndMoveToElementClickable(getWebElementList(list_productOptions).get(0), 10);
        }
        clickAndMoveToElementClickable(getRandomWebElementFromList(list_discussTopic, 10), 10);
        clickAndMoveToElementClickable(button_iconRightFlagDiscussionTopic, 10);
    }

    public void fillPatientDetails(String caseOption, String productEnrollment) throws Exception {
        if(caseOption.equalsIgnoreCase("Denied Patient Savings")||
                caseOption.equalsIgnoreCase("Free Limited Supply Program")||
                caseOption.equalsIgnoreCase("General Inquiry")||
                caseOption.equalsIgnoreCase("In-Home Nurse Support")){
            sendKeysAndMoveToElementVisible(input_searchProductEnrollments, productEnrollment, 20);
            waitForElementVisibility(list_autocomplete, 20);
            if(waitForNumberOfElementsToBeMoreThanBy(list_productOptions, 0, 20)){
                clickAndMoveToElementClickable(getWebElementList(list_productOptions).get(0), 10);
            }
        }
    }

    public void clickSaveButton() throws Exception {
        clickAndMoveToElementClickable(button_save, 10);
    }

    public boolean isCaseOptionPageDisplayed(){
        return waitForElementVisibility(form_caseOptions, 30);
    }

    private void selectRandomDropdownOption(WebElement element, List<WebElement> listElement) throws Exception {
        clickAndMoveToElementClickable(element, 10);
        waitForElementListVisible(listElement, 10);
        clickAndMoveToElementClickable(getRandomWebElementFromListExceptFirst(listElement, 10), 10);
    }
}
