package pageObject.pages.accessServices.selenium4;

import base.functions.CommonFunctions;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class NewS4FunctionalitiesPage extends CommonFunctions {


    @FindBy(xpath = "//*[@class='slds-global-header__logo'][1]")
    private WebElement logoCloud;


    public void takeSS() throws Exception {
        File file = logoCloud.getScreenshotAs(OutputType.FILE);
        File destFile = new File("CLOUD_LOGO.png");
        FileUtils.copyFile(file, destFile);
    }

}
