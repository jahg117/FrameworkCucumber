package pageObject.pages.accessServices.productEnrollment;

import base.functions.CommonFunctions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;

import java.nio.file.NoSuchFileException;
import java.util.List;

public class NewProductEnrollmentForm extends CommonFunctions {

    @FindBy(xpath = "//div[contains(@class,'modal-container')]")
    private WebElement form_productEnrollmentContainer;

    @FindBy(xpath = "//div[contains(@class,'modal-container')]//input[@title='Search Accounts']")
    private WebElement input_patientName;

    @FindBy(xpath = "//div[contains(@class,'modal-container')]//input[@title='Search Products']")
    private WebElement input_productName;

    @FindBy(xpath = "//div[contains(@class,'modal-container')]//span[contains(text(),'Therapeutic')]/../..//input")
    private WebElement input_therapeuticArea;

    @FindBy(xpath = "//div[contains(@class,'lookup__menu uiAbstractList')]//li[contains(@class,'default uiAutocompleteOption')]//div[contains(@class,'primaryLabel')]")
    private List<WebElement> list_autocompleteElements;

    @FindBy(xpath = "//div[contains(@class,'modal-container')]//button[@title='Save']")
    private WebElement button_save;

    public boolean isProductEnrollmentFormDisplayed(){
        return waitForElementVisibility(form_productEnrollmentContainer, 10);
    }

    public void fillProductEnrollmentForm(String patientName, String product) throws Exception {
        selectElementFromAutocompleteList(input_patientName, patientName);
        selectElementFromAutocompleteList(input_productName, product);
        if(waitForElementVisibility(input_therapeuticArea, 5)){
            sendKeysAndMoveToElementClickable(input_therapeuticArea, "test", 10);
        }
        clickAndMoveToElementClickable(button_save, 10);
    }

    private void selectElementFromAutocompleteList(WebElement element, String textElement) throws Exception {
        sendKeysAndMoveToElementClickable(element, textElement, 10);
        if (!waitForElementListVisible(list_autocompleteElements, 5)) {
            sendKeysByActions(Keys.TAB.toString());
            clickAndMoveToElementVisible(element, 5);
            waitForElementListVisible(list_autocompleteElements, 5);
        }
        for (WebElement el : list_autocompleteElements) {
            if (getWebElementText(el).equalsIgnoreCase(textElement)) {
                clickAndMoveToElementClickable(el, 10);
                break;
            }
        }
    }

    public String getProduct(String productType) throws NoSuchFileException {
        String product = "";
        if(productType.equalsIgnoreCase("")){
            productType = "AZ";
        }
        if(productType.equalsIgnoreCase("AZ")
                ||productType.equalsIgnoreCase("DSI")){
            JsonFiles file = new JsonFiles();
            file.setFileName("1372_EnrollmentProducts");
            product = file.getRandomFieldArray(productType);
        } else{
            product = productType;
        }
        return product;
    }
}
