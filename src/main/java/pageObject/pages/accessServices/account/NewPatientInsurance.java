package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewPatientInsurance extends CommonFunctions {
    @FindBy(xpath = "//*[contains(text(),'No Patient Insurance')]")
    private WebElement radioButton_NoPI;

    @FindBy(xpath = "//*[contains(text(),'Patient Medical Insurance')]")
    private WebElement radioButton_PMI;

    @FindBy(xpath = "//*[contains(text(),'Patient PBM Insurance')]")
    private WebElement radioButton_PBM;

    @FindBy(xpath = "//span[contains(text(),'Next')]")
    private WebElement button_Next;

    @FindBy(xpath = "//*[@slot='primaryField']/..//*[starts-with(text(),'PI')]")
    private WebElement label_patientInsuranceNumber;


    /**
     * It will select the insurance type i.e. NOPI (No Patient Insurance), PMI and PBM
     *
     * @throws Exception
     * @author J.Ruano
     */
    public void selectInsuranceType(String insuranceType) throws Exception {
        waitForElementClickable(radioButton_NoPI, mediumWait());
        switch (insuranceType.trim().toLowerCase()) {
            case "nopi":
                clickAndMoveToElementClickable(radioButton_NoPI, shortWait());
                break;
            case "pmi":
                clickAndMoveToElementClickable(radioButton_PMI, shortWait());
                break;
            case "pbm":
                clickAndMoveToElementClickable(radioButton_PBM, shortWait());
                break;
        }
        clickAndMoveToElementClickable(button_Next, shortWait());
    }

    /**
     * It will verify if the Insurance was created
     *
     * @return it returns the Insurance Number
     * @throws Exception
     * @author J.Ruano
     */
    public String getPatientInsuranceNumber() throws Exception {
        waitForElementVisibility(label_patientInsuranceNumber, shortWait());
        return getWebElementText(label_patientInsuranceNumber);
    }
}