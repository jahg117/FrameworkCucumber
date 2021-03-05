package base.functions;

import base.driverInitialize.DriverFactory;

import com.github.javafaker.Faker;
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

    /**
     * Return a WebElement if it is found
     *
     * @param locator to find as a WebElement
     * @throws Exception if the WebElement is not located
     * @author Alejandro Hernandez
     */
    protected WebElement getWebElement(By locator) {
        return driver.findElement(locator);
    }

    protected List<WebElement> getWebElementList(By locator) {
        return driver.findElements(locator);
    }


    /**
     * Return true if a WebElement is found or false if it's not found
     *
     * @param webElement       to find
     * @param timeOutInMinutes Time to wait in minutes.
     * @param pollingEvery     Seconds to search a WebElement every specific second.
     * @throws Exception if the element is not found
     * @author Alejandro Hernandez
     */
    protected void waitForElementFluentMinutes(WebElement webElement, int timeOutInMinutes, int pollingEvery) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver);
            wait.withTimeout(Duration.ofMinutes(timeOutInMinutes));
            wait.pollingEvery(Duration.ofSeconds(pollingEvery));
            wait.ignoring(NoSuchElementException.class);
            wait.ignoring(TimeoutException.class);
            wait.ignoring(StaleElementReferenceException.class);

            WebElement el = wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    logger.info("The WebElement was found: " + getWebElementLocatorPath(webElement));
                    return webElement;
                }
            });

        } catch (Exception e) {
            logger.error("The WebElement " + getWebElementLocatorPath(webElement) + " was not found");
            new NoSuchElementException("The WebElement was not found");
        }
    }

    /**
     * Return true if a WebElement is found or false if it's not found
     *
     * @param webElement       to find
     * @param timeOutInSeconds Time to wait in seconds.
     * @param pollingEvery     Seconds to search a WebElement every specific second.
     * @throws Exception if the element is not found
     * @author Alejandro Hernandez
     */
    protected void waitForElementFluentSeconds(WebElement webElement, int timeOutInSeconds, int pollingEvery) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver);
            wait.withTimeout(Duration.ofSeconds(timeOutInSeconds));
            wait.pollingEvery(Duration.ofSeconds(pollingEvery));
            wait.ignoring(NoSuchElementException.class);
            wait.ignoring(TimeoutException.class);
            wait.ignoring(StaleElementReferenceException.class);

            WebElement el = wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    logger.info("The WebElement was found: " + getWebElementLocatorPath(webElement));
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
     * @param element          WebElement to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementClickable(WebElement element, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.info("Element found " + getWebElementLocatorPath(element));
            return true;
        } catch (Exception e) {
            logger.warn("Element was not found " + getWebElementLocatorPath(element));
            return false;
        }
    }

    /**
     * Return true if a WebElement is visible or false if it's not visible
     *
     * @param webElement       WebElement to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementVisibility(WebElement webElement, int timeOutInSeconds) {
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
     * @param element          WebElement to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementNotVisible(WebElement element, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.invisibilityOf(element));
            logger.info("Element not visible " + getWebElementLocatorPath(element));
            return true;
        } catch (Exception e) {
            logger.warn("Element found " + getWebElementLocatorPath(element));
            return false;
        }
    }

    /**
     * Return true if a list of WebElements is not visible or false if it's visible
     *
     * @param elements         List of WebElements to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementListNotVisible(List<WebElement> elements, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
            logger.info("List of web elements is not visible " + getWebElementLocatorPath(elements));
            return true;
        } catch (Exception e) {
            logger.warn("List of web elements is visible " + getWebElementLocatorPath(elements));
            return false;
        }
    }

    /**
     * Return true if a list of WebElements is visible or false if it's not visible
     *
     * @param elements         List of WebElements to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementListVisible(List<WebElement> elements, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            logger.info("List of web elements is visible " + getWebElementLocatorPath(elements));
            return true;
        } catch (Exception e) {
            logger.warn("List of web elements is not visible");
            return false;
        }
    }

    /**
     * Return true if an Alert is displayed or false if it's not displayed
     *
     * @param timeOutInSeconds Seconds to wait for an Alert.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForAlertVisible(int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.alertIsPresent());
            logger.info("Alert is visible");
            return true;
        } catch (Exception e) {
            logger.warn("Alert is not visible");
            return false;
        }
    }

    /**
     * Return true if a WebElement with a specific attribute is visible or false if it's not visible
     *
     * @param element          WebElement to find.
     * @param attribute        WebElement Attribute
     * @param attributeValue   Attribute Value
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementAttributeContains(WebElement element, String attribute, String attributeValue, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.attributeContains(element, attribute, attributeValue));
            logger.info("Element found " + getWebElementLocatorPath(element));
            return true;
        } catch (Exception e) {
            logger.warn("Element not found " + getWebElementLocatorPath(element));
            return false;
        }
    }

    /**
     * Return true if a WebElement attribute is not empty or false if it's empty
     *
     * @param element          WebElement to find.
     * @param attribute        WebElement Attribute
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementAttributeNotEmpty(WebElement element, String attribute, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
            logger.info("Element not found " + getWebElementLocatorPath(element));
            return true;
        } catch (Exception e) {
            logger.warn("Element found " + getWebElementLocatorPath(element));
            return false;
        }
    }

    /**
     * An expectation for checking given WebElement has attribute with a specific value
     *
     * @param element          WebElement to find.
     * @param attribute        WebElement Attribute
     * @param attributeValue   Attribute Value
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementAttributeToBe(WebElement element, String attribute, String attributeValue, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.attributeToBe(element, attribute, attributeValue));
            logger.info("Element found " + getWebElementLocatorPath(element));
            return true;
        } catch (Exception e) {
            logger.warn("Element not found " + getWebElementLocatorPath(element));
            return false;
        }
    }

    /**
     * Return true if an expected page title is displayed or false if it's not displayed
     *
     * @param title            Page title value
     * @param timeOutInSeconds Seconds to wait for an expected title.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementPageTitle(String title, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.titleIs(title));
            logger.info("The page with title " + title + "is displayed");
            return true;
        } catch (Exception e) {
            logger.info("The page with title " + title + "is not displayed");
            return false;
        }
    }

    /**
     * Return true if a specific URL is displayed or false if it's not displayed
     *
     * @param url              URL to be displayed in the page
     * @param timeOutInSeconds Seconds to wait for a URL.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementUrlToBe(String url, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.urlToBe(url));
            logger.info("The page with URL " + url + "is displayed");
            return true;
        } catch (Exception e) {
            logger.warn("The page with URL " + url + "is not displayed");
            return false;
        }
    }

    /**
     * Return true and switch to a frame if it's displayed or false if it's not displayed
     *
     * @param element          WebElement frame
     * @param timeOutInSeconds Seconds to wait for an expected frame.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementFrameAndSwitchToIt(WebElement element, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
            logger.info("Element frame found " + getWebElementLocatorPath(element));
            return true;
        } catch (Exception e) {
            logger.warn("Element frame is not found " + getWebElementLocatorPath(element));
            return false;
        }
    }

    /**
     * Return true if a WebElement is selected or false if it's not selected
     *
     * @param element          WebElement
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementSelected(WebElement element, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeSelected(element));
            logger.info("WebElement is selected " + getWebElementLocatorPath(element));
            return true;
        } catch (Exception e) {
            logger.warn("WebElement is not selected " + getWebElementLocatorPath(element));
            return false;
        }
    }

    /**
     * Return true if a WebElement has a specific text or false if it's not present
     *
     * @param element          WebElement
     * @param textElement      Text to wait from a WebElement
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementTextPresent(WebElement element, String textElement, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.textToBePresentInElement(element, textElement));
            logger.warn("WebElement " + getWebElementLocatorPath(element) + " with text " + textElement + " is displayed");
            return true;
        } catch (Exception e) {
            logger.warn("WebElement " + getWebElementLocatorPath(element) + " with text " + textElement + " is not displayed");
            return false;
        }
    }

    /**
     * Return true if a WebElement element's value has a specific text or false if it's not present
     *
     * @param element          WebElement
     * @param textElementValue Text to be present in the element's value attribute
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementTextPresentValue(WebElement element, String textElementValue, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.textToBePresentInElementValue(element, textElementValue));
            logger.info("WebElement " + getWebElementLocatorPath(element) + " with text in attribute " + textElementValue + " is displayed");
            return true;
        } catch (Exception e) {
            logger.warn("WebElement " + getWebElementLocatorPath(element) + " with text in attribute " + textElementValue + " is not displayed");
            return false;
        }
    }

    /**
     * This method is to give wait time to load a page.
     *
     * @author Alejandro Hernandez
     */
    protected void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
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
    protected boolean waitForElementPresenceBy(By locator, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            logger.info("Element found: " + locator.toString());
            return true;
        } catch (Exception e) {
            logger.warn("Element was not found: " + locator.toString());
            return false;
        }
    }

    /**
     * Return true if a WebElement is displayed with a specific text
     *
     * @param locator          it contains the locator (path) to search the element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementTextToBeBy(By locator, String text, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.textToBe(locator, text));
            logger.info("Element found: " + locator.toString());
            return true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString());
            return false;
        }
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
    protected boolean waitForElementTextToBePresentInElementValueBy(By locator, String text, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
            logger.info("Element found: " + locator.toString() + " with value: " + text);
            return true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString() + " with value: " + text);
            return false;
        }
    }

    /**
     * Return true if a WebElement is selected or false if it's not
     *
     * @param locator          it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementToBeSelectedBy(By locator, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeSelected(locator));
            logger.info("Element already selected: " + locator.toString());
            return true;
        } catch (Exception e) {
            logger.warn("Element is not selected: " + locator.toString());
            return false;
        }
    }

    /**
     * Return true and switch if a frame is available or false if it's not
     *
     * @param locator          it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementFrameToBeAvailableAndSwitchToItBy(By locator, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
            logger.info("iFrame found and switch to it: " + locator.toString());
            return true;
        } catch (Exception e) {
            logger.warn("iFrame not found: " + locator.toString());
            return false;
        }
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
    protected boolean waitForElementAttributeToBeBy(By locator, String attribute, String value, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.attributeToBe(locator, attribute, value));
            logger.info("Element found: " + locator.toString() + " with attribute: " + attribute + " and value: " + value);
            return true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString());
            return false;
        }
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
    protected boolean waitForElementAttributeContainsBy(By locator, String attribute, String value, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
            logger.info("Element found: " + locator.toString() + " with attribute: " + attribute + " and value: " + value);
            return true;
        } catch (Exception e) {
            logger.info("Element found: " + locator.toString());
            return false;
        }
    }

    /**
     * Return true if a WebElement is clickable or false if it's not
     *
     * @param locator          it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementToBeClickableBy(By locator, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            logger.info("Element found: " + locator.toString());
            return true;
        } catch (Exception e) {
            logger.info("Element not found: " + locator.toString());
            return false;
        }
    }

    /**
     * Return true if a WebElement is visible or false if it's not
     *
     * @param locator          it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementVisibilityOfElementLocatedBy(By locator, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element found: " + locator.toString());
            return true;
        } catch (Exception e) {
            logger.info("Element not found: " + locator.toString());
            return false;
        }
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
    protected boolean waitForElementSelectionStateToBeBy(By locator, boolean selectionState, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementSelectionStateToBe(locator, selectionState));
            logger.info("Element found: " + locator.toString());
            return true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator.toString());
            return false;
        }
    }

    /**
     * Return true if a WebElement is not visible or false if it is visible
     *
     * @param locator          it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForElementInvisibilityOfElementLocatedBy(By locator, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            logger.info("Element not found: " + locator.toString());
            return true;
        } catch (Exception e) {
            logger.warn("Element found: " + locator.toString());
            return false;
        }
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
    protected boolean waitForElementInvisibilityOfElementWithTextBy(By locator, String text, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
            logger.info("Element not found: " + locator.toString());
            return true;
        } catch (Exception e) {
            logger.warn("Element found: " + locator.toString());
            return false;
        }
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
    protected boolean waitForNumberOfElementsToBe(By locator, int numberElements, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.numberOfElementsToBe(locator, numberElements));
            logger.info("Elements found: " + locator.toString() + " number of elements: " + numberElements);
            return true;
        } catch (Exception e) {
            logger.warn("Expected number of elements not found");
            return false;
        }
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
    protected boolean waitForNumberOfElementsToBeLessThanBy(By locator, int numberElements, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(locator, numberElements));
            logger.info("Elements found: " + locator.toString() + " number of elements less than: " + numberElements);
            return true;
        } catch (Exception e) {
            logger.warn("Expected number of elements not found");
            return false;
        }
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
    protected boolean waitForNumberOfElementsToBeMoreThanBy(By locator, int numberElements, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, numberElements));
            logger.info("Elements found: " + locator.toString() + " number of elements more than: " + numberElements);
            return true;
        } catch (Exception e) {
            logger.warn("Expected number of elements not found");
            return false;
        }
    }

    /**
     * Return true if all the elements were found
     *
     * @param locator          it contains the locator (path) to search an element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     * @author Alejandro Hernandez
     */
    protected boolean waitForPresenceOfAllElementsLocatedBy(By locator, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            logger.info("Elements found: " + locator.toString());
            return true;
        } catch (Exception e) {
            logger.info("Elements not found: " + locator.toString());
            return false;
        }
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
    protected boolean waitForTextToBePresentInElementLocatedBy(By locator, String text, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Method used to click and wait for a clickable WebElement
     *
     * @param webElement contains the By Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void clickElementClickable(By webElement, int waitTime) throws Exception {
        if (waitForElementToBeClickableBy(webElement, waitTime)) {
            clickWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element is not clickable");
            throw new NoSuchElementException("Element not clickable");
        }
    }

    /**
     * Method used to click and wait for a visible WebElement
     *
     * @param webElement contains the By Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void clickElementVisible(By webElement, int waitTime) throws Exception {
        if (waitForElementPresenceBy(webElement, waitTime)) {
            clickWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click and wait for a visible WebElement
     *
     * @param webElement contains the By Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickElementVisible(By webElement, int waitTime) throws Exception {
        if (waitForElementPresenceBy(webElement, waitTime)) {
            doubleClickWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click, move and wait for a visible WebElement
     *
     * @param webElement contains the By Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickAndMoveToElementVisible(By webElement, int waitTime) throws Exception {
        if (waitForElementPresenceBy(webElement, waitTime)) {
            doubleClickAndMoveToWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click and wait for a clickable WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickElementClickable(By webElement, int waitTime) throws Exception {
        if (waitForElementToBeClickableBy(webElement, waitTime)) {
            doubleClickWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click, move and wait for a clickable WebElement
     *
     * @param webElement contains the By Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickAndMoveToElementClickable(By webElement, int waitTime) throws Exception {
        if (waitForElementToBeClickableBy(webElement, waitTime)) {
            doubleClickAndMoveToWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to click, move and wait for a visible WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void clickAndMoveToElementVisible(By webElement, int waitTime) throws Exception {
        if (waitForElementPresenceBy(webElement, waitTime)) {
            clickAndMoveToWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to click, move and wait for a clickable WebElement
     *
     * @param webElement contains the By Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void clickAndMoveToElementClickable(By webElement, int waitTime) throws Exception {
        if (waitForElementToBeClickableBy(webElement, waitTime)) {
            clickAndMoveToWebElementByActions(getWebElement(webElement));
            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element is not clickable");
            throw new NoSuchElementException("Element not clickable");
        }
    }

    /**
     * Method used to click and wait for a clickable WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void clickElementClickable(WebElement webElement, int waitTime) throws Exception {
        if (waitForElementClickable(webElement, waitTime)) {
            clickWebElementByActions(webElement);
            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element is not clickable");
            throw new NoSuchElementException("Element not clickable");
        }
    }

    /**
     * Method used to click and wait for a visible WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void clickElementVisible(WebElement webElement, int waitTime) throws Exception {
        if (waitForElementVisibility(webElement, waitTime)) {
            clickWebElementByActions(webElement);
            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click and wait for a visible WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickElementVisible(WebElement webElement, int waitTime) throws Exception {
        if (waitForElementVisibility(webElement, waitTime)) {
            doubleClickWebElementByActions(webElement);
            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click, move and wait for a visible WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickAndMoveToElementVisible(WebElement webElement, int waitTime) throws Exception {
        if (waitForElementVisibility(webElement, waitTime)) {
            doubleClickAndMoveToWebElementByActions(webElement);
            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click and wait for a clickable WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickElementClickable(WebElement webElement, int waitTime) throws Exception {
        if (waitForElementClickable(webElement, waitTime)) {
            doubleClickWebElementByActions(webElement);
            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to double click, move and wait for a clickable WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void doubleClickAndMoveToElementClickable(WebElement webElement, int waitTime) throws Exception {
        if (waitForElementClickable(webElement, waitTime)) {
            doubleClickAndMoveToWebElementByActions(webElement);
            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to click, move and wait for a visible WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void clickAndMoveToElementVisible(WebElement webElement, int waitTime) throws Exception {
        if (waitForElementVisibility(webElement, waitTime)) {
            clickAndMoveToWebElementByActions(webElement);
            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to click, move and wait for a clickable WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void clickAndMoveToElementClickable(WebElement webElement, int waitTime) throws Exception {
        if (waitForElementClickable(webElement, waitTime)) {
            clickAndMoveToWebElementByActions(webElement);
            logger.info("WebElement clicked");
        } else {
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
        try {
            //logger.info("USING " + usedMethod + " TO SCROLL TO AN ELEMENT");
            jsExecutor.executeScript(scrollElementIntoMiddle, wElement);
            return waitForElementVisibility(wElement, 10);
        } catch (NoSuchElementException | StaleElementReferenceException e) {
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
     * @throws Exception
     * @author Alejandro Hernandez
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
     * @throws Exception
     * @author Alejandro Hernandez
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
     * @param webElement to scroll
     * @throws Exception
     * @author Alejandro Hernandez
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
     * @param wElement    contains the WebElement to move
     * @param timeSeconds to wait
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void scrollToWebElementVisibleByAction(WebElement wElement, int timeSeconds) throws Exception {
        if (waitForElementVisibility(wElement, timeSeconds)) {
            scrollToWebElementByAction(wElement);
        } else {
            logger.error("WebElement not located");
            new NoSuchElementException("Element not found");
        }
    }

    /**
     * This method is used to move to a clickable element by Action Class
     *
     * @param wElement    contains the WebElement to move
     * @param timeSeconds to wait
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void scrollToWebElementClickableByAction(WebElement wElement, int timeSeconds) throws Exception {
        if (waitForElementClickable(wElement, timeSeconds)) {
            scrollToWebElementByAction(wElement);
        } else {
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
        } catch (Exception e) {
            logger.error("Web element not found");
            throw new NoSuchElementException("Web Element not found");
        }
    }

    /**
     * Method used to sendKeys, move and wait for a visible WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysAndMoveToElementVisible(By webElement, String text, int waitTime) throws Exception {
        if (waitForElementPresenceBy(webElement, waitTime)) {
            sendKeysAndMoveToWebElementByActions(getWebElement(webElement), text);
        } else {
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to sendKeys, move and wait for a visible WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysAndMoveToElementClickable(By webElement, String text, int waitTime) throws Exception {
        if (waitForElementToBeClickableBy(webElement, waitTime)) {
            sendKeysAndMoveToWebElementByActions(getWebElement(webElement), text);
        } else {
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to sendKeys and wait for a visible WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysElementVisible(By webElement, String text, int waitTime) throws Exception {
        if (waitForElementPresenceBy(webElement, waitTime)) {
            sendKeysWebElementByActions(getWebElement(webElement), text);
        } else {
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to sendKeys and wait for a visible WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysElementClickable(By webElement, String text, int waitTime) throws Exception {
        if (waitForElementToBeClickableBy(webElement, waitTime)) {
            sendKeysWebElementByActions(getWebElement(webElement), text);
        } else {
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to select a dropdown option by text
     *
     * @param webElement contains the Element to select
     * @param text       to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectDropDownByText(By webElement, String text, int waitTime) throws Exception {
        if (waitForElementPresenceBy(webElement, waitTime)) {
            selectDropDownByText(getWebElement(webElement), text);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to move and select a dropdown option by text
     *
     * @param webElement contains the Element to select
     * @param text       to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectAndMoveDropdownByText(By webElement, String text, int waitTime) throws Exception {
        if (waitForElementPresenceBy(webElement, waitTime)) {
            selectAndMoveToDropDownByText(getWebElement(webElement), text);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to select a dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param index      to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectDropDownByIndex(By webElement, int index, int waitTime) throws Exception {
        if (waitForElementPresenceBy(webElement, waitTime)) {
            selectDropDownByIndex(getWebElement(webElement), index);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to move and select a dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param index      to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectAndMoveDropdownByIndex(By webElement, int index, int waitTime) throws Exception {
        if (waitForElementPresenceBy(webElement, waitTime)) {
            selectAndMoveToDropDownByIndex(getWebElement(webElement), index);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to select a dropdown option by text
     *
     * @param webElement contains the Element to select
     * @param text       to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectDropDownClickableByText(By webElement, String text, int waitTime) throws Exception {
        if (waitForElementToBeClickableBy(webElement, waitTime)) {
            selectDropDownByText(getWebElement(webElement), text);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to move and select a dropdown option by text
     *
     * @param webElement contains the Element to select
     * @param text       to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectAndMoveDropdownClickableByText(By webElement, String text, int waitTime) throws Exception {
        if (waitForElementToBeClickableBy(webElement, waitTime)) {
            selectAndMoveToDropDownByText(getWebElement(webElement), text);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to select a dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param index      to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectDropDownClickableByIndex(By webElement, int index, int waitTime) throws Exception {
        if (waitForElementToBeClickableBy(webElement, waitTime)) {
            selectDropDownByIndex(getWebElement(webElement), index);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to move and select a dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param index      to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectAndMoveDropdownClickableByIndex(By webElement, int index, int waitTime) throws Exception {
        if (waitForElementToBeClickableBy(webElement, waitTime)) {
            selectAndMoveToDropDownByIndex(getWebElement(webElement), index);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to select a random dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectDropDownVisibleRandomOption(By webElement, int waitTime) throws Exception {
        if (waitForElementPresenceBy(webElement, waitTime)) {
            selectRandomDropDownOption(getWebElement(webElement));
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to select a random dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectAndMoveDropDownVisibleRandomOption(By webElement, int waitTime) throws Exception {
        if (waitForElementPresenceBy(webElement, waitTime)) {
            scrollMethodToWebElementByActions(getWebElement(webElement));
            selectRandomDropDownOption(getWebElement(webElement));
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to select a random dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectDropDownClickableRandomOption(By webElement, int waitTime) throws Exception {
        if (waitForElementToBeClickableBy(webElement, waitTime)) {
            selectRandomDropDownOption(getWebElement(webElement));
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to select a random dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectAndMoveDropDownClickableRandomOption(By webElement, int waitTime) throws Exception {
        if (waitForElementToBeClickableBy(webElement, waitTime)) {
            scrollMethodToWebElementByActions(getWebElement(webElement));
            selectRandomDropDownOption(getWebElement(webElement));
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to return a random webElement from a List<WebElement>
     *
     * @param webElementList
     * @param timeSeconds    to wait for visible elements
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected WebElement getRandomWebElementFromList(By webElementList, int timeSeconds) throws IllegalAccessException {
        try {
            if (waitForPresenceOfAllElementsLocatedBy(webElementList, timeSeconds)) {
                Random random = new Random();
                return getWebElementList(webElementList).get(random.nextInt(getWebElementList(webElementList).size()));
            } else {
                logger.error("The list of WebElements was not found/visible");
                throw new NoSuchElementException("WebElements are not visible");
            }
        } catch (Exception e) {
            logger.error("List<WebElement> type invalid");
            throw new IllegalAccessException("List<WebElement> type invalid");
        }
    }

    /**
     * Method used to return a random webElement from a List<WebElement> except the first
     *
     * @param webElementList
     * @param timeSeconds    to wait for visible elements
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected WebElement getRandomWebElementFromListExceptFirst(List<WebElement> webElementList, int timeSeconds) throws IllegalAccessException {
        try {
            if (waitForElementListVisible(webElementList, timeSeconds)) {
                if (webElementList.size() != 1) {
                    return webElementList.get((int) (Math.random() * webElementList.size() - 1) + 1);
                } else {
                    return webElementList.get(0);
                }
            } else {
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
     * @param webElementList
     * @param attribute      to handle
     * @param attributeValue to search
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected WebElement getWebElementByAttributeFromList(By webElementList, String attribute, String attributeValue) throws IllegalAccessException {
        WebElement element = null;
        for (WebElement elementList : getWebElementList(webElementList)) {
            if (elementList.getAttribute(attribute).equalsIgnoreCase(attributeValue)) {
                element = elementList;
                break;
            }
        }
        if (element != null) {
            return element;
        } else {
            logger.error("Not element found with attribute: " + attribute + " and value: " + attributeValue);
            throw new NoSuchElementException("Not element found");
        }
    }

    /**
     * Method used to validate if a visible webElement is selected or not
     *
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is selected or false if it is not selected
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected boolean isVisibleElementSelected(By webElement, int timeSeconds) {
        if (waitForElementPresenceBy(webElement, timeSeconds)) {
            logger.info("WebElement selected: " + getWebElementLocatorPath(getWebElement(webElement)) + ": " + getWebElement(webElement).isSelected());
            return getWebElement(webElement).isSelected();
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to validate if a visible webElement is selected or not
     *
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is clickable or false if it is not selected
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected boolean isClickableElementSelected(By webElement, int timeSeconds) {
        if (waitForElementToBeClickableBy(webElement, timeSeconds)) {
            logger.info("WebElement selected: " + getWebElementLocatorPath(getWebElement(webElement)) + ": " + getWebElement(webElement).isSelected());
            return getWebElement(webElement).isSelected();
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to validate if a visible webElement is enabled or not
     *
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is enabled or false if it is not
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected boolean isVisibleElementEnabled(By webElement, int timeSeconds) {
        if (waitForElementPresenceBy(webElement, timeSeconds)) {
            logger.info("WebElement enabled: " + getWebElementLocatorPath(getWebElement(webElement)) + ": " + getWebElement(webElement).isSelected());
            return getWebElement(webElement).isEnabled();
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to validate if a clickable webElement is enabled or not
     *
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is enabled or false if it is not
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected boolean isClickableElementEnabled(By webElement, int timeSeconds) {
        if (waitForElementToBeClickableBy(webElement, timeSeconds)) {
            logger.info("WebElement enabled: " + getWebElementLocatorPath(getWebElement(webElement)) + ": " + getWebElement(webElement).isSelected());
            return getWebElement(webElement).isEnabled();
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to sendKeys, move and wait for a visible WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysAndMoveToElementVisible(WebElement webElement, String text, int waitTime) throws Exception {
        if (waitForElementVisibility(webElement, waitTime)) {
            sendKeysAndMoveToWebElementByActions(webElement, text);
        } else {
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to sendKeys, move and wait for a visible WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysAndMoveToElementClickable(WebElement webElement, String text, int waitTime) throws Exception {
        if (waitForElementClickable(webElement, waitTime)) {
            sendKeysAndMoveToWebElementByActions(webElement, text);
        } else {
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to sendKeys and wait for a visible WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysElementVisibleWithCoordinates(WebElement webElement, String text, int xOffset, int yOffset, int waitTime) throws Exception {
        if (waitForElementVisibility(webElement, waitTime)) {
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement, 5, 5).click().sendKeys(text).perform();
        } else {
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to sendKeys and wait for a visible WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysElementVisible(WebElement webElement, String text, int waitTime) throws Exception {
        if (waitForElementVisibility(webElement, waitTime)) {
            sendKeysWebElementByActions(webElement, text);
        } else {
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to sendKeys and wait for a visible WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysElementClickable(WebElement webElement, String text, int waitTime) throws Exception {
        if (waitForElementClickable(webElement, waitTime)) {
            sendKeysWebElementByActions(webElement, text);
        } else {
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to select a dropdown option by text
     *
     * @param webElement contains the Element to select
     * @param text       to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectDropDownByText(WebElement webElement, String text, int waitTime) throws Exception {
        if (waitForElementVisibility(webElement, waitTime)) {
            selectDropDownByText(webElement, text);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to move and select a dropdown option by text
     *
     * @param webElement contains the Element to select
     * @param text       to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectAndMoveDropdownByText(WebElement webElement, String text, int waitTime) throws Exception {
        if (waitForElementVisibility(webElement, waitTime)) {
            selectAndMoveToDropDownByText(webElement, text);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to select a dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param index      to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectDropDownByIndex(WebElement webElement, int index, int waitTime) throws Exception {
        if (waitForElementVisibility(webElement, waitTime)) {
            selectDropDownByIndex(webElement, index);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to move and select a dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param index      to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectAndMoveDropdownByIndex(WebElement webElement, int index, int waitTime) throws Exception {
        if (waitForElementVisibility(webElement, waitTime)) {
            selectAndMoveToDropDownByIndex(webElement, index);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to select a dropdown option by text
     *
     * @param webElement contains the Element to select
     * @param text       to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectDropDownClickableByText(WebElement webElement, String text, int waitTime) throws Exception {
        if (waitForElementClickable(webElement, waitTime)) {
            selectDropDownByText(webElement, text);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to move and select a dropdown option by text
     *
     * @param webElement contains the Element to select
     * @param text       to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectAndMoveDropdownClickableByText(WebElement webElement, String text, int waitTime) throws Exception {
        if (waitForElementClickable(webElement, waitTime)) {
            selectAndMoveToDropDownByText(webElement, text);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to select a dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param index      to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectDropDownClickableByIndex(WebElement webElement, int index, int waitTime) throws Exception {
        if (waitForElementClickable(webElement, waitTime)) {
            selectDropDownByIndex(webElement, index);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to move and select a dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param index      to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectAndMoveDropdownClickableByIndex(WebElement webElement, int index, int waitTime) throws Exception {
        if (waitForElementClickable(webElement, waitTime)) {
            selectAndMoveToDropDownByIndex(webElement, index);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to get a random date with format mm/dd/yyyy
     *
     * @return String with the date format
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected String getRandomDate() {
        Faker faker = new Faker();
        Date randomDate = faker.date().birthday(18, 70);
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(randomDate);
    }

    /**
     * Return a random number
     *
     * @return a random String number
     * @author Alejandro Hernandez
     */
    protected String getRandomNumber() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100000));
    }

    /**
     * Method used to select a random dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectDropDownVisibleRandomOption(WebElement webElement, int waitTime) throws Exception {
        if (waitForElementVisibility(webElement, waitTime)) {
            selectRandomDropDownOption(webElement);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to select a random dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectAndMoveDropDownVisibleRandomOption(WebElement webElement, int waitTime) throws Exception {
        if (waitForElementVisibility(webElement, waitTime)) {
            scrollMethodToWebElementByActions(webElement);
            selectRandomDropDownOption(webElement);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to select a random dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectDropDownClickableRandomOption(WebElement webElement, int waitTime) throws Exception {
        if (waitForElementClickable(webElement, waitTime)) {
            selectRandomDropDownOption(webElement);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to select a random dropdown option by index
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectAndMoveDropDownClickableRandomOption(WebElement webElement, int waitTime) throws Exception {
        if (waitForElementClickable(webElement, waitTime)) {
            scrollMethodToWebElementByActions(webElement);
            selectRandomDropDownOption(webElement);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to return a random webElement from a List<WebElement>
     *
     * @param webElementList
     * @param timeSeconds    to wait for visible elements
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected WebElement getRandomWebElementFromList(List<WebElement> webElementList, int timeSeconds) throws IllegalAccessException {
        try {
            if (waitForElementListVisible(webElementList, timeSeconds)) {
                Random random = new Random();
                return webElementList.get(random.nextInt(webElementList.size()));
            } else {
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
     * @param webElementList
     * @param attribute      to handle
     * @param attributeValue to search
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected WebElement getWebElementByAttributeFromList(List<WebElement> webElementList, String attribute, String attributeValue) throws IllegalAccessException {
        WebElement element = null;
        for (WebElement elementList : webElementList) {
            if (elementList.getAttribute(attribute).equalsIgnoreCase(attributeValue)) {
                element = elementList;
                break;
            }
        }

        if (element != null) {
            return element;
        } else {
            logger.error("Not element found with attribute: " + attribute + " and value: " + attributeValue);
            throw new NoSuchElementException("Not element found");
        }
    }

    /**
     * Method used to validate if a visible webElement is selected or not
     *
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is selected or false if it is not selected
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected boolean isVisibleElementSelected(WebElement webElement, int timeSeconds) {
        if (waitForElementVisibility(webElement, timeSeconds)) {
            logger.info("WebElement selected: " + getWebElementLocatorPath(webElement) + ": " + webElement.isSelected());
            return webElement.isSelected();
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to validate if a visible webElement is selected or not
     *
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is clickable or false if it is not selected
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected boolean isClickableElementSelected(WebElement webElement, int timeSeconds) {
        if (waitForElementClickable(webElement, timeSeconds)) {
            logger.info("WebElement selected: " + getWebElementLocatorPath(webElement) + ": " + webElement.isSelected());
            return webElement.isSelected();
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to validate if a visible webElement is enabled or not
     *
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is enabled or false if it is not
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected boolean isVisibleElementEnabled(WebElement webElement, int timeSeconds) {
        if (waitForElementVisibility(webElement, timeSeconds)) {
            logger.info("WebElement enabled: " + getWebElementLocatorPath(webElement) + ": " + webElement.isSelected());
            return webElement.isEnabled();
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to validate if a clickable webElement is enabled or not
     *
     * @param webElement
     * @param timeSeconds
     * @return true if a webElement is enabled or false if it is not
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected boolean isClickableElementEnabled(WebElement webElement, int timeSeconds) {
        if (waitForElementClickable(webElement, timeSeconds)) {
            logger.info("WebElement enabled: " + getWebElementLocatorPath(webElement) + ": " + webElement.isSelected());
            return webElement.isEnabled();
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to switch in a frame By WebElement, Index or Name
     *
     * @param frame       WebElement, int or String
     * @param timeSeconds wait only if the WebElement option is used.
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected <f> void switchToFrameByWebElementIndexOrName(f frame, int timeSeconds) {
        try {
            String frameType = frame.getClass().getName();
            if (frameType.contains("Integer")) {
                driver.switchTo().frame((Integer) frame);
            } else {
                if (frameType.contains("String")) {
                    driver.switchTo().frame(frame.toString());
                } else {
                    if (waitForElementVisibility((WebElement) frame, timeSeconds)) {
                        driver.switchTo().frame((WebElement) frame);
                    } else {
                        logger.error("The Web Element was not found");
                        throw new NoSuchElementException("Element not valid");
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * Method used to switch to the parent frame
     *
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void switchToParentFrame() throws Exception {
        try {
            driver.switchTo().parentFrame();
            logger.info("Switch to parent frame");
        } catch (Exception e) {
            logger.error("No parent frame was found");
            throw new Exception("No parent frame was found");
        }
    }

    /**
     * Method used to accept an alert
     *
     * @param timeSeconds wait for an alter to be visible
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void switchToAlertAccept(int timeSeconds) {
        if (waitForAlertVisible(timeSeconds)) {
            driver.switchTo().alert().accept();
        } else {
            logger.error("The alert is not displayed");
            new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to dismiss an alert
     *
     * @param timeSeconds wait for an alter to be visible
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void switchToAlertDismiss(int timeSeconds) {
        if (waitForAlertVisible(timeSeconds)) {
            driver.switchTo().alert().dismiss();
        } else {
            logger.error("The alert is not displayed");
            new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to get text from an alert
     *
     * @param timeSeconds wait for an alter to be visible
     * @return String
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected String switchToAlertGetText(int timeSeconds) {
        if (waitForAlertVisible(timeSeconds)) {
            return driver.switchTo().alert().getText();
        } else {
            logger.error("The alert is not displayed");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to accept an alert
     *
     * @param text        to be send in the alert
     * @param timeSeconds wait for an alter to be visible
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void switchToAlertSendKeys(String text, int timeSeconds) {
        if (waitForAlertVisible(timeSeconds)) {
            driver.switchTo().alert().sendKeys(text);
        } else {
            logger.error("The alert is not displayed");
            new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method used to switch to a window tab by index
     *
     * @param tabIndex tab number to switch
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void switchToWindow(int tabIndex) {
        try {
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabIndex));
            logger.info("Change to tab: " + tabIndex);
        } catch (Exception e) {
            logger.error("The tab was not found");
            throw new IndexOutOfBoundsException("Tab index not found");
        }
    }

    /**
     * Method used to get the number of windows open
     *
     * @return int
     * @author Alejandro Hernandez
     */
    protected int getOpenTabsSize() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        return tabs.size();
    }

    /**
     * Method used to close the current window
     *
     * @author Alejandro Hernandez
     */
    protected void closeWindow() {
        driver.close();
    }

    //***********************************************************************
    // private methods

    /**
     * This method is used to SendKeys to a WebElement by Action
     *
     * @param wElement
     * @author Alejandro Hernandez
     */
    protected void sendKeysWebElementByActions(WebElement wElement, String text) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.click(wElement).sendKeys(wElement, text).build().perform();
            logger.info("Element found: " + getWebElementLocatorPath(wElement));
            logger.info("Keys sent: " + text);
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: " + getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to SendKeys without specify a WebElement
     *
     * @param text
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void sendKeysByActions(String text) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(text).build().perform();
            logger.info("Keys sent: " + text);
        } catch (Exception e) {
            logger.error("Element not visible or not clickable");
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to move and SendKeys to a WebElement by Action
     *
     * @param wElement
     * @author Alejandro Hernandez
     */
    protected void sendKeysAndMoveToWebElementByActions(WebElement wElement, String text) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(wElement).sendKeys(wElement, text).build().perform();
            logger.info("Element found: " + getWebElementLocatorPath(wElement));
            logger.info("Keys sent: " + text);
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: " + getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to move and click a WebElement by Action Class
     *
     * @param wElement
     * @author Alejandro Hernandez
     */
    private void clickAndMoveToWebElementByActions(WebElement wElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(wElement).click(wElement).build().perform();
            logger.info("Element found: " + getWebElementLocatorPath(wElement));
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: " + getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to click a WebElement by Action Class
     *
     * @param wElement
     * @author Alejandro Hernandez
     */
    private void clickWebElementByActions(WebElement wElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.click(wElement).build().perform();
            logger.info("Element found: " + getWebElementLocatorPath(wElement));
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: " + getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to move and double click a WebElement by Action Class
     *
     * @param wElement
     * @author Alejandro Hernandez
     */
    private void doubleClickAndMoveToWebElementByActions(WebElement wElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(wElement).doubleClick(wElement).build().perform();
            logger.info("Element found: " + getWebElementLocatorPath(wElement));
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: " + getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to double click a WebElement by Action Class
     *
     * @param wElement
     * @author Alejandro Hernandez
     */
    private void doubleClickWebElementByActions(WebElement wElement) throws Exception {
        try {
            Actions actions = new Actions(driver);
            actions.doubleClick(wElement).build().perform();
            logger.info("Element found: " + getWebElementLocatorPath(wElement));
        } catch (Exception e) {
            logger.error("Element not visible or not clickable: " + getWebElementLocatorPath(wElement));
            logger.error(e.getMessage());
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
    protected String getWebElementLocatorPath(WebElement webElement) {
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
    protected String getWebElementLocatorPath(List<WebElement> webElement) {
        try {
            return webElement.toString().split("-> ")[1].replace("]", "");
        } catch (Exception e) {
            return webElement.toString().split("DefaultElementLocator")[1].replace("'", "");
        }
    }

    /**
     * This method is used to move to a visible element by Action Class
     *
     * @param wElement contains the WebElement to move
     * @throws Exception
     * @author Alejandro Hernandez
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
     * @param webElement
     * @param text       to select from dropdown
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectAndMoveToDropDownByText(WebElement webElement, String text) {
        try {
            scrollToWebElementByAction(webElement);
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Selected option: " + text);
        } catch (Exception e) {
            logger.error("Element not clickable");
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to move and select a dropdown option by index
     *
     * @param webElement
     * @param index      to select from dropdown
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectAndMoveToDropDownByIndex(WebElement webElement, int index) {
        try {
            scrollToWebElementByAction(webElement);
            Select select = new Select(webElement);
            select.selectByIndex(index);
            logger.info("Selected option: " + index);
        } catch (Exception e) {
            logger.error("Element not clickable");
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to move and select a dropdown option by text
     *
     * @param webElement
     * @param text       to select from dropdown
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectDropDownByText(WebElement webElement, String text) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Selected option: " + text);
        } catch (Exception e) {
            logger.error("Element not clickable");
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to select a dropdown option by index
     *
     * @param webElement
     * @param index      to select from index
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectDropDownByIndex(WebElement webElement, int index) {
        try {
            Select select = new Select(webElement);
            select.selectByIndex(index);
            logger.info("Selected option: " + index);
        } catch (Exception e) {
            logger.error("Element not clickable");
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to select a random dropdown option by index
     *
     * @param webElement
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected void selectRandomDropDownOption(WebElement webElement) {
        try {
            Random random = new Random();
            Select select = new Select(webElement);
            int optionIndex = random.nextInt(select.getOptions().size() - 1);
            select.selectByIndex(optionIndex++);
            logger.info("Selected option: " + optionIndex++);
        } catch (Exception e) {
            logger.error("Element not clickable");
            logger.error(e.getMessage());
        }
    }

    /**
     * This method is used to move and select a dropdown option by text
     *
     * @author J.Ruano
     */
    protected void switchToDefaultContentFrame() throws Exception {
        try {
            driver.switchTo().defaultContent();
            logger.error("Change to default content");
        } catch (Exception e) {
            logger.error("The default content was found");
            throw new Exception("No default content was found");
        }
    }

    /**
     * This method is used to switch to a subtab by name
     *
     * @param name
     * @param waitTime
     * @throws Exception
     * @author J.Ruano
     * @author J.Ruano
     */
    protected void switchSubTabByNameSF(String name, int waitTime) throws Exception {
        try {
            By pathForSubTabs = By.xpath("//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]");
            if (waitForPresenceOfAllElementsLocatedBy(pathForSubTabs, waitTime)) {
                for (WebElement tab : getWebElementList(pathForSubTabs)) {
                    if (getWebElementAttribute(tab, "title").trim().equalsIgnoreCase(name.trim())) {
                        clickAndMoveToElementClickable(tab, 10);
                        logger.info("Switch to sub-tab");
                        break;
                    }
                }
            } else {
                logger.warn("The sub-tab is not visible");
            }
        } catch (Exception e) {
            logger.error("The subt-tab " + name + " was not found");
            throw new NoSuchElementException("The sub-tab was not found");
        }
    }

    /**
     * This method is used to switch to a subtab by index
     *
     * @param index
     * @param waitTime
     * @throws Exception
     * @author J.Ruano
     * @author J.Ruano
     */
    protected void switchSubTabByIndexSF(int index, int waitTime) throws Exception {
        try {
            By pathForSubTabs = By.xpath("//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]");
            if (waitForPresenceOfAllElementsLocatedBy(pathForSubTabs, waitTime)) {
                List<WebElement> subTabsList = getWebElementList(pathForSubTabs);
                clickAndMoveToElementClickable(subTabsList.get(index), 10);
                logger.info("Switch to sub-tab");
            } else {
                logger.warn("The sub-tab is not visible");
            }
        } catch (Exception e) {
            logger.error("The tab " + index + " was not found");
            throw new NoSuchElementException("The sub-tab was not found");
        }
    }

    protected void closeSubTabByIndexSF(int index, int waitTime) throws Exception {
        try {
            By pathForSubTabs = By.xpath("//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]//*[starts-with(@class,'close')]");
            if (waitForPresenceOfAllElementsLocatedBy(pathForSubTabs, waitTime)) {
                List<WebElement> subTabsList = getWebElementList(pathForSubTabs);
                clickAndMoveToElementClickable(subTabsList.get(index), 10);
                logger.info("Switch to sub-tab");
            } else {
                logger.warn("The sub-tab is not visible");
            }
        } catch (Exception e) {
            logger.error("The tab " + index + " was not found");
            throw new NoSuchElementException("The sub-tab was not found");
        }
    }

    protected void closeLastSubTabSF(int waitTime) throws Exception {
        try {
            By subTabs = By.xpath("//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]//*[starts-with(@class,'close')]");
            By lastSubTab = By.xpath("(//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]//*[starts-with(@class,'close')]//button)[last()]");
            if (waitForPresenceOfAllElementsLocatedBy(subTabs, waitTime)) {
                waitForPresenceOfAllElementsLocatedBy(subTabs, waitTime);
                clickAndMoveToElementClickable(getWebElement(lastSubTab), waitTime);
                logger.info("sub-tab "+lastSubTab+ " clicked");
            } else {
                logger.warn("The sub-tab is not visible");
            }
        } catch (Exception e) {
            logger.error("The tab was not found");
            throw new NoSuchElementException("The sub-tab was not found");
        }
    }

    /**
     * Method used to select a random dropdown excluding 'None'
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author J.Ruano
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
     * This method is used to return visible elements from a list
     *
     * @param
     * @param webElementList
     * @throws Exception
     * @throws Exception
     * @author J.Ruano
     */
    protected List<WebElement> getVisibleElements(List<WebElement> webElementList) {
        List<WebElement> visibleWebElementsList = new LinkedList<>();
        for (WebElement wE : webElementList) {
            if (wE.isDisplayed()) {
                visibleWebElementsList.add(wE);
            }
        }
        return visibleWebElementsList;
    }

    /**
     * This method is used to get text from a WebElement
     *
     * @param webElement
     * @return String
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected String getWebElementText(WebElement webElement) {
        try {
            return webElement.getText();
        } catch (Exception e) {
            logger.error("Web Element not found: " + getWebElementLocatorPath(webElement));
            throw new NoSuchElementException("Web Element not found");
        }
    }

    /**
     * This method is used to get an attribute value from a WebElement
     *
     * @param webElement
     * @param attribute
     * @return String
     * @throws Exception
     * @author Alejandro Hernandez
     */
    protected String getWebElementAttribute(WebElement webElement, String attribute) {
        try {
            return webElement.getAttribute(attribute);
        } catch (Exception e) {
            logger.error("Web Element or attribute not found: " + getWebElementLocatorPath(webElement));
            throw new NoSuchElementException("Web Element not found");
        }
    }

    /**
     * Used to split some string given the string to split and the regex rule
     *
     * @param textToSplit it contains the string to be split
     * @param regexRule   it contains the regular expresion used to split the String
     * @return a list with the split text
     * @author J.Ruano
     */
    protected List<String> splitRegex(String textToSplit, String regexRule) {
        List<String> splitData = Arrays.asList(textToSplit.split(regexRule));
        return splitData;
    }

    /**
     * Method to move up or down using Actions, usedul for dropdowns that does not contains Select>option
     *
     * @param element        it contains the webelement or dropdown where the actions will be perform
     * @param moveDirection  where the actions will be apply Up or Down
     * @param ammountOfMoves the amount of action Up Or Down will be perform
     * @author J.Ruano
     */
    protected void moveDownUpAction(WebElement element, String moveDirection, int ammountOfMoves) {
        Actions actions = new Actions(driver);
        int counter = 0;
        boolean notNone = false;
        WebElement optionID = null;
        WebElement dropdownVisible = null;
        String valueOptionID = "";
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
    }

    /**
     * Method used to click and wait for a visible WebElement
     *
     * @param webElement contains the Element to select
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author J.Ruano
     */
    protected void clickWhileCondition(WebElement webElement, String attribute, String attributeValue, int waitTime) throws Exception {
        if (waitForElementVisibility(webElement, waitTime)) {
            do {
                //clickWebElementByActions(webElement);
                clickElementClickable(webElement, 10);
            } while (webElement.getAttribute(attribute).trim().equalsIgnoreCase(attributeValue.trim()));

            logger.info("WebElement clicked");
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Method to generate a TimeStamp returning a timestamp with an specific Format
     *
     * @param strFormat format required to return the timestamp
     * @return a string value
     * @author J.Ruano
     */
    public String generateTimeStamp(String strFormat) {
        String dateCreated = new SimpleDateFormat(strFormat).format(new Date()).replace(".", "");
        return dateCreated;
    }

    /**
     * Used to move and click to a element from a list that contains certain value in an attribute
     *
     * @param elementList    it contains the list with all the elements
     * @param attribute      it contains the attribute used to get the value
     * @param attributeValue it contains the value for the attribute
     * @return The webelement that matched with the attributeValue
     * @throws Exception
     * @author J.Ruano
     */
    protected WebElement clickAndMoveToElementClickableFromListByAttribute(List<WebElement> elementList, String attribute, String attributeValue) throws Exception {
        boolean elementFound = false;
        WebElement returnElement = null;
        for (WebElement element : elementList) {
            if (element.getAttribute(attribute).trim().equalsIgnoreCase(attributeValue.trim())) {
                clickAndMoveToWebElementByActions(element);
                returnElement = element;
                logger.info("WebElement clicked");
                elementFound = true;
                break;
            }
        }
        if (elementFound == false) {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
        return returnElement;
    }

    /**
     * Used to move and click to a element from a list that contains certain value in an attribute
     *
     * @param elementList it contains the list with all the elements
     * @param textValue   contains the value used to get the correct element
     * @return The element that matched with the textValue
     * @throws Exception
     * @author J.Ruano
     */
    protected WebElement clickAndMoveToElementClickableFromListByText(List<WebElement> elementList, String textValue) throws Exception {
        boolean elementFound = false;
        WebElement returnElement = null;
        for (WebElement element : elementList) {
            if (element.getText().trim().equalsIgnoreCase(textValue.trim())) {
                clickMethod(element);
                returnElement = element;
                logger.info("WebElement clicked");
                elementFound = true;
                break;
            }
        }
        if (elementFound == false) {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not found");
        }
        return returnElement;
    }

    /**
     * Method used to return a random webElement from a List<WebElement>
     *
     * @param webElementList
     * @throws Exception
     * @author J.Ruano
     */
    protected WebElement getRandomWebElementIgnoreIdexValue(List<WebElement> webElementList, int ignoreValue) throws IllegalAccessException {
        int randomNumber = 0;
        WebElement wE = null;
        try {
            Random random = new Random();
            do {
                randomNumber = random.nextInt(webElementList.size());
            } while (randomNumber == ignoreValue);
        } catch (Exception e) {
            logger.error("List<WebElement> type invalid");
            throw new IllegalAccessException("List<WebElement> type invalid");
        }
        return wE = webElementList.get(randomNumber);
    }

    /**
     * Method used to return a random webElement from a List<WebElement>
     *
     * @param webElementList
     * @throws Exception
     * @author J.Ruano
     */
    protected WebElement getRandomWebElementIgnoreText(List<WebElement> webElementList, String searchValue) throws IllegalAccessException {
        int randomNumber = 0;
        WebElement wE = null;
        Random random = new Random();

        try {
            do {
                randomNumber = random.nextInt(webElementList.size());
            } while (webElementList.get(randomNumber).getText().equalsIgnoreCase(searchValue));
        } catch (Exception e) {
            logger.error("List<WebElement> type invalid");
            throw new IllegalAccessException("List<WebElement> type invalid");
        }
        return wE = webElementList.get(randomNumber);
    }


    /**
     * Method used to return a random webElement from a List<WebElement>
     *
     * @param webElementList
     * @throws Exception
     * @author J.Ruano
     */
    protected WebElement getRandomWebElementIgnoreAttribute(List<WebElement> webElementList, String attributeValue, String searchValue) throws IllegalAccessException {
        int randomNumber = 0;
        WebElement wE = null;
        Random random = new Random();
        try {
            do {
                randomNumber = random.nextInt(webElementList.size());
            } while (webElementList.get(randomNumber).getAttribute(attributeValue.trim()).equalsIgnoreCase(searchValue));
        } catch (Exception e) {
            logger.error("List<WebElement> type invalid");
            throw new IllegalAccessException("List<WebElement> type invalid");
        }
        return wE = webElementList.get(randomNumber);
    }

    /**
     * Method used to move and select a dropdown option by text
     *
     * @param webElement contains the Element to select
     * @param value      to select from a dropdown
     * @param waitTime   time to wait for a WebElement
     * @throws Exception
     * @author J.Ruano
     */
    protected void selectAndMoveDropdownByOptionAttributeValue(WebElement webElement, String value, int waitTime) throws Exception {
        if (waitForElementVisibility(webElement, waitTime)) {
            selectAndMoveToDropDownByOptionValue(webElement, value);
        } else {
            logger.error("The Web Element was not found");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * This method is used to move and select a dropdown option by text
     *
     * @param webElement
     * @param value      to select from dropdown
     * @throws Exception
     * @author J.Ruano
     */
    protected void selectAndMoveToDropDownByOptionValue(WebElement webElement, String value) {
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
        } catch (Exception e) {
            logger.error("Element not clickable");
            logger.error(e.getMessage());
        }
    }

    /**
     * Use to generate a random number between a min and max value
     *
     * @param min contains the min value that can contains the randomNumber
     * @param max contains the max value that can contains the randomNumber
     * @return an integer value selected between the range min - max
     * @author J.Ruano
     */
    public int getRandomNumberByLimits(int min, int max) {
        return (int) (Math.floor(Math.random() * (1 + max - 1)));
    }
}