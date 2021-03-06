package base.functions;

import base.driverInitialize.DriverFactory;
import com.github.javafaker.Faker;
import org.apache.commons.compress.utils.Lists;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import utils.FileReading;
import utils.JsonFiles;

import java.io.File;
import java.lang.reflect.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import utils.Values;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class CommonFunctions {

    private WebDriver driver = DriverFactory.getDriver();
    protected FileReading fileReading = new FileReading();
    private final Logger logger = Logger.getLogger(CommonFunctions.class);
    public static int maxNumberOfTries = 0;
    public static Object reflectionReturnValue;
    Class<?> myClass;

    {
        try {
            fileReading.setLog4jFile();
            fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);
            maxNumberOfTries = Integer.parseInt(fileReading.getField(Values.TXT_RETRYWHILE));
            myClass = Class.forName(Values.REFLECTION_COMMONFUNCTIONSCLASSPATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Return a WebElement if it is found
     *
     * @param locator to find as a WebElement
     * @return WebElement if it is found
     * @author Alejandro Hernandez
     */
    protected WebElement getWebElement(By locator) throws Exception {
        WebElement webElement = null;
        try {
            Thread.sleep(2000);
            webElement = driver.findElement(locator);
        } catch (Exception e) {
            webElement = autoCasting(executeReflection(locator));
        }
        return webElement;
    }


    protected List<WebElement> getWebElementList(By locator) throws Exception {
        List<WebElement> webElements = new ArrayList<>();
        try {
            webElements = driver.findElements(locator);
        } catch (Exception e) {
            webElements = autoCasting(executeReflection(locator));
        }
        return webElements;
    }

    /**
     * Return true if a WebElement is found or false if it's not found
     *
     * @param webElement       to find
     * @param timeOutInMinutes Time to wait in minutes.
     * @param pollingEvery     Seconds to search a WebElement every specific second.
     * @author Alejandro Hernandez
     */
    protected void waitForElementFluentMinutes(WebElement webElement, int timeOutInMinutes, int pollingEvery) throws Exception {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver);
            wait.withTimeout(Duration.ofMinutes(timeOutInMinutes));
            wait.pollingEvery(Duration.ofSeconds(pollingEvery));
            wait.ignoring(NoSuchElementException.class);
            wait.ignoring(TimeoutException.class);
            wait.ignoring(StaleElementReferenceException.class);
            wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    try {
                        logger.info("The WebElement was found: " + getWebElementLocatorPath(webElement));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return webElement;
                }
            });
        } catch (Exception e) {
            logger.error(getMessage("EXC_NSE") + getWebElementLocatorPath(webElement));
            throw new NoSuchElementException(getMessage("EXC_NSE"));
        }
    }

    /**
     * Return true if a WebElement is found or false if it's not found
     *
     * @param webElement       to find
     * @param timeOutInSeconds Time to wait in seconds.
     * @param pollingEvery     Seconds to search a WebElement every specific second.
     * @throws Exception selenium Exception if the element is not found
     * @author Alejandro Hernandez
     */
    protected void waitForElementFluentSeconds(WebElement webElement, int timeOutInSeconds, int pollingEvery) throws Exception {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver);
            wait.withTimeout(Duration.ofSeconds(timeOutInSeconds));
            wait.pollingEvery(Duration.ofSeconds(pollingEvery));
            wait.ignoring(NoSuchElementException.class);
            wait.ignoring(TimeoutException.class);
            wait.ignoring(StaleElementReferenceException.class);

            WebElement el = wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    try {
                        logger.info("The WebElement was found: " + getWebElementLocatorPath(webElement));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return webElement;
                }
            });

        } catch (Exception e) {
            logger.error("The WebElement was not found");
            new NoSuchElementException("The WebElement was not found");
        }
    }

    /**
     * Return true if a WebElement is clickable or false if it's not clickable
     *
     * @param webElement       WebElement to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementClickable(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            logger.info("Element found " + getWebElementLocatorPath(webElement));
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + getWebElementLocatorPath(webElement));
            statusOperation = autoCastingBoolean(executeReflection(webElement, timeOutInSeconds));
        }
        return statusOperation;
    }

    /**
     * Return true if a WebElement is clickable or false if it's not clickable
     *
     * @param webElement       WebElement to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementClickableNoReflection(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            logger.info("Element found " + getWebElementLocatorPath(webElement));
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + getWebElementLocatorPath(webElement));
        }
        return statusOperation;
    }

    /**
     * Return true if a WebElement is visible or false if it's not visible
     *
     * @param webElement       WebElement to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    public boolean waitForElementVisibility(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            logger.info("Element found: " + getWebElementLocatorPath(webElement));
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + getWebElementLocatorPath(webElement));
            statusOperation = autoCastingBoolean(executeReflection(webElement, timeOutInSeconds));
        }
        return statusOperation;
    }

    /**
     * Return true if a WebElement is visible or false if it's not visible
     *
     * @param webElement       WebElement to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Jonathan Ruano
     */
    public boolean waitForElementVisibilityNoReflection(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            logger.info("Element found: " + getWebElementLocatorPath(webElement));
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + getWebElementLocatorPath(webElement));
        }
        return statusOperation;
    }

    /**
     * Return true if a WebElement is not visible or false if it's visible
     *
     * @param webElement       WebElement to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementNotVisible(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.invisibilityOf(webElement));
            //logger.info("Element not visible " + getWebElementLocatorPath(webElement));
            statusOperation = true;
        } catch (Exception e) {
            //logger.warn("Element found: " + getWebElementLocatorPath(webElement));
            statusOperation = autoCastingBoolean(executeReflection(webElement, timeOutInSeconds));
        }
        return statusOperation;
    }

    /**
     * Return true if a list of WebElements is not visible or false if it's visible
     *
     * @param webElements      List of WebElements to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementListNotVisible(List<WebElement> webElements, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.invisibilityOfAllElements(webElements));
            logger.info("List of web elements is not visible " + getWebElementLocatorPath(webElements));
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element List found: " + getWebElementLocatorPath(webElements));
            statusOperation = autoCastingBoolean(executeReflection(webElements, timeOutInSeconds));
        }
        return statusOperation;
    }

    /**
     * Return true if a list of WebElements is visible or false if it's not visible
     *
     * @param webElements      List of WebElements to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementListVisible(List<WebElement> webElements, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
            logger.info("List of web elements is visible " + getWebElementLocatorPath(webElements));
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element List not found");
            statusOperation = autoCastingBoolean(executeReflection(webElements, timeOutInSeconds));
        }
        return statusOperation;
    }

    /**
     * Return true if an Alert is displayed or false if it's not displayed
     *
     * @param timeOutInSeconds Seconds to wait for an Alert.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForAlertVisible(int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.alertIsPresent());
            logger.info("Alert is visible");
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Alert not found");
            statusOperation = autoCastingBoolean(executeReflection(timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if a WebElement with a specific attribute is visible or false if it's not visible
     *
     * @param webElement       WebElement to find.
     * @param attribute        WebElement Attribute
     * @param attributeValue   Attribute Value
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementAttributeContains(WebElement webElement, String attribute, String attributeValue, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.attributeContains(webElement, attribute, attributeValue));
            logger.info("Element found " + getWebElementLocatorPath(webElement));
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + getWebElementLocatorPath(webElement) + " with attribute: " + attribute + " value: " + attributeValue);
            statusOperation = autoCastingBoolean(executeReflection(webElement, attribute, attributeValue, timeOutInSeconds));
        }
        return statusOperation;
    }

    /**
     * Return true if a WebElement attribute is not empty or false if it's empty
     *
     * @param webElement       WebElement to find.
     * @param attribute        WebElement Attribute
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementAttributeNotEmpty(WebElement webElement, String attribute, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.attributeToBeNotEmpty(webElement, attribute));
            logger.info("Element not found " + getWebElementLocatorPath(webElement));
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + getWebElementLocatorPath(webElement));
            statusOperation = autoCastingBoolean(executeReflection(webElement, attribute, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * An expectation for checking given WebElement has attribute with a specific value
     *
     * @param webElement       WebElement to find.
     * @param attribute        WebElement Attribute
     * @param attributeValue   Attribute Value
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementAttributeToBe(WebElement webElement, String attribute, String attributeValue, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.attributeToBe(webElement, attribute, attributeValue));
            logger.info("Element found " + getWebElementLocatorPath(webElement));
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + getWebElementLocatorPath(webElement) + " with attribute: " + attribute);
            statusOperation = autoCastingBoolean(executeReflection(webElement, attribute, attributeValue, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if an expected page title is displayed or false if it's not displayed
     *
     * @param title            Page title value
     * @param timeOutInSeconds Seconds to wait for an expected title.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementPageTitle(String title, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.titleIs(title));
            logger.info("The page with title " + title + "is displayed");
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("The page with title was not found: " + title);
            statusOperation = autoCastingBoolean(executeReflection(title, timeOutInSeconds));
        }
        return statusOperation;
    }

    /**
     * Return true if a specific URL is displayed or false if it's not displayed
     *
     * @param url              URL to be displayed in the page
     * @param timeOutInSeconds Seconds to wait for a URL.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementUrlToBe(String url, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.urlToBe(url));
            logger.info("The page with URL " + url + "is displayed");
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("URL not found: " + url);
            statusOperation = autoCastingBoolean(executeReflection(url, timeOutInSeconds));
        }
        return statusOperation;
    }

    /**
     * Return true and switch to a frame if it's displayed or false if it's not displayed
     *
     * @param webElement       WebElement frame
     * @param timeOutInSeconds Seconds to wait for an expected frame.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementFrameAndSwitchToIt(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement));
            logger.info("Element frame found " + getWebElementLocatorPath(webElement));
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + getWebElementLocatorPath(webElement));
            statusOperation = autoCastingBoolean(executeReflection(webElement, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if a WebElement is selected or false if it's not selected
     *
     * @param webElement       WebElement
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementSelected(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.elementToBeSelected(webElement));
            logger.info("WebElement is selected " + getWebElementLocatorPath(webElement));
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + getWebElementLocatorPath(webElement));
            statusOperation = autoCastingBoolean(executeReflection(webElement, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if a WebElement has a specific text or false if it's not present
     *
     * @param webElement       WebElement
     * @param textElement      Text to wait from a WebElement
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementTextPresent(WebElement webElement, String textElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.textToBePresentInElement(webElement, textElement));
            logger.info("WebElement " + getWebElementLocatorPath(webElement) + " with text " + textElement + " is displayed");
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + getWebElementLocatorPath(webElement));
            statusOperation = autoCastingBoolean(executeReflection(webElement, textElement, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if a WebElement webElement's value has a specific text or false if it's not present
     *
     * @param webElement       WebElement
     * @param textElementValue Text to be present in the element's value attribute
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementTextPresentValue(WebElement webElement, String textElementValue, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.textToBePresentInElementValue(webElement, textElementValue));
            logger.info("WebElement " + getWebElementLocatorPath(webElement) + " with text in attribute " + textElementValue + " is displayed");
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + getWebElementLocatorPath(webElement));
            statusOperation = autoCastingBoolean(executeReflection(webElement, textElementValue, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * This method is to give wait time to load a page.
     *
     * @author Alejandro Hernandez
     */
    protected void waitForPageToLoad() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, waitDriverTimeOutInSeconds());
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jsLoad = webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").toString().equals("complete");

        boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");

        if (!jsReady) {
            wait.until(jsLoad);
        } else {
            logger.info("Page is ready !");
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        }
    }

    /**
     * This method is to refresh a page and give a time to load.
     *
     * @author Alejandro Hernandez
     */
    protected void reloadPage() throws Exception {
        DriverFactory.getDriver().navigate().refresh();
        waitForPageToLoad();
    }

    /**
     * Return true if a WebElement is presence on the Dom not necessarily visible
     *
     * @param locator          it contains the locator (path) to search the element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return a boolean value
     * @author J.Ruano
     */
    protected boolean waitForElementPresenceBy(By locator, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            logger.info("Element found: " + locator.toString());
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString());
            statusOperation = autoCastingBoolean(executeReflection(locator, timeOutInSeconds));
        }
        return statusOperation;
    }

    /**
     * Return true if a WebElement is displayed with a specific text
     *
     * @param locator          it contains the locator (path) to search the element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementTextToBeBy(By locator, String text, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.textToBe(locator, text));
            logger.info("Element found: " + locator.toString());
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString());
            statusOperation = autoCastingBoolean(executeReflection(locator, text, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if a WebElement is displayed with a specific text in an element value
     *
     * @param locator          it contains the locator (path) to search an element
     * @param text             to search in element value
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementTextToBePresentInElementValueBy(By locator, String text, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
            logger.info("Element found: " + locator.toString() + " with value: " + text);
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString());
            statusOperation = autoCastingBoolean(executeReflection(locator, text, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if a WebElement is selected or false if it's not
     *
     * @param locator          it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementToBeSelectedBy(By locator, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.elementToBeSelected(locator));
            logger.info("Element already selected: " + locator.toString());
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString());
            statusOperation = autoCastingBoolean(executeReflection(locator, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true and switch if a frame is available or false if it's not
     *
     * @param locator          it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementFrameToBeAvailableAndSwitchToItBy(By locator, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
            logger.info("iFrame found and switch to it: " + locator.toString());
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString());
            statusOperation = autoCastingBoolean(executeReflection(locator, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if a WebElement is displayed with a specific attribute value
     *
     * @param locator          it contains the locator (path) to search an element
     * @param attribute        to handle
     * @param value            to find
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementAttributeToBeBy(By locator, String attribute, String value, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.attributeToBe(locator, attribute, value));
            logger.info("Element found: " + locator.toString() + " with attribute: " + attribute + " and value: " + value);
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString());
            statusOperation = autoCastingBoolean(executeReflection(locator, attribute, value, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if a WebElement is displayed contains an attribute value
     *
     * @param locator          it contains the locator (path) to search an element
     * @param attribute        to handle
     * @param value            to find
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementAttributeContainsBy(By locator, String attribute, String value, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
            logger.info("Element found: " + locator.toString() + " with attribute: " + attribute + " and value: " + value);
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString());
            statusOperation = autoCastingBoolean(executeReflection(locator, attribute, value, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if a WebElement is clickable or false if it's not
     *
     * @param locator          it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementToBeClickableBy(By locator, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            logger.info("Element found: " + locator.toString());
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString());
            statusOperation = autoCastingBoolean(executeReflection(locator, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if a WebElement is visible or false if it's not
     *
     * @param locator          it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementVisibilityOfElementLocatedBy(By locator, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element found: " + locator.toString());
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString());
            statusOperation = autoCastingBoolean(executeReflection(locator, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if a WebElement has a specific selection state true/false
     *
     * @param locator          it contains the locator (path) to search an element
     * @param selectionState   true/false
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementSelectionStateToBeBy(By locator, boolean selectionState, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.elementSelectionStateToBe(locator, selectionState));
            logger.info("Element found: " + locator.toString());
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString());
            statusOperation = autoCastingBoolean(executeReflection(locator, selectionState, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if a WebElement is not visible or false if it is visible
     *
     * @param locator          it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementInvisibilityOfElementLocatedBy(By locator, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            logger.info("Element not found: " + locator.toString());
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString());
            statusOperation = autoCastingBoolean(executeReflection(locator, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if a WebElement is not visible  with a specific text or false if it is visible
     *
     * @param locator          it contains the locator (path) to search an element
     * @param text             to search
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementInvisibilityOfElementWithTextBy(By locator, String text, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
            logger.info("Element not found: " + locator.toString());
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString());
            statusOperation = autoCastingBoolean(executeReflection(locator, text, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if a specific number of elements are displayed or false if it is visible
     *
     * @param locator          it contains the locator (path) to search an element
     * @param numberElements   to search
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForNumberOfElementsToBe(By locator, int numberElements, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.numberOfElementsToBe(locator, numberElements));
            logger.info("Elements found: " + locator.toString() + " number of elements: " + numberElements);
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Elements not found: " + locator.toString() + " with umber of elements: " + numberElements);
            statusOperation = autoCastingBoolean(executeReflection(locator, numberElements, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if the number of elements is less than the expected
     *
     * @param locator          it contains the locator (path) to search an element
     * @param numberElements   to search
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForNumberOfElementsToBeLessThanBy(By locator, int numberElements, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(locator, numberElements));
            logger.info("Elements found: " + locator.toString() + " number of elements less than: " + numberElements);
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Elements not found: " + locator.toString() + " with number of elements less than: " + numberElements);
            statusOperation = autoCastingBoolean(executeReflection(locator, numberElements, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true if the number of elements is more than the expected
     *
     * @param locator          it contains the locator (path) to search an element
     * @param numberElements   to search
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForNumberOfElementsToBeMoreThanBy(By locator, int numberElements, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, numberElements));
            logger.info("Elements found: " + locator.toString() + " number of elements more than: " + numberElements);
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Elements not found: " + locator.toString() + " number of elements more than: " + numberElements);
            statusOperation = autoCastingBoolean(executeReflection(locator, numberElements, timeOutInSeconds));
        }
        return statusOperation;
    }

    /**
     * Return true if the number of elements is more than the expected
     *
     * @param locator          it contains the locator (path) to search an element
     * @param numberElements   to search
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Jonathan Ruano
     */
    protected boolean waitForNumberOfElementsToBeMoreThanByNoAutoCasting(By locator, int numberElements, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, numberElements));
            logger.info("Elements found: " + locator.toString() + " number of elements more than: " + numberElements);
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Elements not found: " + locator.toString() + " number of elements more than: " + numberElements);
        }
        return statusOperation;
    }

    /**
     * Return true if all the elements were found
     *
     * @param locator          it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForPresenceOfAllElementsLocatedBy(By locator, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            logger.info("Elements found: " + locator.toString());
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Elements not found: " + locator.toString());
            statusOperation = autoCastingBoolean(executeReflection(locator, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Return true a specific text is displayed in an element found
     *
     * @param locator          it contains the locator (path) to search an element
     * @param text             to found
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForTextToBePresentInElementLocatedBy(By locator, String text, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            statusOperation = true;
        } catch (Exception e) {
            logger.warn("Elements not found: " + locator.toString());
            statusOperation = autoCastingBoolean(executeReflection(locator, text, timeOutInSeconds));
        }
        return statusOperation;
    }


    /**
     * Method used to click and wait for a clickable WebElement
     *
     * @param locator          contains the By Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void clickElementClickable(By locator, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementToBeClickableBy(locator, timeOutInSeconds)) {
                clickMethodsByLocator(locator);
                logger.info("WebElement clicked");
            } else {
                throw new NoSuchElementException("WebElement not clickable");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement invalid or not clickable");
        }
    }


    /**
     * Method used to click and wait for a visible WebElement
     *
     * @param locator          contains the By Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void clickElementVisible(By locator, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementPresenceBy(locator, timeOutInSeconds)) {
                clickMethodsByLocator(locator);
                logger.info("WebElement clicked");
            } else {
                throw new NoSuchElementException("WebElement not visible");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement invalid or not clickable");
        }
    }


    /**
     * Method used to double click and wait for a visible WebElement
     *
     * @param locator          contains the By Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickElementVisible(By locator, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementPresenceBy(locator, timeOutInSeconds)) {
                doubleClickWebElementByActions(getWebElement(locator));
                logger.info("WebElement clicked");
            } else {
                throw new NoSuchElementException("WebElement not clickable");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement invalid or not clickable");
        }
    }


    /**
     * Method used to double click, move and wait for a visible WebElement
     *
     * @param locator          contains the By Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickAndMoveToElementVisible(By locator, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementPresenceBy(locator, timeOutInSeconds)) {
                doubleClickAndMoveToWebElementByActions(getWebElement(locator));
                logger.info("WebElement clicked");
            } else {
                throw new NoSuchElementException("WebElement not clickable");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement invalid or not clickable");
        }
    }

    /**
     * Method used to double click and wait for a clickable WebElement
     *
     * @param locator          contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickElementClickable(By locator, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementToBeClickableBy(locator, timeOutInSeconds)) {
                doubleClickWebElementByActions(getWebElement(locator));
                logger.info("WebElement clicked");
            } else {
                throw new NoSuchElementException("WebElement not clickable");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement invalid or not clickable");
        }
    }


    /**
     * Method used to double click, move and wait for a clickable WebElement
     *
     * @param locator          contains the By Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickAndMoveToElementClickable(By locator, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementToBeClickableBy(locator, timeOutInSeconds)) {
                doubleClickAndMoveToWebElementByActions(getWebElement(locator));
                logger.info("WebElement clicked");
            } else {
                throw new NoSuchElementException("WebElement not clickable");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement not clickable");
        }
    }


    /**
     * Method used to click, move and wait for a visible WebElement
     *
     * @param locator          contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void clickAndMoveToElementVisible(By locator, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementPresenceBy(locator, timeOutInSeconds)) {
                clickAndMoveMethodsByLocator(locator);
                logger.info("WebElement clicked");
            } else {
                throw new NoSuchElementException("WebElement not found");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement not clickable");
        }
    }


    /**
     * Method used to click, move and wait for a clickable WebElement
     *
     * @param locator          contains the By Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */

    protected void clickAndMoveToElementClickable(By locator, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementToBeClickableBy(locator, timeOutInSeconds)) {
                clickAndMoveMethodsByLocator(locator);
                logger.info("WebElement clicked");

            } else {
                throw new NoSuchElementException("WebElement not clickable");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement not clickable");
        }
    }


    /**
     * Method used to click and wait for a clickable WebElement
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void clickElementClickable(WebElement webElement, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                clickMethodsWebElement(webElement);
                logger.info("WebElement clicked");
            } else {
                logger.error("WebElement not clickable");
                throw new NoSuchElementException("WebElement not found");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement not clickable");
        }
    }


    /**
     * Method used to click and wait for a visible WebElement
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void clickElementVisible(WebElement webElement, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                clickMethodsWebElement(webElement);
                logger.info("WebElement clicked");
            } else {
                throw new NoSuchElementException("WebElement not clickable");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement not clickable");
        }
    }


    /**
     * Method used to double click and wait for a visible WebElement
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickElementVisible(WebElement webElement, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                doubleClickWebElementByActions(webElement);
                logger.info("WebElement clicked");
            } else {
                throw new NoSuchElementException("WebElement not clickable");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement not clickable");
        }
    }


    /**
     * Method used to double click, move and wait for a visible WebElement
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickAndMoveToElementVisible(WebElement webElement, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                doubleClickAndMoveToWebElementByActions(webElement);
                logger.info("WebElement clicked");
            } else {
                throw new NoSuchElementException("WebElement not clickable");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement not clickable");
        }
    }


    /**
     * Method used to double click and wait for a clickable WebElement
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickElementClickable(WebElement webElement, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                doubleClickWebElementByActions(webElement);
                logger.info("WebElement clicked");
            } else {
                throw new NoSuchElementException("WebElement not clickable");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement not clickable");
        }
    }


    /**
     * Method used to double click, move and wait for a clickable WebElement
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickAndMoveToElementClickable(WebElement webElement, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                doubleClickAndMoveToWebElementByActions(webElement);
                logger.info("WebElement clicked");
            } else {
                throw new NoSuchElementException("WebElement not clickable");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement not clickable");
        }
    }


    /**
     * Method used to click, move and wait for a visible WebElement
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void clickAndMoveToElementVisible(WebElement webElement, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                clickAndMoveMethodsWebElement(webElement);
                logger.info("WebElement clicked");
            } else {
                throw new NoSuchElementException("WebElement not clickable");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement not clickable");
        }
    }


    /**
     * Method used to click, move and wait for a clickable WebElement
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    public void clickAndMoveToElementClickable(WebElement webElement, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                clickAndMoveMethodsWebElement(webElement);
                logger.info("WebElement clicked");
            } else {
                throw new NoSuchElementException("WebElement not clickable");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement not clickable");
        }
    }


    /**
     * Method used to click an element and if there is an "ElementClickInterceptedException" it will click again
     *
     * @param webElement contains the Element to do click
     * @return returns true if the click was done successfully
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    protected boolean clickMethod(WebElement webElement) throws Exception {
        boolean statusOperation = false;
        try {
            statusOperation = scrollToElementByCoordinates(webElement);
            if (!statusOperation) {
                statusOperation = scrollMethodToWebElement(webElement);
                if (!statusOperation) {
                    statusOperation = scrollMethodToWebElementByActions(webElement);
                    if (!statusOperation) {
                        logger.info("NONE OF THE METHODS DID WORK TO SCROLL DOWN USING PIXELS");
                    }
                }
            }
            if (waitForElementClickable(webElement, mediumWait()) && statusOperation) {
                scrollMethodToWebElement(webElement);
                webElement.click();
                statusOperation = true;
            } else {
                throw new NoSuchElementException("WebElement not clickable");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement not clickable");
        }
        return statusOperation;
    }

    /**
     * This method will scroll to the Element using the scroll into view at Top of the element With JS
     *
     * @param webElement It contains the WebElement
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    protected boolean scrollMethodToWebElement(WebElement webElement) throws Exception {
        boolean statusOperation = false;
        //Arguments to get into the middle of the WebElement, using as arguments in the java script
        String scrollElementIntoMiddle = "arguments[0].scrollIntoView(true); var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        //===========================================================================
        try {
            ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, webElement);
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElement));
        }
        return statusOperation;
    }

    protected void scrollToElement(WebElement webElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).build().perform();
            logger.info("Scrolled by actions " + getWebElementLocatorPath(webElement));
        } catch (Exception e) {
            if (e.getMessage().contains(Values.TXT_JSEXCACTIONSCLICK)) {
                String scrollElement = "arguments[0].scrollIntoView(true); var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                        + "var elementTop = arguments[0].getBoundingClientRect().top;"
                        + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript(scrollElement, webElement);
                logger.info("Scrolled by javaScript " + getWebElementLocatorPath(webElement));
            } else {
                logger.error("Cannot scroll to webElement: " + getWebElementLocatorPath(webElement));
                executeReflection(webElement);
            }
        }
    }

    protected void scrollToVisibleElement(WebElement webElement, int seconds) throws Exception {
        if (waitForElementVisibility(webElement, seconds)) {
            scrollToElement(webElement);
        } else {
            logger.error("WebElement not visible or invalid");
            throw new NoSuchElementException("WebElement not visible or invalid");
        }
    }

    protected void scrollToClickableElement(WebElement webElement, int seconds) throws Exception {
        if (waitForElementClickable(webElement, seconds)) {
            scrollToElement(webElement);
        } else {
            logger.error("WebElement not visible or invalid");
            throw new NoSuchElementException("WebElement not visible or invalid");
        }
    }

    /**
     * Scroll into the page Up or Down using amount of pixels
     *
     * @param scrollDirection can be Top or Bottom of the page
     * @param pixels          Is an integer that contains the amount of pixels to scroll up or down when "up" or "down" word are use in the "scrollDirection"
     * @throws Exception selenium Exception
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
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(scrollDirection, pixels));
        }

        return statusOperation;
    }

    /**
     * @param webElement contains the Element to get the coordinates X,Y and scroll base on coordinates
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    protected boolean scrollToElementByCoordinates(WebElement webElement) throws Exception {
        boolean statusOperation = false;
        try {
            Point point = webElement.getLocation();
            int x_coordinate = point.getX();
            int y_coordinate = point.getY();
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("window.scrollBy(" + x_coordinate + ", " + y_coordinate + ");");
            statusOperation = waitForElementVisibility(webElement, mediumWait());
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElement));
        }
        return statusOperation;
    }

    /**
     * Scroll to the BOTTOM of the page
     *
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void scrollBottom() throws Exception {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            logger.info("Scrolled to the bottom page");
        } catch (Exception e) {
            autoCasting(executeReflection());
        }
    }


    /**
     * Scroll to the TOP of the page
     *
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void scrollTop() throws Exception {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
            logger.info("Scrolled to the top page");
        } catch (Exception e) {
            logger.warn("Scroll method was not applied");
            autoCasting(executeReflection());
        }
    }


    /**
     * This method is to scroll to a webElement in specific with JS
     *
     * @param webElement to scroll
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void scrollToWebElementJS(WebElement webElement) throws Exception {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
        } catch (Exception e) {
            autoCasting(executeReflection(webElement));
        }
    }


    /**
     * This method is used to move to an element by Action Class
     *
     * @param webElement it contains the WebElement To Move
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    protected boolean scrollMethodToWebElementByActions(WebElement webElement) throws Exception {
        boolean statusOperation = false;
        Actions actions = new Actions(driver);
        try {
            actions.moveToElement(webElement).build().perform();
            statusOperation = waitForElementVisibility(webElement, mediumWait());
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElement));
        }
        return statusOperation;
    }


    /**
     * This method is used to move to a visible element by Action Class
     *
     * @param webElement       contains the WebElement to move
     * @param timeOutInSeconds to wait
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void scrollToWebElementVisibleByAction(WebElement webElement, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                scrollToWebElementByAction(webElement);
            } else {
                throw new NoSuchElementException("WebElement not found");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement not found");
        }
    }


    /**
     * This method is used to move to a clickable element by Action Class
     *
     * @param webElement       contains the WebElement to move
     * @param timeOutInSeconds to wait
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void scrollToWebElementClickableByAction(WebElement webElement, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                scrollToWebElementByAction(webElement);
            } else {
                throw new NoSuchElementException("WebElement not found");
            }
        } catch (Exception e) {
            throw new NoSuchElementException("WebElement not found");
        }
    }


    /**
     * Click to an element with JavaScript
     *
     * @param webElement contains the Element to do click
     * @return returns true if the click was done successfully
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    protected void clickElementJS(WebElement webElement) throws Exception {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click();", webElement);
            logger.info("Web Element selected");
        } catch (Exception e) {
            autoCasting(executeReflection(webElement));
        }
    }


    /**
     * Method used to sendKeys, move and wait for a visible WebElement
     *
     * @param locator          contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysAndMoveToElementVisible(By locator, String text, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementPresenceBy(locator, timeOutInSeconds)) {
                scrollToElement(getWebElement(locator));
                sendKeysWebElement(getWebElement(locator), text);
            } else {
                throw new NoSuchElementException("");
            }
        } catch (Exception e) {
            logger.error("WebElement invalid or unable to send keys");
        }
    }

    protected void sendKeysAndMoveToElement(By locator, String text) throws Exception {
        try {
            scrollToElement(getWebElement(locator));
            sendKeysWebElement(getWebElement(locator), text);
        } catch (Exception e) {
            executeReflection(locator, text);
        }
    }

    protected void sendKeysAndMoveToElement(WebElement webElement, String text) throws Exception {
        try {
            scrollToElement(webElement);
            sendKeysWebElement(webElement, text);
        } catch (Exception e) {
            executeReflection(webElement, text);
        }
    }


    /**
     * Method used to sendKeys, move and wait for a visible WebElement
     *
     * @param locator          contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysAndMoveToElementClickable(By locator, String text, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementToBeClickableBy(locator, timeOutInSeconds)) {
                sendKeysAndMoveToElement(locator, text);
            } else {
                throw new NoSuchElementException("Not element found");
            }
        } catch (Exception e) {
            logger.error("WebElement not found or invalid");
            throw new NoSuchElementException("Not element found");
        }
    }


    /**
     * Method used to sendKeys and wait for a visible WebElement
     *
     * @param locator          contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysElementVisible(By locator, String text, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementPresenceBy(locator, timeOutInSeconds)) {
                sendKeysWebElement(locator, text);
            } else {
                throw new NoSuchElementException("Not element found");
            }
        } catch (Exception e) {
            logger.error("WebElement not found or invalid");
            throw new NoSuchElementException("Not element found");
        }
    }


    /**
     * Method used to sendKeys and wait for a visible WebElement
     *
     * @param locator          contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysElementClickable(By locator, String text, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementToBeClickableBy(locator, timeOutInSeconds)) {
                sendKeysWebElement(locator, text);
            } else {
                throw new NoSuchElementException("Not element found");
            }
        } catch (Exception e) {
            logger.error("WebElement not found or invalid");
            throw new NoSuchElementException("Not element found");
        }
    }


    /**
     * Method used to select a dropdown option by text
     *
     * @param locator          contains the Element to select
     * @param text             to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectDropDownByText(By locator, String text, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementPresenceBy(locator, timeOutInSeconds)) {
                selectDropDownByText(getWebElement(locator), text);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }

        return statusOperation;
    }


    /**
     * Method used to move and select a dropdown option by text
     *
     * @param locator          contains the Element to select
     * @param text             to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectAndMoveDropdownByText(By locator, String text, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementPresenceBy(locator, timeOutInSeconds)) {
                selectAndMoveToDropDownByText(getWebElement(locator), text);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }

        return statusOperation;
    }


    /**
     * Method used to select a dropdown option by index
     *
     * @param locator          contains the Element to select
     * @param index            to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectDropDownByIndex(By locator, int index, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementPresenceBy(locator, timeOutInSeconds)) {
                selectDropDownByIndex(getWebElement(locator), index);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }

        return statusOperation;
    }


    /**
     * Method used to move and select a dropdown option by index
     *
     * @param locator          contains the Element to select
     * @param index            to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectAndMoveDropdownByIndex(By locator, int index, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementPresenceBy(locator, timeOutInSeconds)) {
                selectAndMoveToDropDownByIndex(getWebElement(locator), index);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to select a dropdown option by text
     *
     * @param locator          contains the Element to select
     * @param text             to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void selectDropDownClickableByText(By locator, String text, int timeOutInSeconds) throws Exception {
        if (waitForElementToBeClickableBy(locator, timeOutInSeconds)) {
            selectDropDownByText(getWebElement(locator), text);
        } else {
            logger.info(Values.TXT_EXCREFLECTION);
        }
    }

    /**
     * Method used to move and select a dropdown option by text
     *
     * @param locator          contains the Element to select
     * @param text             to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectAndMoveDropdownClickableByText(By locator, String text, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementToBeClickableBy(locator, timeOutInSeconds)) {
                selectAndMoveToDropDownByText(getWebElement(locator), text);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }

        return statusOperation;
    }

    /**
     * Method used to select a dropdown option by index
     *
     * @param locator          contains the Element to select
     * @param index            to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectDropDownClickableByIndex(By locator, int index, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementToBeClickableBy(locator, timeOutInSeconds)) {
                selectDropDownByIndex(getWebElement(locator), index);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    /**
     * Method used to move and select a dropdown option by index
     *
     * @param locator          contains the Element to select
     * @param index            to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectAndMoveDropdownClickableByIndex(By locator, int index, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementToBeClickableBy(locator, timeOutInSeconds)) {
                selectAndMoveToDropDownByIndex(getWebElement(locator), index);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to select a random dropdown option by index
     *
     * @param locator          contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectDropDownVisibleRandomOption(By locator, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementPresenceBy(locator, timeOutInSeconds)) {
                selectRandomDropDownOption(getWebElement(locator));
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to select a random dropdown option by index
     *
     * @param locator          contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectAndMoveDropDownVisibleRandomOption(By locator, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementPresenceBy(locator, timeOutInSeconds)) {
                scrollMethodToWebElementByActions(getWebElement(locator));
                selectRandomDropDownOption(getWebElement(locator));
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to select a random dropdown option by index
     *
     * @param locator          contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectDropDownClickableRandomOption(By locator, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementToBeClickableBy(locator, timeOutInSeconds)) {
                selectRandomDropDownOption(getWebElement(locator));
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to select a random dropdown option by index
     *
     * @param locator          contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectAndMoveDropDownClickableRandomOption(By locator, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementToBeClickableBy(locator, timeOutInSeconds)) {
                scrollMethodToWebElementByActions(getWebElement(locator));
                selectRandomDropDownOption(getWebElement(locator));
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to return a random webElement from a List<WebElement>
     *
     * @param locatorList
     * @param timeOutInSeconds to wait for visible elements
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected WebElement getRandomWebElementFromList(By locatorList, int timeOutInSeconds) throws Exception {
        WebElement statusOperation = null;
        try {
            if (waitForPresenceOfAllElementsLocatedBy(locatorList, timeOutInSeconds)) {
                Random random = new Random();
                statusOperation = getWebElementList(locatorList).get(random.nextInt(getWebElementList(locatorList).size()));
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to return a random webElement from a List<WebElement> except the first
     *
     * @param webElementList
     * @param timeOutInSeconds to wait for visible elements
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected WebElement getRandomWebElementFromListExceptFirst(List<WebElement> webElementList, int timeOutInSeconds) throws Exception {
        WebElement statusOperation = null;
        try {
            if (waitForElementListVisible(webElementList, timeOutInSeconds)) {
                if (webElementList.size() != 1) {
                    statusOperation = webElementList.get((int) (Math.random() * webElementList.size() - 1) + 1);
                } else {
                    statusOperation = webElementList.get(0);
                }
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }

        return statusOperation;
    }


    /**
     * Method used to return a webElement with specific attribute value from a List<WebElement>
     *
     * @param locatorList
     * @param attribute      to handle
     * @param attributeValue to search
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected WebElement getWebElementByAttributeFromList(By locatorList, String attribute, String attributeValue) throws Exception {
        WebElement statusOperation = null;
        try {
            for (WebElement webElementFromList : getWebElementList(locatorList)) {
                if (webElementFromList.getAttribute(attribute).equalsIgnoreCase(attributeValue)) {
                    statusOperation = webElementFromList;
                    break;
                }
            }
            if (statusOperation != null) {
                return statusOperation;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to validate if a visible webElement is selected or not
     *
     * @param locator
     * @param timeOutInSeconds
     * @return true if a webElement is selected or false if it is not selected
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean isVisibleElementSelected(By locator, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementPresenceBy(locator, timeOutInSeconds)) {
                logger.info("WebElement selected: " + getWebElementLocatorPath(getWebElement(locator)) + ": " + getWebElement(locator).isSelected());
                statusOperation = getWebElement(locator).isSelected();
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    /**
     * Method used to validate if a visible webElement is selected or not
     *
     * @param locator
     * @param timeOutInSeconds
     * @return true if a webElement is clickable or false if it is not selected
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean isClickableElementSelected(By locator, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementToBeClickableBy(locator, timeOutInSeconds)) {
                logger.info("WebElement selected: " + getWebElementLocatorPath(getWebElement(locator)) + ": " + getWebElement(locator).isSelected());
                statusOperation = getWebElement(locator).isSelected();
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to validate if a visible webElement is enabled or not
     *
     * @param locator
     * @param timeOutInSeconds
     * @return true if a webElement is enabled or false if it is not
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean isVisibleElementEnabled(By locator, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementPresenceBy(locator, timeOutInSeconds)) {
                logger.info("WebElement enabled: " + getWebElementLocatorPath(getWebElement(locator)) + ": " + getWebElement(locator).isSelected());
                statusOperation = getWebElement(locator).isEnabled();
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to validate if a clickable webElement is enabled or not
     *
     * @param locator
     * @param timeOutInSeconds
     * @return true if a webElement is enabled or false if it is not
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean isClickableElementEnabled(By locator, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementToBeClickableBy(locator, timeOutInSeconds)) {
                logger.info("WebElement enabled: " + getWebElementLocatorPath(getWebElement(locator)) + ": " + getWebElement(locator).isSelected());
                statusOperation = getWebElement(locator).isEnabled();
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to sendKeys, move and wait for a visible WebElement
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysAndMoveToElementVisible(WebElement webElement, String text, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                sendKeysAndMoveToElement(webElement, text);
            } else {
                throw new NoSuchElementException("The WebElement is not valid");
            }
        } catch (Exception e) {
            logger.error("WebElement not found or invalid");
            throw new NoSuchElementException("The WebElement is not valid");
        }
    }


    /**
     * Method used to sendKeys, move and wait for a visible WebElement
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysAndMoveToElementClickable(WebElement webElement, String text, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                sendKeysAndMoveToElement(webElement, text);
            } else {
                throw new NoSuchElementException("The WebElement is not valid");
            }
        } catch (Exception e) {
            logger.error("WebElement not found or invalid");
            throw new NoSuchElementException("The WebElement is not valid");
        }

    }


    /**
     * Method used to sendKeys and wait for a visible WebElement
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysElementVisibleWithCoordinates(WebElement webElement, String text, int xOffset, int yOffset, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                Actions actions = new Actions(driver);
                actions.moveToElement(webElement, 5, 5).click().sendKeys(text).perform();
            } else {
                throw new NoSuchElementException("The WebElement is not valid");
            }
        } catch (Exception e) {
            logger.error("WebElement not found or invalid");
            logger.info(Values.TXT_EXCREFLECTION);
        }
    }


    /**
     * Method used to sendKeys and wait for a visible WebElement
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysElementVisible(WebElement webElement, String text, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                sendKeysWebElement(webElement, text);
            } else {
                throw new NoSuchElementException("Not element found");
            }
        } catch (Exception e) {
            logger.error("WebElement not found or invalid");
            throw new NoSuchElementException("Not element found");
        }
    }


    /**
     * Method used to sendKeys and wait for a visible WebElement
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysElementClickable(WebElement webElement, String text, int timeOutInSeconds) throws Exception {
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                sendKeysWebElement(webElement, text);
            } else {
                throw new NoSuchElementException("Not element found");
            }
        } catch (Exception e) {
            logger.error("WebElement not found or invalid");
            throw new NoSuchElementException("Not element found");
        }
    }


    /**
     * Method used to select a dropdown option by text
     *
     * @param webElement       contains the Element to select
     * @param text             to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectDropDownByText(WebElement webElement, String text, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                selectDropDownByText(webElement, text);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    /**
     * Method used to move and select a dropdown option by text
     *
     * @param webElement       contains the Element to select
     * @param text             to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectAndMoveDropdownByText(WebElement webElement, String text, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                selectAndMoveToDropDownByText(webElement, text);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    /**
     * Method used to select a dropdown option by index
     *
     * @param webElement       contains the Element to select
     * @param index            to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectDropDownByIndex(WebElement webElement, int index, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                selectDropDownByIndex(webElement, index);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    /**
     * Method used to move and select a dropdown option by index
     *
     * @param webElement       contains the Element to select
     * @param index            to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectAndMoveDropdownByIndex(WebElement webElement, int index, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                selectAndMoveToDropDownByIndex(webElement, index);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    /**
     * Method used to select a dropdown option by text
     *
     * @param webElement       contains the Element to select
     * @param text             to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectDropDownClickableByText(WebElement webElement, String text, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                selectDropDownByText(webElement, text);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    /**
     * Method used to move and select a dropdown option by text
     *
     * @param webElement       contains the Element to select
     * @param text             to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectAndMoveDropdownClickableByText(WebElement webElement, String text, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                selectAndMoveToDropDownByText(webElement, text);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to select a dropdown option by index
     *
     * @param webElement       contains the Element to select
     * @param index            to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectDropDownClickableByIndex(WebElement webElement, int index, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                selectDropDownByIndex(webElement, index);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to move and select a dropdown option by index
     *
     * @param webElement       contains the Element to select
     * @param index            to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectAndMoveDropdownClickableByIndex(WebElement webElement, int index, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                selectAndMoveToDropDownByIndex(webElement, index);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    /**
     * Method used to get a random date with format mm/dd/yyyy
     *
     * @return String with the date format
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected String getRandomDate() throws Exception {
        String statusOperation = "";
        Faker faker = new Faker();
        try {
            Date randomDate = faker.date().birthday(18, 70);
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            statusOperation = simpleDateFormat.format(randomDate);
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    /**
     * Method used to get a random date providing a date format e.g. mm/dd/yyyy
     *
     * @return String with the date format
     * @throws Exception selenium Exception
     * @dateFormat contains the date format
     * @author Jonathan Ruano
     */
    protected String getRandomDate(String dateFormat) throws Exception {
        String statusOperation = "";
        Faker faker = new Faker();
        try {
            Date randomDate = faker.date().birthday(18, 70);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            statusOperation = simpleDateFormat.format(randomDate);
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    /**
     * Return a random number
     *
     * @return a random String number
     * @author Alejandro Hernandez
     */
    protected String getRandomNumber() throws Exception {
        Random random = new Random();
        String statusOperation = "";
        try {
            statusOperation = String.valueOf(random.nextInt(100000));
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }

        return statusOperation;
    }

    /**
     * Method used to select a random dropdown option by index
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectDropDownVisibleRandomOption(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                selectRandomDropDownOption(webElement);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to select a random dropdown option by index
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectAndMoveDropDownVisibleRandomOption(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                scrollMethodToWebElementByActions(webElement);
                selectRandomDropDownOption(webElement);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to select a random dropdown option by index
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectDropDownClickableRandomOption(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                selectRandomDropDownOption(webElement);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to select a random dropdown option by index
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectAndMoveDropDownClickableRandomOption(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                scrollMethodToWebElementByActions(webElement);
                selectRandomDropDownOption(webElement);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to return a random webElement from a List<WebElement>
     *
     * @param webElementList
     * @param timeOutInSeconds to wait for visible elements
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected WebElement getRandomWebElementFromList(List<WebElement> webElementList, int timeOutInSeconds) throws Exception {
        WebElement statusOperation = null;
        try {
            if (waitForElementListVisible(webElementList, timeOutInSeconds)) {
                Random random = new Random();
                statusOperation = webElementList.get(random.nextInt(webElementList.size()));
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to return a webElement with specific attribute value from a List<WebElement>
     *
     * @param webElementList
     * @param attribute      to handle
     * @param attributeValue to search
     * @return a WebElement
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected WebElement getWebElementByAttributeFromList(List<WebElement> webElementList, String attribute, String attributeValue) throws Exception {
        WebElement statusOperation = null;
        try {
            for (WebElement webElementFromList : webElementList) {
                if (webElementFromList.getAttribute(attribute).equalsIgnoreCase(attributeValue)) {
                    statusOperation = webElementFromList;
                    break;
                }
            }
            if (statusOperation != null) {
                return statusOperation;
            }
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElementList, attribute, attributeValue));
        }
        return statusOperation;
    }

    /**
     * Method used to validate if a visible webElement is selected or not
     *
     * @param webElement
     * @param timeOutInSeconds
     * @return true if a webElement is selected or false if it is not selected
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean isVisibleElementSelected(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                logger.info("WebElement selected: " + getWebElementLocatorPath(webElement) + ": " + webElement.isSelected());
                statusOperation = webElement.isSelected();
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to validate if a visible webElement is selected or not
     *
     * @param webElement
     * @param timeOutInSeconds
     * @return true if a webElement is clickable or false if it is not selected
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean isClickableElementSelected(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                logger.info("WebElement selected: " + getWebElementLocatorPath(webElement) + ": " + webElement.isSelected());
                statusOperation = webElement.isSelected();
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to validate if a visible webElement is enabled or not
     *
     * @param webElement
     * @param timeOutInSeconds
     * @return true if a webElement is enabled or false if it is not
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean isVisibleElementEnabled(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                logger.info("WebElement enabled: " + getWebElementLocatorPath(webElement) + ": " + webElement.isSelected());
                statusOperation = webElement.isEnabled();
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to validate if a clickable webElement is enabled or not
     *
     * @param webElement
     * @param timeOutInSeconds
     * @return true if a webElement is enabled or false if it is not
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean isClickableElementEnabled(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                logger.info("WebElement enabled: " + getWebElementLocatorPath(webElement) + ": " + webElement.isSelected());
                statusOperation = webElement.isEnabled();
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to switch in a frame By locator, Index or Name
     *
     * @param frame            WebElement, int or String
     * @param timeOutInSeconds wait only if the WebElement option is used.
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected <f> boolean switchToFrameByWebElementIndexOrName(f frame, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            String frameType = frame.getClass().getName();
            if (frameType.contains("Integer")) {
                driver.switchTo().frame((Integer) frame);
                statusOperation = true;
            } else {
                if (frameType.contains("String")) {
                    driver.switchTo().frame(frame.toString());
                    statusOperation = true;
                } else {
                    if (waitForElementVisibility((WebElement) frame, timeOutInSeconds)) {
                        driver.switchTo().frame((WebElement) frame);
                        statusOperation = true;
                    } else {
                        logger.error("The Web Element was not found");
                        throw new NoSuchElementException("Element not valid");
                    }
                }
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    /**
     * Method used to switch to the parent frame
     *
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean switchToParentFrame() throws Exception {
        boolean statusOperation = false;
        try {
            driver.switchTo().parentFrame();
            logger.info("Switch to parent frame");
            statusOperation = true;
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection());
        }
        return statusOperation;
    }


    /**
     * Method used to accept an alert
     *
     * @param timeOutInSeconds wait for an alter to be visible
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean switchToAlertAccept(int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForAlertVisible(timeOutInSeconds)) {
                driver.switchTo().alert().accept();
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to dismiss an alert
     *
     * @param timeOutInSeconds wait for an alter to be visible
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean switchToAlertDismiss(int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForAlertVisible(timeOutInSeconds)) {
                driver.switchTo().alert().dismiss();
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to get text from an alert
     *
     * @param timeOutInSeconds wait for an alter to be visible
     * @return String
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected String switchToAlertGetText(int timeOutInSeconds) throws Exception {
        String statusOperation = "";
        try {
            if (waitForAlertVisible(timeOutInSeconds)) {
                statusOperation = driver.switchTo().alert().getText();
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to accept an alert
     *
     * @param text             to be send in the alert
     * @param timeOutInSeconds wait for an alter to be visible
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean switchToAlertSendKeys(String text, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForAlertVisible(timeOutInSeconds)) {
                driver.switchTo().alert().sendKeys(text);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method used to switch to a window tab by index
     *
     * @param tabIndex tab number to switch
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean switchToWindow(int tabIndex) throws Exception {
        boolean statusOperation = false;
        try {
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabIndex));
            logger.info("Change to tab: " + tabIndex);
            statusOperation = true;
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(tabIndex));
        }
        return statusOperation;
    }


    /**
     * Method used to get the number of windows open
     *
     * @return int
     * @author Alejandro Hernandez
     */
    protected int getOpenTabsSize() throws Exception {
        int statusOperation = 0;
        try {
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            statusOperation = tabs.size();
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection());
        }
        return statusOperation;
    }

    /**
     * Method used to close the current window
     *
     * @author Alejandro Hernandez
     */
    protected void closeWindow() throws Exception {
        try {
            driver.close();
        } catch (Exception e) {
            executeReflection();
        }

    }

    /**
     * This method is used to SendKeys to a WebElement by Action
     *
     * @param webElement
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean sendKeysWebElementByActions(WebElement webElement, String text) throws Exception {
        boolean statusOperation = false;
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(webElement, text).build().perform();
            logger.info("Element found: " + getWebElementLocatorPath(webElement));
            logger.info("Keys sent: " + text);
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElement, text));
        }
        return statusOperation;
    }

    /**
     * This method is used to SendKeys to a WebElement
     *
     * @param webElement
     * @return
     * @author Alejandro Hernandez
     */
    protected void sendKeysWebElement(WebElement webElement, String text) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(webElement, text).build().perform();
            logger.info("Keys sent by actions " + getWebElementLocatorPath(webElement) + " with text: " + text);
        } catch (Exception e) {
            try {
                webElement.sendKeys(text);
                logger.info("Keys sent " + getWebElementLocatorPath(webElement) + " with text: " + text);
            } catch (Exception x) {
                try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;

                    js.executeScript("arguments[0].value='" + text + "';", webElement);
                    logger.info("Keys sent by JS " + getWebElementText(webElement) + " with text: " + text);
                } catch (Exception y) {
                    logger.error("WebElement not valid: " + getWebElementLocatorPath(webElement));
                }
            }
        }
    }

    /**
     * This method is used to SendKeys to a WebElement
     *
     * @param webElement
     * @return
     * @author J.Ruano
     */
    protected void sendKeysWebElementJS(WebElement webElement, String text) throws Exception {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value='" + text + "';", webElement);
                logger.info("Keys sent by JS " + getWebElementText(webElement) + " with text: " + text);
            } catch (Exception y) {
                logger.error("WebElement not valid: " + getWebElementLocatorPath(webElement));
            }
    }

    /**
     * This method is used to SendKeys to a WebElement
     *
     * @param locator
     * @return
     * @author Alejandro Hernandez
     */
    protected void sendKeysWebElement(By locator, String text) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(getWebElement(locator), text).build().perform();
            logger.info("Keys sent by actions " + locator.toString() + " with text: " + text);
        } catch (Exception e) {
            try {
                getWebElement(locator).sendKeys(text);
                logger.info("Keys sent " + locator.toString() + " with text: " + text);
            } catch (Exception x) {
                try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].value='" + text + "';", getWebElement(locator));
                    logger.info("Keys sent by JS " + locator.toString() + " with text: " + text);
                } catch (Exception y) {
                    logger.error("WebElement not valid: " + locator.toString());
                }
            }
        }
    }

    /**
     * This method is used to SendKeys without specify a WebElement
     *
     * @param text
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean sendKeysByActions(String text) throws Exception {
        boolean statusOperation = false;
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(text).build().perform();
            logger.info("Keys sent: " + text);
            statusOperation = true;
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(text));
        }
        return statusOperation;
    }


    /**
     * This method is used to move and SendKeys to a WebElement by Action
     *
     * @param webElement
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean sendKeysAndMoveToWebElementByActions(WebElement webElement, String text) throws Exception {
        boolean statusOperation = false;
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).sendKeys(webElement, text).build().perform();
            logger.info("Element found: " + getWebElementLocatorPath(webElement));
            logger.info("Keys sent: " + text);
            statusOperation = true;
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElement, text));
        }
        return statusOperation;
    }

    /**
     * This method is used to move and click a WebElement by Action Class
     *
     * @param webElement
     * @return
     * @author Alejandro Hernandez
     */
    private void clickAndMoveToWebElementByActions(WebElement webElement) throws Exception { //NO BORRAR
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).click(webElement).build().perform();
            logger.info("Element found: " + getWebElementLocatorPath(webElement));
        } catch (Exception e) {
            executeReflection(webElement);
        }
    }


    /**
     * This method is used to click a WebElement by Action Class
     *
     * @param webElement
     * @return
     * @author Alejandro Hernandez
     */
    private void clickWebElementByActions(WebElement webElement) throws Exception {//NO BORRAR
        try {
            Actions actions = new Actions(driver);
            actions.click(webElement).build().perform();
            logger.info("WebElement clicked: " + getWebElementLocatorPath(webElement));
        } catch (Exception e) {
            executeReflection(webElement);
        }
    }

    /**
     * This method is used to move and double click a WebElement by Action Class
     *
     * @param webElement
     * @return
     * @author Alejandro Hernandez
     */
    private void doubleClickAndMoveToWebElementByActions(WebElement webElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).doubleClick(webElement).build().perform();
            logger.info("Element clicked: " + getWebElementLocatorPath(webElement));
        } catch (Exception e) {
            logger.info("Element not clicked: " + getWebElementLocatorPath(webElement));
            executeReflection(webElement);
        }
    }


    /**
     * This method is used to double click a WebElement by Action Class
     *
     * @param webElement
     * @return
     * @author Alejandro Hernandez
     */
    private void doubleClickWebElementByActions(WebElement webElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.doubleClick(webElement).build().perform();
            logger.info("Element clicked: " + getWebElementLocatorPath(webElement));
        } catch (Exception e) {
            logger.info("Element not clicked: " + getWebElementLocatorPath(webElement));
            executeReflection(webElement);
        }
    }


    /**
     * Return the WebElement locator string
     *
     * @param webElement
     * @return WebElement locator string.
     * @throws null if the webElement is empty, null or the string has a different format.
     * @author Alejandro Hernandez
     */
    protected String getWebElementLocatorPath(WebElement webElement) throws Exception {
        try {
            return webElement.toString().split("-> ")[1].replace("]", "");
        } catch (Exception e) {
            return webElement.toString().split("DefaultElementLocator")[1].replace("'", "");
        }
    }

    /**
     * Return the WebElement locator string
     *
     * @param webElement
     * @return WebElement locator string.
     * @author Alejandro Hernandez
     */
    protected String getWebElementLocatorPath(List<WebElement> webElement) throws Exception {
        try {
            return webElement.toString().split("-> ")[1].replace("]", "");
        } catch (Exception e) {
            return webElement.toString().split("DefaultElementLocator")[1].replace("'", "");
        }
    }

    /**
     * This method is used to move to a visible element by Action Class
     *
     * @param webElement contains the WebElement to move
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    private void scrollToWebElementByAction(WebElement webElement) throws Exception {
        Actions actions = new Actions(driver);
        try {
            actions.moveToElement(webElement).build().perform();
            logger.info("Scrolling to webElement: " + getWebElementLocatorPath(webElement));
        } catch (Exception e) {
            executeReflection(webElement);
        }
    }

    /**
     * This method is used to move and select a dropdown option by text
     *
     * @param webElement
     * @param text       to select from dropdown
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected void selectAndMoveToDropDownByText(WebElement webElement, String text) throws Exception {
        try {
            scrollToWebElementByAction(webElement);
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Selected option: " + text);
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
    }

    /**
     * This method is used to move and select a dropdown option by index
     *
     * @param webElement
     * @param index      to select from dropdown
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectAndMoveToDropDownByIndex(WebElement webElement, int index) throws Exception {
        boolean statusOperation = false;
        try {
            scrollToWebElementByAction(webElement);
            Select select = new Select(webElement);
            select.selectByIndex(index);
            logger.info("Selected option: " + index);
            statusOperation = true;
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * This method is used to move and select a dropdown option by text
     *
     * @param webElement
     * @param text       to select from dropdown
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectDropDownByText(WebElement webElement, String text) throws Exception {
        boolean statusOperation = false;
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Selected option: " + text);
            statusOperation = true;
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElement, text));
        }
        return statusOperation;
    }


    /**
     * This method is used to select a dropdown option by index
     *
     * @param webElement
     * @param index      to select from index
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectDropDownByIndex(WebElement webElement, int index) throws Exception {
        boolean statusOperation = false;
        try {
            Select select = new Select(webElement);
            select.selectByIndex(index);
            logger.info("Selected option: " + index);
            statusOperation = true;
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElement, index));
        }

        return statusOperation;
    }


    /**
     * This method is used to select a random dropdown option by index
     *
     * @param webElement
     * @return
     * @throws Exception selenium Exception
     * @author Alejandro Hernandez
     */
    protected boolean selectRandomDropDownOption(WebElement webElement) throws Exception {
        boolean statusOperation = false;
        try {
            Random random = new Random();
            Select select = new Select(webElement);
            int optionIndex = random.nextInt(select.getOptions().size() - 1);
            select.selectByIndex(optionIndex++);
            logger.info("Selected option: " + optionIndex++);
            statusOperation = true;
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElement));
        }
        return statusOperation;
    }


    /**
     * This method is used to move and select a dropdown option by text
     *
     * @return
     * @author J.Ruano
     */
    protected boolean switchToDefaultContentFrame() throws Exception {
        boolean statusOperation = false;
        try {
            driver.switchTo().defaultContent();
            logger.info("Change to default content");
            statusOperation = true;
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection());
        }
        return statusOperation;
    }


    /**
     * This method is used to switch to a subtab by name
     *
     * @param name
     * @param timeOutInSeconds
     * @return
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    public boolean switchSubTabByNameSF(String name, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            By pathForSubTabs = By.xpath("//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]");
            if (waitForPresenceOfAllElementsLocatedBy(pathForSubTabs, timeOutInSeconds)) {
                for (WebElement tab : getWebElementList(pathForSubTabs)) {
                    if (getWebElementAttribute(tab, "title").trim().equalsIgnoreCase(name.trim())) {
                        clickAndMoveToElementClickable(tab, mediumWait());
                        logger.info("Switch to sub-tab");
                        statusOperation = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * This method is used to switch to a subtab by index
     *
     * @param index            contains the number of the tab to switch
     * @param timeOutInSeconds is the time to wait until the operation is done
     * @return
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    public void switchSubTabByIndexSF(int index, int timeOutInSeconds) throws Exception {
        try {
            By pathForSubTabs = By.xpath("//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]");
            if (waitForPresenceOfAllElementsLocatedBy(pathForSubTabs, timeOutInSeconds)) {
                List<WebElement> subTabsList = getWebElementList(pathForSubTabs);
                clickAndMoveToElementClickable(subTabsList.get(index), mediumWait());
                logger.info("Switch to sub-tab");
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
            executeReflection(index, timeOutInSeconds);
        }
    }


    /**
     * method to close the subtab open at Salesforce
     *
     * @param index            contains the number of the subtab to close
     * @param timeOutInSeconds is the time to wait until the operation is done
     * @return
     * @throws Exception selenium Exception
     */
    protected boolean closeSubTabByIndexSF(int index, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            By pathForSubTabs = By.xpath("//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]//*[starts-with(@class,'close')]");
            if (waitForPresenceOfAllElementsLocatedBy(pathForSubTabs, timeOutInSeconds)) {
                List<WebElement> subTabsList = getWebElementList(pathForSubTabs);
                clickAndMoveToElementClickable(subTabsList.get(index), mediumWait());
                logger.info("Switch to sub-tab");
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * method to close the subtabs open at Salesforce
     *
     * @param timeOutInSeconds is the time to wait until the operation is done
     * @return
     * @throws Exception selenium Exception
     */
    public boolean closeLastSubTabSF(int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            By subTabs = By.xpath("//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]//*[starts-with(@class,'close')]");
            By lastSubTab = By.xpath("(//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]//*[starts-with(@class,'close')]//button)[last()]");
            if (waitForPresenceOfAllElementsLocatedBy(subTabs, timeOutInSeconds)) {
                waitForPresenceOfAllElementsLocatedBy(subTabs, timeOutInSeconds);
                clickAndMoveToElementClickable(getWebElement(lastSubTab), timeOutInSeconds);
                logger.info("sub-tab " + lastSubTab + " clicked");
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    public void closeLastTabSF(int waitTime) throws Exception {
        try {
            By tabs = By.xpath("(//div[contains(@class,'secondary')]//div[starts-with(@class,'close')]//button)[last()]");
            if (waitForPresenceOfAllElementsLocatedBy(tabs, waitTime)) {
                clickAndMoveToElementClickable(getWebElement(tabs), waitTime);
                logger.info("Tab " + tabs + " clicked");
            } else {
                logger.warn("The tab is not visible");
            }
        } catch (Exception e) {
            logger.error("The tab was not found");
            throw new NoSuchElementException("The tab was not found");
        }
    }

    /**
     * Method used to select a random dropdown excluding 'None'
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    protected boolean selectDropDownRandomOptionNone(WebElement webElement, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementClickable(webElement, timeOutInSeconds)) {
                selectRandomDropDownNotNone(webElement);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * This method is used to select a random dropdown option by index excluding the --None-- Option
     *
     * @param webElement contains the Element to select
     * @return
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    protected boolean selectRandomDropDownNotNone(WebElement webElement) throws Exception {
        boolean statusOperation = false;
        int optionIndex = 0;
        Random random = new Random();
        Select select = new Select(webElement);
        try {
            do {
                optionIndex = random.nextInt(select.getOptions().size() - 1);
            } while (optionIndex == 0);
            select.selectByIndex(optionIndex++);
            logger.info("Selected option: " + optionIndex++);
            statusOperation = true;
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElement));
        }
        return statusOperation;
    }


    /**
     * This method is used to return visible elements from a list
     *
     * @param webElementList contains the list of webElements used to filter by visible elements
     * @return a list of all the visible webElements
     * @author J.Ruano
     */
    protected List<WebElement> getVisibleElements(List<WebElement> webElementList) throws Exception {
        List<WebElement> statusOperation = new LinkedList<>();
        try {
            for (WebElement webElement : webElementList) {
                if (webElement.isDisplayed()) {
                    statusOperation.add(webElement);
                }
            }
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElementList));
        }
        return statusOperation;
    }


    /**
     * This method is used to get text from a WebElement
     *
     * @param webElement contains the webElement used to get the text value
     * @return it returns the webElement text value
     * @author Alejandro Hernandez
     */
    protected String getWebElementText(WebElement webElement) throws Exception {
        String statusOperation = "";
        try {
            statusOperation = webElement.getText();
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElement));
        }
        return statusOperation;
    }


    /**
     * This method is used to get an attribute value from a WebElement
     *
     * @param webElement contains the webElement used to get the attribute value
     * @param attribute  it contains the attribute name used to get the value i.e. id, data-label, name etc...
     * @return the value of the specific attribute for the webElement
     * @author Alejandro Hernandez
     */
    protected String getWebElementAttribute(WebElement webElement, String attribute) throws Exception {
        String statusOperation = "";
        try {
            statusOperation = webElement.getAttribute(attribute);
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElement, attribute));
        }
        return statusOperation;
    }

    /**
     * Used to split some string given the string to split and the regex rule
     *
     * @param textToSplit it contains the string to be split
     * @param regexRule   it contains the regular expresion used to split the String
     * @return a list with the split text
     * @author J.Ruano
     */
    public List<String> splitRegex(String textToSplit, String regexRule) throws Exception {
        List<String> statusOperation = new ArrayList<>();
        try {
            statusOperation = Arrays.asList(textToSplit.split(regexRule));
            textToSplit.split(regexRule);
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(textToSplit, regexRule));
        }
        return statusOperation;
    }

    /**
     * Method to move up or down using Actions, usedul for dropdowns that does not contains Select>option
     *
     * @param webElement     it contains the webelement or dropdown where the actions will be perform
     * @param moveDirection  where the actions will be apply Up or Down
     * @param ammountOfMoves the amount of action Up Or Down will be perform
     * @return
     * @author J.Ruano
     */
    protected boolean moveDownUpAction(WebElement webElement, String moveDirection, int ammountOfMoves) throws
            Exception {
        boolean statusOperation = false;
        Actions actions = new Actions(driver);
        int counter = 0;
        boolean notNone = false;
        WebElement optionID = null;
        WebElement dropdownVisible = null;
        String valueOptionID = "";
        try {
            do {
                actions.sendKeys(Keys.ARROW_DOWN).build().perform();
                By activeDes = By.xpath("//input[contains(@aria-activedescendant,'input')]");
                optionID = getWebElement(activeDes);
                valueOptionID = optionID.getAttribute("aria-activedescendant");
                if (valueOptionID.contains("-0-")) {
                    notNone = true;
                } else {
                    notNone = false;
                }
                counter++;
            } while (counter <= ammountOfMoves || notNone == true);
            actions.sendKeys(Keys.ENTER).build().perform();
            statusOperation = true;
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElement, moveDirection, ammountOfMoves));
        }
        return statusOperation;
    }


    /**
     * Method used to click and wait for a visible WebElement
     *
     * @param webElement       contains the Element to select
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    protected boolean clickWhileCondition(WebElement webElement, String attribute, String attributeValue,
                                          int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                do {
                    clickElementClickable(webElement, mediumWait());
                } while (webElement.getAttribute(attribute).trim().equalsIgnoreCase(attributeValue.trim()));
                logger.info("WebElement clicked");
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method to generate a TimeStamp returning a timestamp with an specific Format
     *
     * @param strFormat format required to return the timestamp
     * @return a string value
     * @author J.Ruano
     */
    public String generateTimeStamp(String strFormat) throws Exception {
        String statusOperation = "";
        try {
            statusOperation = new SimpleDateFormat(strFormat).format(new Date()).replace(".", "");
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(strFormat));
        }
        return statusOperation;
    }

    /**
     * Used to move and click to a element from a list that contains certain value in an attribute
     *
     * @param webElementList it contains the list with all the elements
     * @param attribute      it contains the attribute used to get the value
     * @param attributeValue it contains the value for the attribute
     * @return The webelement that matched with the attributeValue
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    protected WebElement clickAndMoveToElementClickableFromListByAttribute
    (List<WebElement> webElementList, String attribute, String attributeValue) throws Exception {
        boolean webElementFound = false;
        WebElement statusOperation = null;
        try {
            for (WebElement webElement : webElementList) {
                if (webElement.getAttribute(attribute).trim().equalsIgnoreCase(attributeValue.trim())) {
                    clickAndMoveMethodsWebElement(webElement);
                    statusOperation = webElement;
                    logger.info("WebElement clicked");
                    webElementFound = true;
                    break;
                }
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Used to move and click to a element from a list that contains certain value in an attribute
     *
     * @param webElementList it contains the list with all the elements
     * @param textValue      contains the value used to get the correct element
     * @return The element that matched with the textValue
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    protected WebElement clickAndMoveToElementClickableFromListByText(List<WebElement> webElementList, String
            textValue) throws Exception {
        WebElement statusOperation = null;
        WebElement webElementBKP = null;
        try {
            for (WebElement webElement : webElementList) {
                webElementBKP = webElement;
                if (webElement.getText().trim().equalsIgnoreCase(textValue.trim())) {
                    webElement.click();
                    statusOperation = webElement;
                    logger.info("WebElement clicked");
                    break;
                }
            }
        } catch (Exception e) {
            clickAndMoveMethodsWebElement(webElementBKP);
            statusOperation = webElementBKP;
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    /**
     * Method used to return a random webElement from a List<WebElement> ignoring a certain value (Index value) i.e. ignoring the option in the index 1
     *
     * @param webElementList it contains a list of webElements used to search a webElement randomly ignoring certain value (int index)
     * @param ignoreValue    int value of the webElement list to be ignore
     * @return the value selected randomly excluding the option ignoring by the ignoreValue
     * @throws IllegalAccessException
     * @author J.Ruano
     */
    protected WebElement getRandomWebElementIgnoreIdexValue(List<WebElement> webElementList, int ignoreValue) throws
            Exception {
        WebElement statusOperation = null;
        int randomNumber = 0;
        try {
            Random random = new Random();
            do {
                randomNumber = random.nextInt(webElementList.size());
            } while (randomNumber == ignoreValue);
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElementList, ignoreValue));
        }
        statusOperation = webElementList.get(randomNumber);
        return statusOperation;
    }


    /**
     * @param webElementList it contains a list of webElements used to search a webElement randomly ignoring certain value (text Value)
     * @param searchValue    string value of the webElement list to be ignore
     * @return the value selected randomly excluding the option ignoring by the searchValue
     * @throws IllegalAccessException
     * @author J.Ruano
     */
    protected WebElement getRandomWebElementIgnoreText(List<WebElement> webElementList, String searchValue) throws
            Exception {
        WebElement statusOperation = null;
        int randomNumber = 0;
        Random random = new Random();
        try {
            do {
                randomNumber = random.nextInt(webElementList.size());
            } while (webElementList.get(randomNumber).getText().equalsIgnoreCase(searchValue));
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElementList, searchValue));
        }
        statusOperation = webElementList.get(randomNumber);
        return statusOperation;
    }

    /**
     * @param webElementList it contains a list of webElements used to search a webElement randomly ignoring certain value (text Value)
     * @param attributeValue it contains the attribute used to get the value
     * @param searchValue    string value of the webElement list to be ignore
     * @return the value selected randomly excluding the option ignoring by the attributeValue and searchValue
     * @throws IllegalAccessException
     * @author J.Ruano
     */
    protected WebElement getRandomWebElementIgnoreAttribute(List<WebElement> webElementList, String
            attributeValue, String searchValue) throws Exception {
        WebElement statusOperation = null;
        int randomNumber = 0;
        Random random = new Random();
        try {
            do {
                randomNumber = random.nextInt(webElementList.size());
            } while (webElementList.get(randomNumber).getAttribute(attributeValue.trim()).equalsIgnoreCase(searchValue));
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElementList, searchValue));
        }
        statusOperation = webElementList.get(randomNumber);
        return statusOperation;
    }

    /**
     * Method used to move and select a dropdown option by text
     *
     * @param webElement       contains the Element to select
     * @param value            to select from a dropdown
     * @param timeOutInSeconds time to wait for a WebElement
     * @return
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    protected boolean selectAndMoveDropdownByOptionAttributeValue(WebElement webElement, String value,
                                                                  int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementVisibility(webElement, timeOutInSeconds)) {
                selectAndMoveToDropDownByOptionValue(webElement, value);
                statusOperation = true;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * This method is used to move and select a dropdown option by text
     *
     * @param webElement contains the Element to select
     * @param value      to select from a dropdown
     * @return
     * @author J.Ruano
     */
    protected boolean selectAndMoveToDropDownByOptionValue(WebElement webElement, String value) throws Exception {
        boolean statusOperation = false;
        try {
            scrollToWebElementByAction(webElement);
            Select select = new Select(webElement);
            List<WebElement> optionList = select.getOptions();
            for (WebElement option : optionList) {
                if (option.getAttribute("value").trim().equalsIgnoreCase(value)) {
                    select.selectByVisibleText(option.getText());
                    break;
                }
            }
            logger.info("Selected option: " + value);
            statusOperation = true;
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Use to generate a random number between a min and max value
     *
     * @param min contains the min value that can contains the randomNumber
     * @param max contains the max value that can contains the randomNumber
     * @return an integer value selected between the range min - max
     * @author J.Ruano
     */
    public int getRandomNumberByLimits(int min, int max) throws Exception {
        int statusOperation = 0;
        try {
            if (min == max) {
                min = min - 1;
            }
            statusOperation = (int) Math.floor(Math.random() * (max - min) + min);
        } catch (Exception e) {
        }
        return statusOperation;
    }

    /**
     * Used to retry to find an element and avoid the "StaleElementReferenceException"
     *
     * @param locator          contains the locator that will be used to search the element
     * @param amountOfAttempts it contains a int value with the amount of tries
     * @return it returns a boolean value of the result of the operation
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    public boolean retryingFindElementByLocator(By locator, int amountOfAttempts) throws Exception {
        int attemptsCounter = 0;
        boolean statusOperation = false;
        try {
            while (attemptsCounter < amountOfAttempts) {
                try {
                    getWebElement(locator);
                    statusOperation = true;
                    break;
                } catch (StaleElementReferenceException e) {
                }
                attemptsCounter++;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    /**
     * Use to assign the short wait time from the GlobalConfig.properties
     *
     * @return an integer value
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    public int shortWait() throws Exception {
        fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);
        int timeOutInSeconds = 0;
        try {
            timeOutInSeconds = Integer.parseInt(fileReading.getField("shortWaitTime"));
            logger.info(timeOutInSeconds + " Seconds Will Be Wait For shortWaitTime");

        } catch (NumberFormatException e) {
            logger.warn("There is no valid value for shortWaitTime at GlobalConfig.properties File");
        }
        return timeOutInSeconds;
    }

    /**
     * Use to assign the medium wait time from the GlobalConfig.properties
     *
     * @return an integer value
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    public int mediumWait() throws Exception {
        fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);
        int timeOutInSeconds = 0;
        try {
            timeOutInSeconds = Integer.parseInt(fileReading.getField("mediumWaitTime"));
            logger.info(timeOutInSeconds + " Seconds Will Be Wait For mediumWaitTime");

        } catch (NumberFormatException e) {
            logger.warn("There is no valid value for mediumWaitTime at GlobalConfig.properties File");
        }
        return timeOutInSeconds;
    }


    /**
     * Use to assign the long wait time from the GlobalConfig.properties
     *
     * @return an integer value
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    public int longWait() throws Exception {
        fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);
        int timeOutInSeconds = 0;
        try {
            timeOutInSeconds = Integer.parseInt(fileReading.getField("longWaitTime"));
            logger.info(timeOutInSeconds + " Seconds Will Be Wait For longWaitTime");

        } catch (NumberFormatException e) {
            logger.warn("There is no valid value for longWaitTime at GlobalConfig.properties File");
        }
        return timeOutInSeconds;
    }

    /**
     * Use to assign the the Wait Timeout in Seconds for the driver from the GlobalConfig.properties
     *
     * @return an integer value
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    public int waitDriverTimeOutInSeconds() throws Exception {
        fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);
        int timeOutInSeconds = 0;
        try {
            timeOutInSeconds = Integer.parseInt(fileReading.getField("waitDriverTimeOutInSeconds"));
            logger.info(timeOutInSeconds + " Seconds Will Be Wait For waitDriverTimeOutInSeconds");

        } catch (NumberFormatException e) {
            logger.warn("There is no valid value for waitDriverTimeOutInSeconds at GlobalConfig.properties File");
        }
        return timeOutInSeconds;
    }

    /**
     * Use to auto search and switch between iframes to find an element by locator
     *
     * @param locator contains the locator to search the WebElement
     * @return a boolean value of the statu of the operation
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    public boolean autoSwitchIframeByLocator(By locator) throws Exception {
        boolean statusOperation = false;
        int size = getWebElementList(By.tagName("iframe")).size();
        List<WebElement> elementsList = new ArrayList<>();
        try {
            for (int i = 0; i <= size; i++) {
                driver.switchTo().frame(i);
                elementsList = getWebElementList(locator);
                if (!elementsList.isEmpty()) {
                    logger.info("Element Found Switching To Iframe: " + i);
                    statusOperation = true;
                    break;
                } else {
                    driver.switchTo().defaultContent();
                }
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Use to auto search and switch between iframes to find an element By locator
     *
     * @param webElementFound  contains the WebElement that we want found at page
     * @param timeOutInSeconds the wait time to give at search
     * @return a boolean value of the status of the operation
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    public boolean autoSwitchIframeByWebElement(WebElement webElementFound, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        boolean foundIframeFlag = false;
        int size = 0;
        try {
            By frame = By.tagName("iframe");
            if (!waitForNumberOfElementsToBeMoreThanByNoAutoCasting(frame, 0, timeOutInSeconds)) {
                for (int i = 0; i <= iframeTries(); i++) {
                    foundIframeFlag = waitForNumberOfElementsToBeMoreThanByNoAutoCasting(frame, 0, timeOutInSeconds);
                    if (foundIframeFlag) {
                        break;
                    }
                }
            }
            size = getWebElementList(By.tagName("iframe")).size();
            for (int i = 0; i <= size - 1; i++) {
                statusOperation = switchingIframeUntilElementFound(webElementFound, i, timeOutInSeconds);
                if (statusOperation) {
                    logger.info("Element Found Switching To Iframe: " + i);
                    break;
                }
            }
        } catch (Exception e) {
            logger.info("Exception: " + e);
        }
        return statusOperation;
    }


    /**
     * Method used by autoSwitchIframeByWebElement to handle the loop when exception occurs so th eoperation can continue until it gets
     * the correct iframe where the element is located
     *
     * @param webElementFound  contains the WebElement that we want found at page
     * @param counter          counter used to switch between iframes
     * @param timeOutInSeconds the wait time to give at search
     * @return a boolean value of the status of the operation
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    public boolean switchingIframeUntilElementFound(WebElement webElementFound, int counter, int timeOutInSeconds) throws
            Exception {
        boolean statusOperation = false;
        try {
            driver.switchTo().defaultContent();
            int size = getWebElementList(By.tagName("iframe")).size();
            if (size > 0) {
                for (int i = counter; i <= (size - 1); i++) {
                    driver.switchTo().defaultContent();
                    driver.switchTo().frame(i);
                    if (waitForElementVisibilityNoReflection(webElementFound, timeOutInSeconds) || waitForElementClickableNoReflection(webElementFound, timeOutInSeconds) || webElementFound.isDisplayed() || webElementFound.isEnabled()) {
                        logger.info("Element Found Switching To Iframe: " + i);
                        statusOperation = true;
                        break;
                    } else {
                        driver.switchTo().defaultContent();
                    }
                }
            } else {
                if (waitForElementVisibilityNoReflection(webElementFound, timeOutInSeconds) || waitForElementClickableNoReflection(webElementFound, timeOutInSeconds) || webElementFound.isDisplayed() || webElementFound.isEnabled()) {
                    logger.info("There are NOT IFRAMES AVAILABLE But The Element Was Found");
                    statusOperation = true;
                }
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    /**
     * Use to assign the amount of tries to find an Iframe it is setup from the GlobalConfig.properties
     *
     * @return an integer value
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    public int iframeTries() throws Exception {
        int statusOperation = 0;
        fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);
        try {
            statusOperation = Integer.parseInt(fileReading.getField("iFrameTries"));
            logger.info(statusOperation + " Tries to Search An Iframe");
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection());
        }
        return statusOperation;
    }

    /**
     * This method is used to trie n times to see if an element is displayed or not in case in the number of tries the element is not found
     * it will refresh the page and will try again to search the element
     *
     * @param webElement       it contains the webelement to verify if it is displayed or not
     * @param whileTries       it contains an int number of how many times it will try to find the element
     * @param timeOutInSeconds it contains an int value of the wait time in Seconds
     * @return it returns a boolean value true if element was found
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    protected boolean waitUntilVisibleLoop(WebElement webElement, int whileTries, int timeOutInSeconds) throws
            Exception {
        boolean statusOperation = false;
        waitForPageToLoad();
        try {
            for (int i = 0; i < whileTries; i++) {
                if (webElement.isDisplayed()) {
                    logger.info("Element Found");
                    statusOperation = true;
                    break;
                }
            }
            if (!statusOperation) {
                logger.info("Executing A Reload Of Page");
                reloadPage();
                waitForPageToLoad();
                statusOperation = waitForElementVisibility(webElement, timeOutInSeconds);
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method to get a message from ConstantMessages JSON file
     *
     * @param keyValue used to get the correct value from JSON file
     * @return it returns a string with the corresponding message
     * @throws Exception selenium Exception
     * @author J.Ruano
     */
    protected String getMessage(String keyValue) throws Exception {
        JsonFiles jsonFiles = new JsonFiles();
        jsonFiles.setFileName("ConstantMessages");
        String constantMessage = jsonFiles.getFieldArray(keyValue).toString();
        return constantMessage;
    }

    /**
     * Method to get the X and Y position of an webElement
     *
     * @param webElement it will contain the WebElement from where the position will be retreive
     * @return a list that contains at position 0 Y coordinates and position 1 X coordinates
     * @throws Exception selenium Exception selenium exception
     * @author J.Ruano
     */
    protected List<Integer> getXYElementPosition(WebElement webElement) throws Exception {
        List<Integer> statusOperation = new ArrayList<>();
        try {
            statusOperation.add(webElement.getLocation().getY());
            statusOperation.add(webElement.getLocation().getX());
        } catch (Exception e) {
            statusOperation = autoCasting(executeReflection(webElement));
        }
        return statusOperation;
    }

    /**
     * Method to get a message from ConstantMessages JSON file
     *
     * @param keyValue used to get the correct value from JSON file
     * @return it returns a string with the corresponding message
     * @throws Exception selenium Exception JSON object
     */
    public String getJSONData(String fileName, String keyValue) throws Exception {
        JsonFiles jsonFiles = new JsonFiles();
        jsonFiles.setFileName(fileName);
        return jsonFiles.getFieldArray(keyValue).toString();
    }

    /**
     * Method to invoke again a method n times when an exception occurred
     *
     * @param <Obj> array of objects (Params of the method)
     * @param args  it contains an array of objects from where it contain the parameters of the method to be invoke
     * @return it returns and object that contain the object value
     * @throws NoSuchMethodException     method invoke exception
     * @throws InvocationTargetException method invoke exception
     * @throws InstantiationException    method invoke exception
     * @throws IllegalAccessException    method invoke exception
     * @author J.Ruano
     */
    protected <Obj> Object executeReflection(Object... args) throws Exception {
        Method method = null;
        Object returnObj = null;
        if (Values.globalCounter < maxNumberOfTries) {
            Values.globalCounter++;
            for (Method m : this.myClass.getDeclaredMethods()) {
                if (m.getName().equalsIgnoreCase(getCallerMethodName())) {
                    method = m;
                    System.out.println(m.getReturnType());
                    break;
                }
            }
            logger.warn(Values.TXT_RETRYMSG001 + getCallerMethodName());
            returnObj = method.invoke(this.myClass.getConstructor().newInstance(), args);
        }
        Values.globalCounter = 0;
        return returnObj;
    }

    /**
     * It is used to get the curren method name in execution
     *
     * @return the name of the method currently executed
     * @author J.Ruano
     */
    public String getMethodName() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[1];
        return e.getMethodName();
    }

    /**
     * Method to get the name of the current method
     *
     * @return the name of the current method
     * @author J.Ruano
     */
    public static String getCurrentMethodName() {
        StackWalker stackWalker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        return StackWalker.getInstance().walk(s -> s.skip(1).findFirst()).get().getMethodName();
    }


    /**
     * Method to get the name of the Caller method i.e the waitMethod calls getCallerMethodName, it will retyrn the waitMethod since is the caller method
     *
     * @return the name of the caller method
     * @author J.Ruano
     */
    public static String getCallerMethodName() {
        return StackWalker.getInstance()
                .walk(s -> s.skip(2).findFirst())
                .get()
                .getMethodName();
    }

    /**
     * Method to get the value of the Object doing a cast
     *
     * @param objectToCast it contains the object to be cast
     * @param <T>
     * @return the value contained in the object
     * @author J.Ruano
     */
    public <T> T autoCasting(Object objectToCast) {
        T returnedValue = null;
        try {
            returnedValue = (T) objectToCast.getClass().cast(objectToCast);
        } catch (Exception e) {
            logger.warn("The value of the object is null");
        }
        return returnedValue;
    }

    /**
     * Method to get the value of the Object doing a cast
     *
     * @param objectToCast it contains the object to be cast
     * @return the value contained in the object
     * @author Alejandro Hernandez
     */
    private boolean autoCastingBoolean(Object objectToCast) {
        boolean returnedValue = false;
        try {
            returnedValue = (boolean) objectToCast.getClass().cast(objectToCast);
        } catch (Exception e) {
            logger.warn("The value of the object is null");
            if (objectToCast == null) {
                returnedValue = false;
            } else {
                returnedValue = true;
            }
        }
        return returnedValue;
    }

    public List<ArrayList<String>> splitIntoLists2(List<String> listToSplit, String splitCharacter) throws
            Exception {
        ArrayList<ArrayList<String>> ctmListOfList = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < listToSplit.size(); i++) {
            String[] listToArray = (String[]) splitRegex(listToSplit.get(i), splitCharacter).toArray(new String[splitRegex(listToSplit.get(i), splitCharacter).size()]);

        }


        return ctmListOfList;
    }

    /**
     * @param record          it contains the record(s) in a single string
     * @param dataSeparator   it contains the char to be able to split the Data on each record
     * @param recordSeparator it contains the char to be able to split Each Record
     * @return it returns a list of list
     * @throws Exception selenium Exception
     * @author Jonathan Ruano
     * @method This method creates a list of list with multiple records that are created from the string received to
     * the method
     */
    public List<List<String>> splitIntoLists(String record, String recordSeparator, String dataSeparator) throws
            Exception {
        List<List<String>> listOfListWithAllRecords = Lists.newArrayList();
        String[] listOfRecords = null;
        String[] arrayDataList = null;
        //===================Split Of Each Record
        listOfRecords = record.split(recordSeparator);
        for (int i = 0; i < listOfRecords.length; i++) {
            //===================Split Of Data for each record
            arrayDataList = listOfRecords[i].split(dataSeparator);
            listOfListWithAllRecords.add(new ArrayList<String>());
            for (int j = 0; j < arrayDataList.length; j++) {
                listOfListWithAllRecords.get(i).add(arrayDataList[j]);
            }
        }
        return listOfListWithAllRecords;
    }


    /**
     * @param webElement contains the WebElement to click
     * @throws Exception Javascript Error or selenium Exception
     * @author J.Ruano
     * @method perform a click action by Actions Class, Webelement.click or Javascript click, in case there is an error
     * with Actions, WebElement click and using as last option Javascript Click. Using the WebElement
     */
    public void clickMethodsWebElement(WebElement webElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.click(webElement).build().perform();
            logger.info("WebElement clicked: " + getWebElementLocatorPath(webElement));
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().trim().contains(Values.TXT_EXCINTERCEPTED.trim().toLowerCase())
                    || e.getMessage().toLowerCase().trim().contains(Values.TXT_JSEXCACTIONSCLICK.trim().toLowerCase())) {
                try {
                    logger.warn(Values.TXT_WEBELEMENTCLICK);
                    webElement.click();
                } catch (Exception e2) {
                    logger.warn(Values.TXT_JSCLICK);
                    clickElementJS(webElement);
                }
            } else {
                executeReflection(webElement);
            }
        }
    }

    /**
     * @param locator contains the locator path for the WebElement to be click
     * @throws Exception Javascript Error or selenium Exception
     * @author J.Ruano
     * @method perform a click action by Actions Class, Webelement.click or Javascript click, in case there is an error
     * with Actions, WebElement click and using as last option Javascript Click. Using the Locator path
     */
    public void clickMethodsByLocator(By locator) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.click(getWebElement(locator)).build().perform();
            logger.info("WebElement clicked: " + getWebElementLocatorPath(getWebElement(locator)));
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().trim().contains(Values.TXT_EXCINTERCEPTED.trim().toLowerCase())
                    || e.getMessage().toLowerCase().trim().contains(Values.TXT_JSEXCACTIONSCLICK.trim().toLowerCase())) {
                try {
                    logger.warn(Values.TXT_WEBELEMENTCLICK);
                    getWebElement(locator).click();
                } catch (Exception e2) {
                    logger.warn(Values.TXT_JSCLICK);
                    clickElementJS(getWebElement(locator));
                }
            } else {
                executeReflection(locator);
            }
        }
    }

    /**
     * @param webElement contains the WebElement to be click
     * @throws Exception Javascript Error or selenium Exception
     * @author J.Ruano
     * @method perform a click  and move action to the WebElement suing Actions Class, WebElement or Javascript click, in case there is an error
     * with Actions, WebElement click and using as last option Javascript Click. Using the WebElement
     */
    public void clickAndMoveMethodsWebElement(WebElement webElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).click(webElement).build().perform();
            logger.info("Element found: " + getWebElementLocatorPath(webElement));
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().trim().contains(Values.TXT_EXCINTERCEPTED.trim().toLowerCase())
                    || e.getMessage().toLowerCase().trim().contains(Values.TXT_JSEXCACTIONSCLICK.trim().toLowerCase())) {
                try {
                    logger.warn(Values.TXT_WEBELEMENTCLICK);
                    scrollToElement(webElement);
                    webElement.click();
                } catch (Exception e2) {
                    logger.warn(Values.TXT_JSCLICK);
                    scrollToElement(webElement);
                    clickElementJS(webElement);
                }
            } else {
                executeReflection(webElement);
            }
        }
    }

    /**
     * @param locator contains the WebElement to be click
     * @throws Exception Javascript Error or selenium Exception
     * @author J.Ruano
     * @method perform a click  and move action to the WebElement suing Actions Class, WebElement or Javascript click, in case there is an error
     * with Actions, WebElement click and using as last option Javascript Click. Using the locator path
     */
    public void clickAndMoveMethodsByLocator(By locator) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(getWebElement(locator)).click(getWebElement(locator)).build().perform();
            logger.info("Element found: " + getWebElementLocatorPath(getWebElement(locator)));
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().trim().contains(Values.TXT_EXCINTERCEPTED.trim().toLowerCase())
                    || e.getMessage().toLowerCase().trim().contains(Values.TXT_JSEXCACTIONSCLICK.trim().toLowerCase())) {
                try {
                    logger.warn(Values.TXT_WEBELEMENTCLICK);
                    scrollToElement(getWebElement(locator));
                    getWebElement(locator).click();
                } catch (Exception e2) {
                    logger.warn(Values.TXT_JSCLICK);
                    scrollToElement(getWebElement(locator));
                    clickElementJS(getWebElement(locator));
                }
            } else {
                executeReflection(locator);
            }
        }
    }


    /**
     * used to submit a form similar to a Click but it will wait until next page is load
     *
     * @param locator it contains the locator to search the WebElement
     * @throws Exception NoSuchElementException
     * @author J.Ruano
     */
    public void submitWebElementByLocator(By locator) throws Exception {
        getWebElement(locator).submit();
    }

    /**
     * used to submit a form similar to a Click but it will wait until next page is load
     *
     * @param webElement it contains the webElement to be submitted
     * @throws Exception NoSuchElementException
     * @author J.Ruano
     */
    public void submitByWebElement(WebElement webElement) throws Exception {
        webElement.submit();
    }

    /**
     * it gets a date with the following format "Mon Jul 05 15:10:26 CDT 2021"
     *
     * @return a string value of a date
     * @throws Exception
     * @author J.Ruano
     */
    public String getCalendarDate() throws Exception {
        Date date = java.util.Calendar.getInstance().getTime();
        return String.valueOf(date);
    }

    /**
     * use to select if the email use to send the regression results is external or internal. e.g. int or ext
     *
     * @return a list with the email and password
     * @throws Exception
     * @author J.Ruano
     */
    public List<String> getEmailData() throws Exception {
        String dataEmail = "";
        List<String> emailData;
        FileReading fileReading = new FileReading();
        fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);

        switch (fileReading.getField(Values.EMAIL_FIELDNAME).trim().toLowerCase()) {
            case "ext":
                dataEmail = Values.ARRAY_EMAILDATA[0];
                break;

            case "int":
                dataEmail = Values.ARRAY_EMAILDATA[1];
                break;

            default:
                logger.warn(Values.TXT_SWITCHDEFAULTMESSAGE);
                break;
        }
        emailData = splitRegex(dataEmail, Values.REGEX_COMMA);
        return emailData;
    }

    /**
     * Use to validate if a file exist on an specific path
     *
     * @param filePath contains the path of the folder where the file is stored
     * @param fileName contains the name of the file to search
     * @return a boolean value in case the file is found == true and if not found == false
     * @throws Exception
     * @author J.Ruano
     */
    public boolean searchAFile(String filePath, String fileName) throws Exception {
        boolean statusOperation = false;
        Path path = Paths.get(System.getProperty("user.dir") + filePath);
        File pathCreated = new File(String.valueOf(path));
        String[] filesArray = pathCreated.list();
        if (filesArray == null) {
            logger.warn(Values.TXT_FILEDOESNOTEXIST);
        } else {
            for (int i = 0; i <= filesArray.length - 1; i++) {
                logger.info(filesArray[i]);
                if (filesArray[i].equalsIgnoreCase(fileName)) {
                    statusOperation = true;
                    break;
                }
            }
        }
        return statusOperation;
    }

    public boolean validateErrorMessage(By webLocator, String messageError, int timeOutInSeconds) throws Exception {
        boolean statusOperation = false;
        String webElementText = "";
        if (waitForElementPresenceBy(webLocator, timeOutInSeconds)) {
            webElementText = getWebElement(webLocator).getText();
            logger.warn(webElementText);
            statusOperation = true;
        }
        return statusOperation;
    }

    public String searchIntoArray(String valueToSearch, String[] arrayList) throws Exception {
        for (int i = 0; i <= (arrayList.length - 1); i++) {
            if (arrayList[i].trim().startsWith(valueToSearch.trim())) {
                valueToSearch = arrayList[i];
                break;
            }
        }
        logger.info(Values.TXT_DRUG_USEFOR_PE + valueToSearch);
        return valueToSearch;
    }


    /**
     * It clicks the save button after all the data required for the HCP account is populated
     *
     * @throws Exception
     */
    public void clickSaveButton(WebElement buttonSaveAccount) throws Exception {
        try {
            if (isClickableElementEnabled(buttonSaveAccount, shortWait())) {
                clickElementJS(buttonSaveAccount);
            } else {
                if (waitForElementVisibility(buttonSaveAccount, mediumWait())) {
                    scrollToClickableElement(buttonSaveAccount, shortWait());
                    clickElementClickable(buttonSaveAccount, mediumWait());
                } else {
                    buttonSaveAccount.click();
                }
            }
        } catch (Exception e) {
            clickAndMoveMethodsWebElement(buttonSaveAccount);
        }
    }

    /**
     * use to search webelement with relative locators toRightOf, toLeftOf, above and below
     *
     * @param tagName         the tagname is related to the webelement that has to be found example button, input etc
     * @param relativeLocator toRightOf = right or r, toLeftOf = left or l, for above = above or a, for below = below or b
     * @param webElementPath  this is the path for the webeelement reference to find the one that we need
     * @return a WebElementList
     * @throws Exception
     * @author J.Ruano
     */
    public WebElement findRelativeLocatorsByXpath(String tagName, String relativeLocator, By webElementPath) throws Exception {
        List<WebElement> webElementList = null;
        WebElement webElement = null;
        try {
            switch (relativeLocator.toLowerCase().trim()) {
                case "left":
                case "l":
                    webElement = (driver.findElement(with(By.tagName(tagName)).toLeftOf(driver.findElement(webElementPath))));
                    break;

                case "right":
                case "r":
                    webElement = (driver.findElement(with(By.tagName(tagName)).toRightOf(driver.findElement(webElementPath))));
                    break;

                case "above":
                case "a":
                    webElement = (driver.findElement(with(By.tagName(tagName)).above(driver.findElement(webElementPath))));
                    break;

                case "below":
                case "b":
                    webElement = driver.findElement(with(By.tagName(tagName)).below(driver.findElement(webElementPath)));
                    break;
            }
        } catch (Exception e) {

        }
        return webElement;
    }

    /**
     * use to search webelement with relative locators toRightOf, toLeftOf, above and below
     *
     * @param tagName             the tagname is related to the webelement that has to be found example button, input etc
     * @param relativeLocator     toRightOf = right or r, toLeftOf = left or l, for above = above or a, for below = below or b
     * @param webElementReference this is the path for the webeelement reference to find the one that we need
     * @return a WebElementList
     * @throws Exception
     * @author J.Ruano
     */
    public WebElement findRelativeLocatorsWebElement(String tagName, String relativeLocator, WebElement webElementReference) throws Exception {
        WebElement webElement = null;
        try {
            switch (relativeLocator.toLowerCase().trim()) {
                case "left":
                case "l":
                    webElement = (driver.findElement(with(By.tagName(tagName)).toLeftOf(webElementReference)));
                    break;

                case "right":
                case "r":
                    webElement = (driver.findElement(with(By.tagName(tagName)).toRightOf(webElementReference)));
                    break;

                case "above":
                case "a":
                    webElement = (driver.findElement(with(By.tagName(tagName)).above(webElementReference)));
                    break;

                case "below":
                case "b":
                    webElement = driver.findElement(with(By.tagName(tagName)).below(webElementReference));
                    break;
            }
        } catch (Exception e) {

        }
        return webElement;
    }
}