package base.functions;

import base.driverInitialize.DriverFactory;

import com.github.javafaker.Faker;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import utils.FileReading;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class CommonFunctions {

    private WebDriver driver = DriverFactory.getDriver();

    protected FileReading fileReading = new FileReading();

    private Logger logger = Logger.getLogger(CommonFunctions.class);

    public CommonFunctions() {
        fileReading.setLog4jFile();
    }

    protected WebElement getWebElement(By locator) {
        return driver.findElement(locator);
    }

    protected List<WebElement> getWebElementList(By locator) {
        return driver.findElements(locator);
    }


    /**
     * Return true if a WebElement is found or false if it's not found
     *
     * @author Alejandro Hernandez
     * @param webElement to find
     * @param timeOutInMinutes Time to wait in minutes.
     * @param pollingEvery Seconds to search a WebElement every specific second.
     * @throws Exception if the element is not found
     */
    protected void waitForElementFluentMinutes(WebElement webElement,int timeOutInMinutes, int pollingEvery){
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver);
            wait.withTimeout(Duration.ofMinutes(timeOutInMinutes));
            wait.pollingEvery(Duration.ofSeconds(pollingEvery));
            wait.ignoring(NoSuchElementException.class);
            wait.ignoring(TimeoutException.class);
            wait.ignoring(StaleElementReferenceException.class);

            WebElement el = wait.until(new Function<WebDriver, WebElement>(){
                public WebElement apply(WebDriver driver) {
                    logger.info("The WebElement was found: "+getWebElementLocatorPath(webElement));
                    return webElement;
                }
            });

        }catch (Exception e) {
            logger.error("The WebElement "+getWebElementLocatorPath(webElement)+" was not found");
            new NoSuchElementException("The WebElement was not found");
        }
    }

    /**
     * Return true if a WebElement is found or false if it's not found
     *
     * @author Alejandro Hernandez
     * @param webElement to find
     * @param timeOutInSeconds Time to wait in seconds.
     * @param pollingEvery Seconds to search a WebElement every specific second.
     * @throws Exception if the element is not found
     */
    protected void waitForElementFluentSeconds(WebElement webElement,int timeOutInSeconds, int pollingEvery){
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver);
            wait.withTimeout(Duration.ofSeconds(timeOutInSeconds));
            wait.pollingEvery(Duration.ofSeconds(pollingEvery));
            wait.ignoring(NoSuchElementException.class);
            wait.ignoring(TimeoutException.class);
            wait.ignoring(StaleElementReferenceException.class);

            WebElement el = wait.until(new Function<WebDriver, WebElement>(){
                public WebElement apply(WebDriver driver) {
                    logger.info("The WebElement was found: "+getWebElementLocatorPath(webElement));
                    return webElement;
                }
            });

        }catch (Exception e) {
            logger.error("The WebElement was not found");
            new NoSuchElementException("The WebElement was not found");
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
    protected boolean waitForElementClickable(WebElement element, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.info("Element found "+getWebElementLocatorPath(element));
            return true;
        }catch (Exception e){
            logger.warn("Element was not found "+getWebElementLocatorPath(element));
            return false;
        }
    }

    /**
     * Return true if a WebElement is visible or false if it's not visible
     *
     * @author Alejandro Hernandez
     * @param webElement WebElement to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     */
    protected boolean waitForElementVisibility(WebElement webElement, int timeOutInSeconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            logger.info("Element found: " + getWebElementLocatorPath(webElement));
            return true;
        } catch (Exception e) {
            logger.warn("Element was not found: " + getWebElementLocatorPath(webElement));
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
    protected boolean waitForElementNotVisible(WebElement element, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.invisibilityOf(element));
            logger.info("Element not visible "+getWebElementLocatorPath(element));
            return true;
        }catch (Exception e){
            logger.warn("Element found "+getWebElementLocatorPath(element));
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
    protected boolean waitForElementListNotVisible(List<WebElement> elements, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
            logger.info("List of web elements is not visible "+getWebElementLocatorPath(elements));
            return true;
        }catch (Exception e){
            logger.warn("List of web elements is visible "+getWebElementLocatorPath(elements));
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
    protected boolean waitForElementListVisible(List<WebElement> elements, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            logger.info("List of web elements is visible "+getWebElementLocatorPath(elements));
            return true;
        }catch (Exception e){
            logger.warn("List of web elements is not visible");
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
    protected boolean waitForAlertVisible(int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.alertIsPresent());
            logger.info("Alert is visible");
            return true;
        }catch (Exception e){
            logger.warn("Alert is not visible");
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
    protected boolean waitForElementAttributeContains(WebElement element, String attribute, String attributeValue,int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.attributeContains(element, attribute, attributeValue));
            logger.info("Element found "+getWebElementLocatorPath(element));
            return true;
        }catch (Exception e){
            logger.warn("Element not found "+getWebElementLocatorPath(element));
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
    protected boolean waitForElementAttributeNotEmpty(WebElement element, String attribute,int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.attributeToBeNotEmpty(element,attribute));
            logger.info("Element not found "+getWebElementLocatorPath(element));
            return true;
        }catch (Exception e){
            logger.warn("Element found "+getWebElementLocatorPath(element));
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
    protected boolean waitForElementAttributeToBe(WebElement element, String attribute, String attributeValue,int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.attributeToBe(element,attribute,attributeValue));
            logger.info("Element found "+getWebElementLocatorPath(element));
            return true;
        }catch (Exception e){
            logger.warn("Element not found "+getWebElementLocatorPath(element));
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
    protected boolean waitForElementPageTitle(String title,int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.titleIs(title));
            logger.info("The page with title "+title+ "is displayed");
            return true;
        }catch (Exception e){
            logger.info("The page with title "+title+ "is not displayed");
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
    protected boolean waitForElementUrlToBe(String url, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.urlToBe(url));
            logger.info("The page with URL "+url+ "is displayed");
            return true;
        }catch (Exception e){
            logger.warn("The page with URL "+url+ "is not displayed");
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
    protected boolean waitForElementFrameAndSwitchToIt(WebElement element, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
            logger.info("Element frame found "+getWebElementLocatorPath(element));
            return true;
        }catch (Exception e){
            logger.warn("Element frame is not found "+getWebElementLocatorPath(element));
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
    protected boolean waitForElementSelected(WebElement element, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeSelected(element));
            logger.info("WebElement is selected "+getWebElementLocatorPath(element));
            return true;
        }catch (Exception e){
            logger.warn("WebElement is not selected "+getWebElementLocatorPath(element));
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
    protected boolean waitForElementTextPresent(WebElement element, String textElement, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.textToBePresentInElement(element, textElement));
            logger.warn("WebElement "+getWebElementLocatorPath(element)+" with text "+textElement+" is displayed");
            return true;
        }catch (Exception e){
            logger.warn("WebElement "+getWebElementLocatorPath(element)+" with text "+textElement+" is not displayed");
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
    protected boolean waitForElementTextPresentValue(WebElement element, String textElementValue, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.textToBePresentInElementValue(element, textElementValue));
            logger.info("WebElement "+getWebElementLocatorPath(element)+" with text in attribute "+textElementValue+" is displayed");
            return true;
        }catch (Exception e){
            logger.warn("WebElement "+getWebElementLocatorPath(element)+" with text in attribute "+textElementValue+" is not displayed");
            return false;
        }
    }
    /**
     * This method is to give wait time to load a page.
     *
     * @author Alejandro Hernandez
     */
    protected void waitForPageToLoad() {
        WebDriverWait wait= new WebDriverWait(driver, 30);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jsLoad = webDriver ->  ((JavascriptExecutor)webDriver)
                .executeScript("return document.readyState").toString().equals("complete");

        boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");

        if(!jsReady) {
            wait.until(jsLoad);
        }else {
            logger.info("Page is ready !");
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        }
    }
    /**
     * This method is to refresh a page and give a time to load.
     *
     * @author Alejandro Hernandez
     */
    protected void reloadPage() throws InterruptedException {
        DriverFactory.getDriver().navigate().refresh();
        waitForPageToLoad();
    }
    /**
     * Return true if a WebElement is presence on the Dom not necessarily visible
     *
     * @param locator          it contains the locator (path) to search the element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author J.Ruano
     */
    protected boolean waitForElementPresenceBy(By locator, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            logger.info("Element found: " + locator.toString());
            return true;
        }catch (Exception e){
            logger.warn("Element was not found: " + locator.toString());
            return false;
        }
    }
    /**
     * Return true if a WebElement is displayed with a specific text
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search the element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForElementTextToBeBy(By locator, String text, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.textToBe(locator, text));
            logger.info("Element found: " + locator.toString());
            return true;
        }catch (Exception e){
            logger.warn("Element not found: " + locator.toString());
            return false;
        }
    }
    /**
     * Return true if a WebElement is displayed with a specific text in an element value
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search an element
     * @param text to search in element value
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForElementTextToBePresentInElementValueBy(By locator, String text, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
            logger.info("Element found: " + locator.toString()+" with value: "+text);
            return true;
        }catch (Exception e){
            logger.warn("Element not found: " + locator.toString()+" with value: "+text);
            return false;
        }
    }
    /**
     * Return true if a WebElement is selected or false if it's not
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForElementToBeSelectedBy(By locator, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeSelected(locator));
            logger.info("Element already selected: " + locator.toString());
            return true;
        }catch (Exception e){
            logger.warn("Element is not selected: " + locator.toString());
            return false;
        }
    }
    /**
     * Return true and switch if a frame is available or false if it's not
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForElementFrameToBeAvailableAndSwitchToItBy(By locator, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
            logger.info("iFrame found and switch to it: " + locator.toString());
            return true;
        }catch (Exception e){
            logger.warn("iFrame not found: " + locator.toString());
            return false;
        }
    }
    /**
     * Return true if a WebElement is displayed with a specific attribute value
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search an element
     * @param attribute to handle
     * @param value to find
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForElementAttributeToBeBy(By locator, String attribute, String value, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.attributeToBe(locator, attribute, value));
            logger.info("Element found: " + locator.toString()+" with attribute: "+attribute+" and value: "+value);
            return true;
        }catch (Exception e){
            logger.warn("Element not found: " + locator.toString());
            return false;
        }
    }
    /**
     * Return true if a WebElement is displayed contains an attribute value
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search an element
     * @param attribute to handle
     * @param value to find
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForElementAttributeContainsBy(By locator, String attribute, String value, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
            logger.info("Element found: " + locator.toString()+" with attribute: "+attribute+" and value: "+value);
            return true;
        }catch (Exception e){
            logger.info("Element found: " + locator.toString());
            return false;
        }
    }
    /**
     * Return true if a WebElement is clickable or false if it's not
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForElementToBeClickableBy(By locator, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            logger.info("Element found: " + locator.toString());
            return true;
        }catch (Exception e){
            logger.info("Element not found: " + locator.toString());
            return false;
        }
    }
    /**
     * Return true if a WebElement is visible or false if it's not
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForElementVisibilityOfElementLocatedBy(By locator, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element found: " + locator.toString());
            return true;
        }catch (Exception e){
            logger.info("Element not found: " + locator.toString());
            return false;
        }
    }
    /**
     * Return true if a WebElement has a specific selection state true/false
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search an element
     * @param selectionState true/false
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForElementSelectionStateToBeBy(By locator, boolean selectionState, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementSelectionStateToBe(locator, selectionState));
            logger.info("Element found: " + locator.toString());
            return true;
        }catch (Exception e){
            logger.warn("Element not found: " + locator.toString());
            return false;
        }
    }
    /**
     * Return true if a WebElement is not visible or false if it is visible
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForElementInvisibilityOfElementLocatedBy(By locator, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            logger.info("Element not found: " + locator.toString());
            return true;
        }catch (Exception e){
            logger.warn("Element found: " + locator.toString());
            return false;
        }
    }
    /**
     * Return true if a WebElement is not visible  with a specific text or false if it is visible
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search an element
     * @param text to search
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForElementInvisibilityOfElementWithTextBy(By locator, String text, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
            logger.info("Element not found: " + locator.toString());
            return true;
        }catch (Exception e){
            logger.warn("Element found: " + locator.toString());
            return false;
        }
    }
    /**
     * Return true if a specific number of elements are displayed or false if it is visible
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search an element
     * @param numberElements to search
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForNumberOfElementsToBe(By locator, int numberElements,int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.numberOfElementsToBe(locator, numberElements));
            logger.info("Elements found: " + locator.toString()+ " number of elements: "+numberElements);
            return true;
        }catch (Exception e){
            logger.warn("Expected number of elements not found");
            return false;
        }
    }
    /**
     * Return true if the number of elements is less than the expected
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search an element
     * @param numberElements to search
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForNumberOfElementsToBeLessThanBy(By locator, int numberElements,int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(locator, numberElements));
            logger.info("Elements found: " + locator.toString()+ " number of elements less than: "+numberElements);
            return true;
        }catch (Exception e){
            logger.warn("Expected number of elements not found");
            return false;
        }
    }
    /**
     * Return true if the number of elements is more than the expected
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search an element
     * @param numberElements to search
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForNumberOfElementsToBeMoreThanBy(By locator, int numberElements,int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, numberElements));
            logger.info("Elements found: " + locator.toString()+ " number of elements more than: "+numberElements);
            return true;
        }catch (Exception e){
            logger.warn("Expected number of elements not found");
            return false;
        }
    }
    /**
     * Return true if all the elements were found
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForPresenceOfAllElementsLocatedBy(By locator, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            logger.info("Elements found: " + locator.toString());
            return true;
        }catch (Exception e){
            logger.info("Elements not found: " + locator.toString());
            return false;
        }
    }
    /**
     * Return true a specific text is displayed in an element found
     *
     * @author Alejandro Hernandez
     * @param locator it contains the locator (path) to search an element
     * @param text to found
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForTextToBePresentInElementLocatedBy(By locator, String text, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            return true;
        }catch (Exception e){
            return false;
        }
    }


    /**
     * Method used to click and wait for a clickable WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the By Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void clickElementClickable(By webElement, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            clickWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element is not clickable");
            throw new NoSuchElementException("Element not clickable");
        }
    }

    /**
     * Method used to click and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the By Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void clickElementVisible(By webElement, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            clickWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the By Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void doubleClickElementVisible(By webElement, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            doubleClickWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click, move and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the By Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void doubleClickAndMoveToElementVisible(By webElement, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            doubleClickAndMoveToWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click and wait for a clickable WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void doubleClickElementClickable(By webElement, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            doubleClickWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click, move and wait for a clickable WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the By Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void doubleClickAndMoveToElementClickable(By webElement, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            doubleClickAndMoveToWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to click, move and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void clickAndMoveToElementVisible(By webElement, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            clickAndMoveToWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to click, move and wait for a clickable WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the By Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void clickAndMoveToElementClickable(By webElement, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            clickAndMoveToWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element is not clickable");
            throw new NoSuchElementException("Element not clickable");
        }
    }
    /**
     * Method used to click and wait for a clickable WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void clickElementClickable(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            clickWebElementByActions(webElement);
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element is not clickable");
            throw new NoSuchElementException("Element not clickable");
        }
    }

    /**
     * Method used to click and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void clickElementVisible(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            clickWebElementByActions(webElement);
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void doubleClickElementVisible(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            doubleClickWebElementByActions(webElement);
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click, move and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void doubleClickAndMoveToElementVisible(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            doubleClickAndMoveToWebElementByActions(webElement);
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click and wait for a clickable WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void doubleClickElementClickable(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            doubleClickWebElementByActions(webElement);
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click, move and wait for a clickable WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void doubleClickAndMoveToElementClickable(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            doubleClickAndMoveToWebElementByActions(webElement);
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to click, move and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void clickAndMoveToElementVisible(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            clickAndMoveToWebElementByActions(webElement);
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to click, move and wait for a clickable WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void clickAndMoveToElementClickable(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            clickAndMoveToWebElementByActions(webElement);
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element is not clickable");
            throw new NoSuchElementException("Element not clickable");
        }
    }

    /**
     * Method used to click an element and if there is an "ElementClickInterceptedException" it will click again
     *
     * @param wElement contains the Element to do click
     * @return returns true if the click was done successfully
     * @throws Exception
     * @author J.Ruano
     */
    protected boolean clickMethod(WebElement wElement) throws Exception {
        boolean statusOperation = scrollToElementByCoordinates(wElement);
        if (!statusOperation) {
            statusOperation = scrollMethodToWebElement(wElement);
            if (!statusOperation) {
                statusOperation = scrollMethodToWebElementByActions(wElement);
                if (!statusOperation) {
                    logger.info("NONE OF THE METHODS DID WORK TO SCROLL DOWN USING PIXELS");
                }
            }
        }
        if (waitForElementClickable(wElement, 10) && statusOperation) {
            try {
                wElement.click();
                statusOperation = true;
            } catch (ElementClickInterceptedException e) {
                statusOperation = clickElementActions(wElement);
            }
        }
        return statusOperation;
    }

    /**
     * This method will scroll to the Element using the scroll into view at Top of the element With JS
     *
     * @param wElement It contains the WebElement
     * @throws Exception
     * @author J.Ruano
     */
    protected boolean scrollMethodToWebElement(WebElement wElement) throws Exception {
        //Arguments to get into the middle of the WebElement, using as arguments in the java script
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        //===========================================================================
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        try{
            //logger.info("USING " + usedMethod + " TO SCROLL TO AN ELEMENT");
            jsExecutor.executeScript(scrollElementIntoMiddle,wElement);
            return waitForElementVisibility(wElement, 10);
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return false;
        }
    }

    /**
     * Scroll into the page Up or Down using amount of pixels
     *
     * @param scrollDirection can be Top or Bottom of the page
     * @param pixels          Is an integer that contains the amount of pixels to scroll up or down when "up" or "down" word are use in the "scrollDirection"
     * @throws Exception
     * @author J.Ruano
     */
    protected boolean scrollMethodByPixels(String scrollDirection, int pixels) throws Exception {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String strPixels = String.valueOf(pixels);
        boolean statusOperation = false;
        double startPositionY = (double) jsExecutor.executeScript("return window.pageYOffset;");
        double endPositionY = 0;
        try {
            switch (scrollDirection.toLowerCase().trim()) {
                case "up":
                    //logger.info("USING " + usedMethod + " TO SCROLL DOWN USING PIXELS");
                    jsExecutor.executeScript("window.scrollTo(0," + strPixels + ")");
                    endPositionY = (double) jsExecutor.executeScript("return window.pageYOffset;");
                    break;
                case "down":
                    String strPixelsBKP = "-" + strPixels;
                    pixels = Integer.parseInt(strPixelsBKP);
                    //logger.info("USING " + usedMethod + " TO SCROLL UP USING PIXELS");
                    jsExecutor.executeScript("window.scrollTo(0," + strPixels + ")");
                    endPositionY = (double) jsExecutor.executeScript("return window.pageYOffset;");
                    break;
            }
            if (endPositionY != startPositionY) {
                statusOperation = true;
            } else {
                statusOperation = false;
            }
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
        return statusOperation;
    }

    /**
     * @param wElement contains the Element to get the coordinates X,Y and scroll base on coordinates
     * @throws Exception
     * @author J.Ruano
     */
    protected boolean scrollToElementByCoordinates(WebElement wElement) throws Exception {
        Point point = wElement.getLocation();
        int x_coordinate = point.getX();
        int y_coordinate = point.getY();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(" + x_coordinate + ", " + y_coordinate + ");");
        return waitForElementVisibility(wElement, 10);
    }

    /**
     * Scroll to the BOTTOM of the page
     *
     * @author Alejandro Hernandez
	 * @throws Exception
     */
    protected void scrollBottom() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            logger.info("Scrolled to the bottom page");
        } catch (Exception e) {
            logger.error("Unable to scroll to the bottom");
            throw new NoSuchElementException("Unable to scroll to the bottom");
        }
    }
    /**
     * Scroll to the TOP of the page
     *
     * @author Alejandro Hernandez
     * @throws Exception
     */
    protected void scrollTop() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
            logger.info("Scrolled to the top page");
        } catch (Exception e) {
            logger.error("Unable to scroll to the top");
            throw new NoSuchElementException("Unable to scroll to the top");
        }
    }
    /**
     * This method is to scroll to a webElement in specific with JS
     *
     * @author Alejandro Hernandez
     * @param webElement to scroll
     * @throws Exception
     */
    protected void scrollToWebElementJS(WebElement webElement) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
        } catch (Exception e) {
            logger.error("Web Element not found or invalid");
            throw new NoSuchElementException("Web Element not found or invalid");
        }
    }

    /**
     * This method is used to move to an element by Action Class
     *
     * @param wElement it contains the WebElement To Move
     * @throws Exception
     * @author J.Ruano
     */
    protected boolean scrollMethodToWebElementByActions(WebElement wElement) throws Exception {
        Actions actions = new Actions(driver);
        try {
            actions.moveToElement(wElement).build().perform();
            return waitForElementVisibility(wElement, 10);
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }
    /**
     * This method is used to move to a visible element by Action Class
     *
     * @author Alejandro Hernandez
     * @param wElement contains the WebElement to move
     * @param timeSeconds to wait
     * @throws Exception
     */
    protected void scrollToWebElementVisibleByAction(WebElement wElement, int timeSeconds) throws Exception {
        if(waitForElementVisibility(wElement, timeSeconds)){
            scrollToWebElementByAction(wElement);
        }else{
            logger.error("WebElement not located");
            new NoSuchElementException("Element not found");
        }
    }
    /**
     * This method is used to move to a clickable element by Action Class
     *
     * @author Alejandro Hernandez
     * @param wElement contains the WebElement to move
     * @param timeSeconds to wait
     * @throws Exception
     */
    protected void scrollToWebElementClickableByAction(WebElement wElement, int timeSeconds) throws Exception {
        if(waitForElementClickable(wElement, timeSeconds)){
            scrollToWebElementByAction(wElement);
        }else{
            new NoSuchElementException("Element not found");
        }
    }

    /**
     * Click to an element with the Actions Class
     *
     * @param wElement contains the Element to do click
     * @return returns true if the click was done successfully
     * @throws Exception
     * @author J.Ruano
     */
    protected boolean clickElementActions(WebElement wElement) throws Exception {
        boolean statusOperation = false;
        Actions actions = new Actions(driver);
        try {
            actions.click(wElement).build().perform();
            statusOperation = true;
        } catch (ElementClickInterceptedException e) {
            statusOperation = clickElementJS(wElement);
        }
        return statusOperation;
    }

    /**
     * Click to an element with JavaScript
     *
     * @param wElement contains the Element to do click
     * @return returns true if the click was done successfully
     * @throws Exception
     * @author J.Ruano
     */
    protected boolean clickElementJS(WebElement wElement) throws Exception {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click();", wElement);
            logger.info("Web Element selected");
            return true;
        }catch (Exception e) {
            logger.error("Web element not found");
            throw new NoSuchElementException("Web Element not found");
        }
    }
    /**
     * Method used to sendKeys, move and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void sendKeysAndMoveToElementVisible(By webElement, String text, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            sendKeysAndMoveToWebElementByActions(getWebElement(webElement), text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to sendKeys, move and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void sendKeysAndMoveToElementClickable(By webElement, String text, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            sendKeysAndMoveToWebElementByActions(getWebElement(webElement), text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to sendKeys and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void sendKeysElementVisible(By webElement, String text, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            sendKeysWebElementByActions(getWebElement(webElement), text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to sendKeys and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void sendKeysElementClickable(By webElement, String text, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            sendKeysWebElementByActions(getWebElement(webElement), text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to select a dropdown option by text
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param text to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectDropDownByText(By webElement, String text, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            selectDropDownByText(getWebElement(webElement), text);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to move and select a dropdown option by text
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param text to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectAndMoveDropdownByText(By webElement, String text, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            selectAndMoveToDropDownByText(getWebElement(webElement), text);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to select a dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param index to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectDropDownByIndex(By webElement, int index, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            selectDropDownByIndex(getWebElement(webElement), index);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to move and select a dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param index to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectAndMoveDropdownByIndex(By webElement, int index, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            selectAndMoveToDropDownByIndex(getWebElement(webElement), index);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to select a dropdown option by text
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param text to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectDropDownClickableByText(By webElement, String text, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            selectDropDownByText(getWebElement(webElement), text);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to move and select a dropdown option by text
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param text to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectAndMoveDropdownClickableByText(By webElement, String text, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            selectAndMoveToDropDownByText(getWebElement(webElement), text);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to select a dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param index to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectDropDownClickableByIndex(By webElement, int index, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            selectDropDownByIndex(getWebElement(webElement), index);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to move and select a dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param index to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectAndMoveDropdownClickableByIndex(By webElement, int index, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            selectAndMoveToDropDownByIndex(getWebElement(webElement), index);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to select a random dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectDropDownVisibleRandomOption(By webElement, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            selectRandomDropDownOption(getWebElement(webElement));
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to select a random dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectAndMoveDropDownVisibleRandomOption(By webElement, int waitTime) throws Exception {
        if(waitForElementPresenceBy(webElement, waitTime)){
            scrollMethodToWebElementByActions(getWebElement(webElement));
            selectRandomDropDownOption(getWebElement(webElement));
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to select a random dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectDropDownClickableRandomOption(By webElement, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            selectRandomDropDownOption(getWebElement(webElement));
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to select a random dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectAndMoveDropDownClickableRandomOption(By webElement, int waitTime) throws Exception {
        if(waitForElementToBeClickableBy(webElement, waitTime)){
            scrollMethodToWebElementByActions(getWebElement(webElement));
            selectRandomDropDownOption(getWebElement(webElement));
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to return a random webElement from a List<WebElement>
     *
     * @author Alejandro Hernandez
     * @param webElementList
     * @param timeSeconds to wait for visible elements
     * @throws Exception
     */
    protected WebElement getRandomWebElementFromList(By webElementList, int timeSeconds) throws IllegalAccessException {
        try {
            if(waitForPresenceOfAllElementsLocatedBy(webElementList, timeSeconds)) {
                Random random = new Random();
                return getWebElementList(webElementList).get(random.nextInt(getWebElementList(webElementList).size()));
            } else{
                logger.error("The list of WebElements was not found/visible");
                throw new NoSuchElementException("WebElements are not visible");
            }
        } catch (Exception e) {
            logger.error("List<WebElement> type invalid");
            throw new IllegalAccessException("List<WebElement> type invalid");
        }
    }
    /**
     * Method used to return a webElement with specific attribute value from a List<WebElement>
     *
     * @author Alejandro Hernandez
     * @param webElementList
     * @param attribute to handle
     * @param attributeValue to search
     * @throws Exception
     */
    protected WebElement getWebElementByAttributeFromList(By webElementList, String attribute, String attributeValue) throws IllegalAccessException {
        WebElement element=null;
        for(WebElement elementList : getWebElementList(webElementList)){
            if(elementList.getAttribute(attribute).equalsIgnoreCase(attributeValue)){
                element = elementList;
                break;
            }
        }
        if(element!=null){
            return element;
        }else{
            logger.error("Not element found with attribute: "+attribute+" and value: "+attributeValue);
            throw new NoSuchElementException("Not element found");
        }
    }
    /**
     * Method used to validate if a visible webElement is selected or not
     *
     * @author Alejandro Hernandez
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is selected or false if it is not selected
     * @throws Exception
     */
    protected boolean isVisibleElementSelected(By webElement, int timeSeconds){
        if(waitForElementPresenceBy(webElement, timeSeconds)){
            logger.info("WebElement selected: "+getWebElementLocatorPath(getWebElement(webElement))+": "+getWebElement(webElement).isSelected());
            return getWebElement(webElement).isSelected();
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to validate if a visible webElement is selected or not
     *
     * @author Alejandro Hernandez
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is clickable or false if it is not selected
     * @throws Exception
     */
    protected boolean isClickableElementSelected(By webElement, int timeSeconds){
        if(waitForElementToBeClickableBy(webElement, timeSeconds)){
            logger.info("WebElement selected: "+getWebElementLocatorPath(getWebElement(webElement))+": "+getWebElement(webElement).isSelected());
            return getWebElement(webElement).isSelected();
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to validate if a visible webElement is enabled or not
     *
     * @author Alejandro Hernandez
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is enabled or false if it is not
     * @throws Exception
     */
    protected boolean isVisibleElementEnabled(By webElement, int timeSeconds){
        if(waitForElementPresenceBy(webElement, timeSeconds)){
            logger.info("WebElement enabled: "+getWebElementLocatorPath(getWebElement(webElement))+": "+getWebElement(webElement).isSelected());
            return getWebElement(webElement).isEnabled();
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to validate if a clickable webElement is enabled or not
     *
     * @author Alejandro Hernandez
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is enabled or false if it is not
     * @throws Exception
     */
    protected boolean isClickableElementEnabled(By webElement, int timeSeconds){
        if(waitForElementToBeClickableBy(webElement, timeSeconds)){
            logger.info("WebElement enabled: "+getWebElementLocatorPath(getWebElement(webElement))+": "+getWebElement(webElement).isSelected());
            return getWebElement(webElement).isEnabled();
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to sendKeys, move and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void sendKeysAndMoveToElementVisible(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            sendKeysAndMoveToWebElementByActions(webElement, text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to sendKeys, move and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void sendKeysAndMoveToElementClickable(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            sendKeysAndMoveToWebElementByActions(webElement, text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
         /**
     * Method used to sendKeys and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void sendKeysElementVisibleWithCoordinates(WebElement webElement, String text, int xOffset, int yOffset, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement, 5, 5).click().sendKeys(text).perform();
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to sendKeys and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void sendKeysElementVisible(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            sendKeysWebElementByActions(webElement, text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to sendKeys and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void sendKeysElementClickable(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            sendKeysWebElementByActions(webElement, text);
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to select a dropdown option by text
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param text to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectDropDownByText(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            selectDropDownByText(webElement, text);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to move and select a dropdown option by text
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param text to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectAndMoveDropdownByText(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            selectAndMoveToDropDownByText(webElement, text);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to select a dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param index to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectDropDownByIndex(WebElement webElement, int index, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            selectDropDownByIndex(webElement, index);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to move and select a dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param index to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectAndMoveDropdownByIndex(WebElement webElement, int index, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            selectAndMoveToDropDownByIndex(webElement, index);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to select a dropdown option by text
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param text to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectDropDownClickableByText(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            selectDropDownByText(webElement, text);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to move and select a dropdown option by text
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param text to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectAndMoveDropdownClickableByText(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            selectAndMoveToDropDownByText(webElement, text);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to select a dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param index to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectDropDownClickableByIndex(WebElement webElement, int index, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            selectDropDownByIndex(webElement, index);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to move and select a dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param index to select from a dropdown
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectAndMoveDropdownClickableByIndex(WebElement webElement, int index, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            selectAndMoveToDropDownByIndex(webElement, index);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to get a random date with format mm/dd/yyyy
     *
     * @author Alejandro Hernandez
     * @return String with the date format
     * @throws Exception
     */
    protected String getRandomDate() {
        Faker faker = new Faker();
        Date randomDate = faker.date().birthday(18,70);
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(randomDate);
    }
    /**
     * Method used to select a random dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectDropDownVisibleRandomOption(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            selectRandomDropDownOption(webElement);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to select a random dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectAndMoveDropDownVisibleRandomOption(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            scrollMethodToWebElementByActions(webElement);
            selectRandomDropDownOption(webElement);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to select a random dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectDropDownClickableRandomOption(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            selectRandomDropDownOption(webElement);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to select a random dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void selectAndMoveDropDownClickableRandomOption(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            scrollMethodToWebElementByActions(webElement);
            selectRandomDropDownOption(webElement);
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to return a random webElement from a List<WebElement>
     *
     * @author Alejandro Hernandez
     * @param webElementList
     * @param timeSeconds to wait for visible elements
     * @throws Exception
     */
    protected WebElement getRandomWebElementFromList(List<WebElement> webElementList, int timeSeconds) throws IllegalAccessException {
        try {
            if(waitForElementListVisible(webElementList, timeSeconds)) {
                Random random = new Random();
                return webElementList.get(random.nextInt(webElementList.size()));
            } else{
                logger.error("The list of WebElements was not found/visible");
                throw new NoSuchElementException("WebElements are not visible");
            }
        } catch (Exception e) {
            logger.error("List<WebElement> type invalid");
            throw new IllegalAccessException("List<WebElement> type invalid");
        }
    }
    /**
     * Method used to return a webElement with specific attribute value from a List<WebElement>
     *
     * @author Alejandro Hernandez
     * @param webElementList
     * @param attribute to handle
     * @param attributeValue to search
     * @throws Exception
     */
    protected WebElement getWebElementByAttributeFromList(List<WebElement> webElementList, String attribute, String attributeValue) throws IllegalAccessException {
        WebElement element=null;
        for(WebElement elementList : webElementList){
            if(elementList.getAttribute(attribute).equalsIgnoreCase(attributeValue)){
                element = elementList;
                break;
            }
        }

        if(element!=null){
            return element;
        }else{
            logger.error("Not element found with attribute: "+attribute+" and value: "+attributeValue);
            throw new NoSuchElementException("Not element found");
        }
    }
    /**
     * Method used to validate if a visible webElement is selected or not
     *
     * @author Alejandro Hernandez
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is selected or false if it is not selected
     * @throws Exception
     */
    protected boolean isVisibleElementSelected(WebElement webElement, int timeSeconds){
        if(waitForElementVisibility(webElement, timeSeconds)){
            logger.info("WebElement selected: "+getWebElementLocatorPath(webElement)+": "+webElement.isSelected());
            return webElement.isSelected();
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to validate if a visible webElement is selected or not
     *
     * @author Alejandro Hernandez
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is clickable or false if it is not selected
     * @throws Exception
     */
    protected boolean isClickableElementSelected(WebElement webElement, int timeSeconds){
        if(waitForElementClickable(webElement, timeSeconds)){
            logger.info("WebElement selected: "+getWebElementLocatorPath(webElement)+": "+webElement.isSelected());
            return webElement.isSelected();
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to validate if a visible webElement is enabled or not
     *
     * @author Alejandro Hernandez
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is enabled or false if it is not
     * @throws Exception
     */
    protected boolean isVisibleElementEnabled(WebElement webElement, int timeSeconds){
        if(waitForElementVisibility(webElement, timeSeconds)){
            logger.info("WebElement enabled: "+getWebElementLocatorPath(webElement)+": "+webElement.isSelected());
            return webElement.isEnabled();
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to validate if a clickable webElement is enabled or not
     *
     * @author Alejandro Hernandez
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is enabled or false if it is not
     * @throws Exception
     */
    protected boolean isClickableElementEnabled(WebElement webElement, int timeSeconds){
        if(waitForElementClickable(webElement, timeSeconds)){
            logger.info("WebElement enabled: "+getWebElementLocatorPath(webElement)+": "+webElement.isSelected());
            return webElement.isEnabled();
        }else{
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to switch in a frame By WebElement, Index or Name
     *
     * @author Alejandro Hernandez
     * @param frame WebElement, int or String
     * @param timeSeconds wait only if the WebElement option is used.
     * @throws Exception
     */
    protected <f> void switchToFrameByWebElementIndexOrName(f frame, int timeSeconds){
        try{
            String frameType = frame.getClass().getName();
            if(frameType.contains("Integer")) {
                driver.switchTo().frame((Integer) frame);
            }else{
                if(frameType.contains("String")) {
                    driver.switchTo().frame(frame.toString());
                }else{
                    if(waitForElementVisibility((WebElement) frame, timeSeconds)){
                        driver.switchTo().frame((WebElement) frame);
                    }else{
                        logger.error("The Web Element was not found");
                        throw new NoSuchElementException("Element not valid");
                    }
                }
            }
        }catch(Exception e){
            logger.error(e);
        }
    }
    /**
     * Method used to switch to the parent frame
     *
     * @author Alejandro Hernandez
     * @throws Exception
     */
    protected void switchToParentFrame(){
        driver.switchTo().parentFrame();
    }
    /**
     * Method used to accept an alert
     *
     * @author Alejandro Hernandez
     * @param timeSeconds wait for an alter to be visible
     * @throws Exception
     */
    protected void switchToAlertAccept(int timeSeconds){
        if(waitForAlertVisible(timeSeconds)){
            driver.switchTo().alert().accept();
        } else{
            logger.error("The alert is not displayed");
            new NoSuchElementException("Element not found");
        }
    }
    /**
     * Method used to dismiss an alert
     *
     * @author Alejandro Hernandez
     * @param timeSeconds wait for an alter to be visible
     * @throws Exception
     */
    protected void switchToAlertDismiss(int timeSeconds){
        if(waitForAlertVisible(timeSeconds)){
            driver.switchTo().alert().dismiss();
        } else{
            logger.error("The alert is not displayed");
            new NoSuchElementException("Element not found");
        }
    }
    /**
     * Method used to get text from an alert
     *
     * @author Alejandro Hernandez
     * @param timeSeconds wait for an alter to be visible
     * @return String
     * @throws Exception
     */
    protected String switchToAlertGetText(int timeSeconds){
        if(waitForAlertVisible(timeSeconds)){
            return driver.switchTo().alert().getText();
        } else{
            logger.error("The alert is not displayed");
            throw new NoSuchElementException("Element not found");
        }
    }
    /**
     * Method used to accept an alert
     *
     * @author Alejandro Hernandez
     * @param text to be send in the alert
     * @param timeSeconds wait for an alter to be visible
     * @throws Exception
     */
    protected void switchToAlertSendKeys(String text, int timeSeconds){
        if(waitForAlertVisible(timeSeconds)){
            driver.switchTo().alert().sendKeys(text);
        } else{
            logger.error("The alert is not displayed");
            new NoSuchElementException("Element not found");
        }
    }
    /**
     * Method used to switch to a window tab by index
     *
     * @author Alejandro Hernandez
     * @param tabIndex tab number to switch
     * @throws Exception
     */
    protected void switchToWindow(int tabIndex) {
        try {
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabIndex));
            logger.info("Change to tab: "+tabIndex);
        } catch (Exception e) {
            logger.error("The tab was not found");
            throw new IndexOutOfBoundsException("Tab index not found");
        }
    }
    /**
     * Method used to get the number of windows open
     *
     * @author Alejandro Hernandez
     * @return int
     */
    protected int getOpenTabsSize(){
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        return tabs.size();
    }
    /**
     * Method used to close the current window
     *
     * @author Alejandro Hernandez
     */
    protected void closeWindow(){
        driver.close();
    }

    //***********************************************************************
    // private methods

    /**
     * This method is used to SendKeys to a WebElement by Action
     *
     * @author Alejandro Hernandez
     * @param wElement
     */
    protected void sendKeysWebElementByActions(WebElement wElement, String text) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.click(wElement).sendKeys(wElement, text).build().perform();
            logger.info("Element found: "+getWebElementLocatorPath(wElement));
            logger.info("Keys sent: "+text);
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: "+getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
        }
    }
    /**
     * This method is used to SendKeys without specify a WebElement
     *
     * @author Alejandro Hernandez
     * @param text
     * @throws Exception
     */
    protected void sendKeysByActions(String text) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(text).build().perform();
            logger.info("Keys sent: "+text);
        } catch (Exception e) {
            logger.error("Element not visible or not clickable");
            logger.error(e.getMessage());
        }
    }
    /**
     * This method is used to move and SendKeys to a WebElement by Action
     *
     * @author Alejandro Hernandez
     * @param wElement
     */
    protected void sendKeysAndMoveToWebElementByActions(WebElement wElement, String text) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(wElement).sendKeys(wElement, text).build().perform();
            logger.info("Element found: "+getWebElementLocatorPath(wElement));
            logger.info("Keys sent: "+text);
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: "+getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to move and click a WebElement by Action Class
     *
     * @author Alejandro Hernandez
     * @param wElement
     */
    private void clickAndMoveToWebElementByActions(WebElement wElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(wElement).click(wElement).build().perform();
            logger.info("Element found: "+getWebElementLocatorPath(wElement));
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: "+getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to click a WebElement by Action Class
     *
     * @author Alejandro Hernandez
     * @param wElement
     */
    private void clickWebElementByActions(WebElement wElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.click(wElement).build().perform();
            logger.info("Element found: "+getWebElementLocatorPath(wElement));
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: "+getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to move and double click a WebElement by Action Class
     *
     * @author Alejandro Hernandez
     * @param wElement
     */
    private void doubleClickAndMoveToWebElementByActions(WebElement wElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(wElement).doubleClick(wElement).build().perform();
            logger.info("Element found: "+getWebElementLocatorPath(wElement));
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: "+getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to double click a WebElement by Action Class
     *
     * @author Alejandro Hernandez
     * @param wElement
     */
    private void doubleClickWebElementByActions(WebElement wElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.doubleClick(wElement).build().perform();
            logger.info("Element found: "+getWebElementLocatorPath(wElement));
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: "+getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
        }
    }

    /**
     * Return the WebElement locator string
     *
     * @author Alejandro Hernandez
     * @param webElement
     * @return WebElement locator string.
     * @throws null if the webElement is empty, null or the string has a different format.
     */
    private String getWebElementLocatorPath(WebElement webElement){
        try{
            return webElement.toString().split("-> ")[1].replace("]","");
        }catch(Exception e){
            return webElement.toString().split("DefaultElementLocator")[1].replace("'","");
        }
    }
    /**
     * Return the WebElement locator string
     *
     * @author Alejandro Hernandez
     * @param webElement
     * @return WebElement locator string.
     */
    private String getWebElementLocatorPath(List<WebElement> webElement){
        try{
            return webElement.toString().split("-> ")[1].replace("]","");
        }catch(Exception e){
            return webElement.toString().split("DefaultElementLocator")[1].replace("'","");
        }
    }
    /**
     * This method is used to move to a visible element by Action Class
     *
     * @author Alejandro Hernandez
     * @param wElement contains the WebElement to move
     * @throws Exception
     */
    private void scrollToWebElementByAction(WebElement wElement) throws Exception {
        Actions actions = new Actions(driver);
        try {
            actions.moveToElement(wElement).build().perform();
        } catch (Exception e) {
            logger.error("The element was not found");
            logger.error(e.getMessage());
        }
    }
    /**
     * This method is used to move and select a dropdown option by text
     *
     * @author Alejandro Hernandez
     * @param webElement
     * @param text to select from dropdown
     * @throws Exception
     */
    private void selectAndMoveToDropDownByText(WebElement webElement, String text) {
        try{
            scrollToWebElementByAction(webElement);
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Selected option: "+text);
        }catch (Exception e) {
            logger.error("Element not clickable");
            logger.error(e.getMessage());
        }
    }
    /**
     * This method is used to move and select a dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement
     * @param index to select from dropdown
     * @throws Exception
     */
    private void selectAndMoveToDropDownByIndex(WebElement webElement, int index) {
        try{
            scrollToWebElementByAction(webElement);
            Select select = new Select(webElement);
            select.selectByIndex(index);
            logger.info("Selected option: "+index);
        }catch (Exception e) {
            logger.error("Element not clickable");
            logger.error(e.getMessage());
        }
    }
    /**
     * This method is used to move and select a dropdown option by text
     *
     * @author Alejandro Hernandez
     * @param webElement
     * @param text to select from dropdown
     * @throws Exception
     */
    private void selectDropDownByText(WebElement webElement, String text) {
        try{
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Selected option: "+text);
        }catch (Exception e) {
            logger.error("Element not clickable");
            logger.error(e.getMessage());
        }
    }
    /**
     * This method is used to select a dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement
     * @param index to select from index
     * @throws Exception
     */
    private void selectDropDownByIndex(WebElement webElement, int index) {
        try{
            Select select = new Select(webElement);
            select.selectByIndex(index);
            logger.info("Selected option: "+index);
        }catch (Exception e) {
            logger.error("Element not clickable");
            logger.error(e.getMessage());
        }
    }
    /**
     * This method is used to select a random dropdown option by index
     *
     * @author Alejandro Hernandez
     * @param webElement
     * @throws Exception
     */
    private void selectRandomDropDownOption(WebElement webElement) {
        try{
            Random random = new Random();
            Select select = new Select(webElement);
            int optionIndex = random.nextInt(select.getOptions().size() - 1);
            select.selectByIndex(optionIndex++);
            logger.info("Selected option: "+optionIndex++);
        }catch (Exception e) {
            logger.error("Element not clickable");
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to move and select a dropdown option by text
     *
     * @author J.Ruano
     */
    protected void switchToDefaultContentFrame() {
        driver.switchTo().defaultContent();
    }

    /**
     * This method is used to move and select a dropdown option by text
     *
     * @param name
     * @throws Exception
     * @author J.Ruano
     */
    protected void switchTabByNameSF(String name) throws Exception {
        String pathForSubTabs = "//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]";
        List<WebElement> subTabsList = driver.findElements(By.xpath(pathForSubTabs));

        for (WebElement tab : subTabsList) {
            if (tab.getAttribute("title").trim().equalsIgnoreCase(name.trim())) {
                clickAndMoveToElementClickable(tab, 10);
            }
        }
    }

    /**
     * This method is used to move and select a dropdown option by text
     *
     * @param index
     * @throws Exception
     * @author J.Ruano
     */
    protected void switchTabByIndexSF(int index) throws Exception {
        String pathForSubTabs = "//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]";
        List<WebElement> subTabsList = driver.findElements(By.xpath(pathForSubTabs));
        clickAndMoveToElementClickable(subTabsList.get(index), 10);
    }

    /**
     * Method used to select a random dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author J.Ruano
     */
    protected void selectDropDownRandomOptionNone(WebElement webElement, int waitTime) throws Exception {
        if (waitForElementClickable(webElement, waitTime)) {
            selectRandomDropDownNotNone(webElement);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * This method is used to select a random dropdown option by index
     *
     * @param webElement
     * @throws Exception
     * @author J.Ruano
     */
    protected void selectRandomDropDownNotNone(WebElement webElement) {
        int optionIndex = 0;
        Random random = new Random();
        Select select = new Select(webElement);
        try {
            do {
                optionIndex = random.nextInt(select.getOptions().size() - 1);
            } while (optionIndex == 0);
            select.selectByIndex(optionIndex++);
            logger.info("Selected option: " + optionIndex++);
        } catch (Exception e) {
            logger.error("Element not clickable");
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to select a random dropdown option by index
     *
     * @param
     * @throws Exception
     * @author J.Ruano
     */
    protected List<WebElement> getVisibleElements(List<WebElement> webElementList) {
        List<WebElement> visibleWebElementsList = new LinkedList<>();
        for (WebElement wE:webElementList) {
            if (wE.isDisplayed()) {
                visibleWebElementsList.add(wE);
            }
        }
        return visibleWebElementsList;
    }

    protected String getWebElementText(WebElement webElement){
        try{
            return webElement.getText();
        }catch (Exception e){
            logger.error("Web Element not found: "+getWebElementLocatorPath(webElement));
            throw new NoSuchElementException("Web Element not found");
        }
    }

}