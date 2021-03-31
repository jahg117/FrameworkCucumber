package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewPatientInsurance extends CommonFunctions {
    @FindBy(xpath = "//*[contains(text(),'No Patient Insurance')]")
    private WebElement radioButton_NoPI;

    @FindBy(xpath = "//*[contains(text(),'Patient Medical Insurance')]")
    private WebElement radioButton_PMI;

    @FindBy(xpath = "//*[contains(text(),'Patient PBM Insurance')]")
    private WebElement radioButton_PBM;


}