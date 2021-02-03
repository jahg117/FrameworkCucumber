package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewAccountPage extends CommonFunctions {

    @FindBy(xpath = "//div[@class='bPageTitle']")
    private WebElement label_newAccountTitle;

    @FindBy(xpath = "//select[contains(@name,'CreateAccount')]")
    private WebElement dropdown_recordType;

    @FindBy(xpath = "//table//input[contains(@id,'ACS_CreateAccount')]")
    private WebElement button_continue;
    
    @FindBy(xpath = "//iframe[@title='accessibility title']")
    private List<WebElement> iframe_customerLookup;

    public void selectRecordType(String dropdownOption) throws Exception{
        switchToFrameByWebElementIndexOrName(iframe_customerLookup.get(iframe_customerLookup.size()-1), 20);
        waitForElementVisibility(label_newAccountTitle, 20);
        selectAndMoveDropdownByText(dropdown_recordType, dropdownOption, 20);
        clickAndMoveToElementVisible(button_continue, 15);
        switchToParentFrame();
    }

    public void selectRecordTypes(String dropdownOption) throws Exception{

    }
}
