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

    @FindBy(xpath = "//*[contains(text(),'Card Number')]/../..//input")
    private WebElement input_cardNumber;

    private By list_productOptions = By.xpath("//ul[@class='lookup__list  visible']//li[contains(@class,'default uiAutocompleteOption')]");

    private JsonFiles file = new JsonFiles();

    private String caseOption;
    public void fillRandomMandatoryFields(String caseOption, String productType) throws Exception {
        this.caseOption = caseOption;
        if(caseOption.equalsIgnoreCase("Interaction")){
            fillInteractionForm();
        }else{
            if(caseOption.contains("Nurse")){
                productType = "Fasenra Pen";
            }
            fillInteractionForm();
            fillAssetRequestForm(caseOption, productType);
        }
    }

    public void fillInteractionForm() throws Exception {
        selectRandomDropdownOption(dropdown_channel, list_channelOptions);
    }

    public void fillAssetRequestForm(String caseOption, String productType) throws Exception {
        file.setFileName("caseFields");
        selectRandomDropdownOption(dropdown_subType, list_subTypeOptions);
        if(productType == ""){
            productType = file.getRandomFieldArray("Products");
        }
        sendKeysAndMoveToElementVisible(input_searchProducts, productType, 20);
        waitForElementVisibility(list_autocomplete, 20);
        if(waitForNumberOfElementsToBeMoreThanBy(list_productOptions, 0, 20)){
            waitForElementListVisible(getWebElementList(list_productOptions), 10);
            for(WebElement el : getWebElementList(list_productOptions)){
                if(getWebElementText(el).split("\n")[0].equalsIgnoreCase(productType)){
                    clickAndMoveToElementClickable(el, 10);
                    break;
                }
            }
        }
        if(waitForElementListVisible(list_discussTopic, 1)){
            clickAndMoveToElementClickable(getRandomWebElementFromList(list_discussTopic, 10), 10);
            clickAndMoveToElementClickable(button_iconRightFlagDiscussionTopic, 10);
        }
        if(waitForElementVisibility(input_cardNumber, 1)){
            sendKeysAndMoveToElementVisible(input_cardNumber, getRandomNumber(), 3);
        }
    }

    public void fillPatientDetails(String caseOption, String productEnrollment) throws Exception {
        if(!caseOption.equalsIgnoreCase("Interaction")){
            sendKeysAndMoveToElementVisible(input_searchProductEnrollments, productEnrollment, 20);
            waitForElementVisibility(list_autocomplete, 20);
            if(waitForNumberOfElementsToBeMoreThanBy(list_productOptions, 0, 20)){
                waitForElementListVisible(getWebElementList(list_productOptions), 10);
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
