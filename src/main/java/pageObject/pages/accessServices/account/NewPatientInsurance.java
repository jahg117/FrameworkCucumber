package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Values;

public class NewPatientInsurance extends CommonFunctions {
    private final Logger logger = Logger.getLogger(CommonFunctions.class);
    @FindBy(xpath = "//*[contains(text(),'No Patient Insurance')]")
    private WebElement radioButtonNoPI;

    @FindBy(xpath = "//*[contains(text(),'Patient Medical Insurance')]")
    private WebElement radioButtonPMI;

    @FindBy(xpath = "//*[contains(text(),'Patient PBM Insurance')]")
    private WebElement radioButtonPBM;

    @FindBy(xpath = "//span[contains(text(),'Next')]")
    private WebElement buttonNext;

    @FindBy(xpath = "//*[@slot='primaryField']/..//*[starts-with(text(),'PI')]")
    private WebElement labelPatientInsuranceNumber;

    @FindBy(xpath = "//h2[normalize-space(text())='New Patient Insurance']")
    private WebElement labelNewPatientInsurance;

    @FindBy(xpath = "//img[@class=' checked']")
    private WebElement activePayerCheckBox;

    @FindBy(xpath = "//a[normalize-space(text())='Patient Insurance Details']/following::*[starts-with(@class,'test-id__field-value')][2]")
    private WebElement insurancePayerName;


    /**
     * It will select the insurance type i.e. NOPI (No Patient Insurance), PMI and PBM
     *
     * @throws Exception related to selenium
     * @author J.Ruano
     */
    public void selectInsuranceType(String insuranceType) throws Exception {
        waitForElementClickable(labelNewPatientInsurance, mediumWait());
        switch (insuranceType.trim().toLowerCase()) {
            case Values.TXT_NOPI:
                clickAndMoveToElementClickable(radioButtonNoPI, shortWait());
                break;
            case Values.TXT_PMI:
                clickAndMoveToElementClickable(radioButtonPMI, shortWait());
                break;
            case Values.TXT_PBM:
                clickAndMoveToElementClickable(radioButtonPBM, shortWait());
                break;
            default:
                logger.warn(Values.TXT_SWITCHDEFAULTMESSAGE);
                break;
        }
        clickAndMoveToElementClickable(buttonNext, shortWait());
    }

    /**
     * It will verify if the Insurance was created
     *
     * @return it returns the Insurance Number
     * @throws Exception related to selenium
     * @author J.Ruano
     */
    public String getPatientInsuranceNumber() throws Exception {
        waitForElementVisibility(labelPatientInsuranceNumber, mediumWait());
        return getWebElementText(labelPatientInsuranceNumber);
    }

    public String getActivePIName() throws Exception {
        String insurancePayerNameValue = "";
        try {
            if (waitForElementClickable(getWebElement(By.xpath("//a[normalize-space(text())='Patient Insurance Details']/following::" +
                    "*[starts-with(@class,'test-id__field-value')][2]")), shortWait())) {
                if (waitForElementClickable(getWebElement(By.xpath("//img[@class=' checked']")), shortWait())) {
                    insurancePayerNameValue = getWebElement(By.xpath("//a[normalize-space(text())='Patient Insurance Details']" +
                            "/following::*[starts-with(@class,'test-id__field-value')][2]")).getText();
                }
            }
            closeLastSubTabSF(shortWait());
        } catch (Exception e) {
            return insurancePayerNameValue;
        }
        return insurancePayerNameValue;
    }
}