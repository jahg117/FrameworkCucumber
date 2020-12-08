package base.functions;

import base.driverInitialize.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FileReading;

import java.time.Duration;
import java.util.List;

public class CommonFunctions {

    private WebDriver webDriver = DriverFactory.getDriver();

    public FileReading fileReading = new FileReading();

    /**
     * Return true if a WebElement is found or false if it's not found
     *
     * @author Alejandro Hernandez
     * @param timeOutInMinutes Time to wait in minutes.
     * @param pollingEvery Seconds to search a WebElement every specific second.
     * @return boolean
     */
    private boolean waitForElementFluentMinutes(int timeOutInMinutes, int pollingEvery){
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(webDriver);
            wait.withTimeout(Duration.ofMinutes(timeOutInMinutes));
            wait.pollingEvery(Duration.ofSeconds(pollingEvery));
            wait.ignoring(NoSuchElementException.class);
            wait.ignoring(TimeoutException.class);
            wait.ignoring(StaleElementReferenceException.class);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Return true if a WebElement is found or false if it's not found
     *
     * @author Alejandro Hernandez
     * @param timeOutInSeconds Time to wait in seconds.
     * @param pollingEvery Seconds to search a WebElement every specific second.
     * @return boolean
     */
    private boolean waitForElementFluentSeconds(int timeOutInSeconds, int pollingEvery){
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(webDriver);
            wait.withTimeout(Duration.ofSeconds(timeOutInSeconds));
            wait.pollingEvery(Duration.ofSeconds(pollingEvery));
            wait.ignoring(NoSuchElementException.class);
            wait.ignoring(TimeoutException.class);
            wait.ignoring(StaleElementReferenceException.class);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Return true if a WebElement is clickable or false if it's not clickable
     *
     * @author Alejandro Hernandez
     * @param element WebElement to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     */
    public boolean waitForElementClickable(WebElement element, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Return true if a WebElement is visible or false if it's not visible
     *
     * @author Alejandro Hernandez
     * @param element WebElement to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     */
    public boolean waitForElementVisibility(WebElement element, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Return true if a WebElement is not visible or false if it's visible
     *
     * @author Alejandro Hernandez
     * @param element WebElement to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     */
    public boolean waitForElementNotVisible(WebElement element, String attribute, String attributeValue,int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.invisibilityOf(element));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Return true if a list of WebElements is not visible or false if it's visible
     *
     * @author Alejandro Hernandez
     * @param elements List of WebElements to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     */
    public boolean waitForElementsNotVisible(List<WebElement> elements, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Return true if a list of WebElements is visible or false if it's not visible
     *
     * @author Alejandro Hernandez
     * @param elements List of WebElements to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     */
    public boolean waitForElementsVisible(List<WebElement> elements, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Return true if an Alert is displayed or false if it's not displayed
     *
     * @author Alejandro Hernandez
     * @param timeOutInSeconds Seconds to wait for an Alert.
     * @return boolean
     */
    public boolean waitForAlertVisible(int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Return true if a WebElement with a specific attribute is visible or false if it's not visible
     *
     * @author Alejandro Hernandez
     * @param element WebElement to find.
     * @param attribute WebElement Attribute
     * @param attributeValue Attribute Value
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     */
    public boolean waitForElementAttributeContains(WebElement element, String attribute, String attributeValue,int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.attributeContains(element, attribute, attributeValue));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Return true if a WebElement attribute is not empty or false if it's empty
     *
     * @author Alejandro Hernandez
     * @param element WebElement to find.
     * @param attribute WebElement Attribute
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     */
    public boolean waitForElementAttributeNotEmpty(WebElement element, String attribute,int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.attributeToBeNotEmpty(element,attribute));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * An expectation for checking given WebElement has attribute with a specific value
     *
     * @author Alejandro Hernandez
     * @param element WebElement to find.
     * @param attribute WebElement Attribute
     * @param attributeValue Attribute Value
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     */
    public boolean waitForElementAttributeToBe(WebElement element, String attribute, String attributeValue,int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.attributeToBe(element,attribute,attributeValue));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Return true if an expected page title is displayed or false if it's not displayed
     *
     * @author Alejandro Hernandez
     * @param title Page title value
     * @param timeOutInSeconds Seconds to wait for an expected title.
     * @return boolean
     */
    public boolean waitForElementPageTitle(String title,int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.titleIs(title));
            return true;
        }catch (Exception e){
            return false;
        }
    }
    /**
     * Return true if a specific URL is displayed or false if it's not displayed
     *
     * @author Alejandro Hernandez
     * @param url URL to be displayed in the page
     * @param timeOutInSeconds Seconds to wait for a URL.
     * @return boolean
     */
    public boolean waitForElementUrlToBe(String url, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.urlToBe(url));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Return true and switch to a frame if it's displayed or false if it's not displayed
     *
     * @author Alejandro Hernandez
     * @param element WebElement frame
     * @param timeOutInSeconds Seconds to wait for an expected frame.
     * @return boolean
     */
    public boolean waitForElementFrameAndSwitchToIt(WebElement element, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Return true if a WebElement is selected or false if it's not selected
     *
     * @author Alejandro Hernandez
     * @param element WebElement
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     */
    public boolean waitForElementSelected(WebElement element, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeSelected(element));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Return true if a WebElement has a specific text or false if it's not present
     *
     * @author Alejandro Hernandez
     * @param element WebElement
     * @param textElement Text to wait from a WebElement
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     */
    public boolean waitForElementTextPresent(WebElement element, String textElement, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.textToBePresentInElement(element, textElement));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Return true if a WebElement element's value has a specific text or false if it's not present
     *
     * @author Alejandro Hernandez
     * @param element WebElement
     * @param textElementValue Text to be present in the element's value attribute
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     */
    public boolean waitForElementTextPresentValue(WebElement element, String textElementValue, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.textToBePresentInElementValue(element, textElementValue));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void waitForPageToLoad(int timeOutInSeconds){
        WebDriverWait wait= new WebDriverWait(webDriver, 30);
        JavascriptExecutor jsExecutor = (JavascriptExecutor)webDriver;

        ExpectedCondition<Boolean> jsLoad = webDriver ->  ((JavascriptExecutor)webDriver)
                .executeScript("return document.readyState").toString().equals("complete");

        boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");

        if(!jsReady) {
            wait.until(jsLoad);
        }else {
            //Settings.Logs.Write("Page is ready !");
        }
    }
}
