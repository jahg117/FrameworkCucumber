package pageObject.pages.accessServices.productEnrollment;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;

import java.util.List;
import java.util.Map;

public class CreateNewEnrollmentPage extends CommonFunctions {

    @FindBy(xpath = "//iframe[@title='New Program Enrollment']")
    private WebElement iframe_newProgramEnrollment;

    @FindBy(xpath = "//span[@class='lookupInput']")
    private WebElement input_product;

    @FindBy(xpath = "//img[@class='lookupIcon']")
    private WebElement icon_lookUp;

    @FindBy(xpath = "//span[@class='dateInput dateOnlyInput']")
    private WebElement input_programEndDate;

    @FindBy(xpath = "//td[@class='pbButtonb ']//input[@value='Enroll']")
    private WebElement button_enroll;

    @FindBy(xpath = "//*[@title='New Care Team Member']")
    private WebElement button_newCareTeamMember;

    public boolean isProductEnrollmentPageDisplayed() {
        return waitForElementVisibility(iframe_newProgramEnrollment, 30);
    }

    public String fillProductEnrollmentForm(String productType) throws Exception {
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
        switchToFrameByWebElementIndexOrName(iframe_newProgramEnrollment, 20);
        sendKeysAndMoveToElementVisible(input_product, product,20);
        clickElementVisible(input_programEndDate, 10);
        switchToParentFrame();
        return product;
    }

    public void clickEnrollButton() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_newProgramEnrollment, 20);
        waitForElementClickable(input_product, 10);
        waitForElementClickable(button_enroll, 10);
        scrollToWebElementJS(button_enroll);
        waitForElementClickable(button_enroll, 10);
        scrollToWebElementJS(button_enroll);
        doubleClickAndMoveToElementClickable(button_enroll, 10);
        if(!waitForElementVisibility(button_newCareTeamMember, 10)){
            try {
                waitForElementClickable(button_enroll, 10);
                scrollToWebElementJS(button_enroll);
                doubleClickAndMoveToElementClickable(button_enroll, 10);
            }catch (Exception e){}
        }
        switchToParentFrame();
    }

    /**
     *Use to select a random consent type or an specific accountType from table
     *
     * @param consentTypeList it contains all the consents type in the config table
     * @return it returns the consent type to be used
     * @throws Exception
     * @author J.Ruano
     */
    public String selectConsentTypeFromList(List<Map<String, String>> consentTypeList) throws Exception {
        int counter = 0;
        String consentTypeSelected = "";
        boolean rndSelected = false;
        for (Map<String, String> consentType : consentTypeList) {
            if (consentTypeList.get(counter).get("consentType").equalsIgnoreCase("RND")) {
                if (consentTypeList.get(counter).get("useThisAccount").equalsIgnoreCase("Y")) {
                    consentTypeSelected = consentTypeList.get(getRandomNumberByLimits(1, consentTypeList.size())).get("consentType");
                    rndSelected = true;
                    break;
                }
            }
            counter++;
        }
        counter = 0;
        if (!rndSelected) {
            for (Map<String, String> consentType : consentTypeList) {
                if (consentTypeList.get(counter).get("useThisAccount").equalsIgnoreCase("Y")) {
                    consentTypeSelected = consentTypeList.get(counter).get("consentType");
                    break;
                }
                counter++;
            }
        }
        return consentTypeSelected;
    }
}
