package base.functions;

import base.driverInitialize.DriverFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;												
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.FileReading;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class CommonFunctions {

    private WebDriver driver = DriverFactory.getDriver();

    protected FileReading fileReading = new FileReading();

    private Logger logger = Logger.getLogger(CommonFunctions.class);

    public CommonFunctions() {
        fileReading.setLog4jFile();
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
     * @throws null if the webElement is empty, null or the string has a different format.
     */
    private String getWebElementLocatorPath(List<WebElement> webElement){
        try{
            return webElement.toString().split("-> ")[1].replace("]","");
        }catch(Exception e){
            return webElement.toString().split("DefaultElementLocator")[1].replace("'","");
        }
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
            wait.withTimeout(Duration.ofSeconds(timeOutInMinutes));
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
     * @param element WebElement to find.
     * @param timeOutInSeconds Seconds to wait for a WebElement.
     * @return boolean
     */
    protected boolean waitForElementVisibility(WebElement element, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("Element found "+getWebElementLocatorPath(element));
            return true;
        }catch (Exception e){
            logger.warn("Element was not found "+getWebElementLocatorPath(element));
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
            logger.warn("List of web elements is not visible "+getWebElementLocatorPath(elements));
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
            logger.warn("Element found "+getWebElementLocatorPath(element));
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
            logger.warn("WebElement "+getWebElementLocatorPath(element)+" with text in attribute "+textElementValue+" is displayed");
            return true;
        }catch (Exception e){
            logger.warn("WebElement "+getWebElementLocatorPath(element)+" with text in attribute "+textElementValue+" is not displayed");
            return false;
        }
    }

    protected void waitForPageToLoad(int timeOutInSeconds){
        WebDriverWait wait= new WebDriverWait(driver, 30);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jsLoad = webDriver ->  ((JavascriptExecutor)webDriver)
                .executeScript("return document.readyState").toString().equals("complete");

        boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");

        if(!jsReady) {
            wait.until(jsLoad);
        }else {
            logger.info("Page is ready !");
        }
    }
	
    /**
     * @author J.Ruano
     * @apiNote Return true if a WebElement is presence on the Dom not necessarily visible
     * @param locator it contains the locator (path) to search the element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    protected boolean waitForElementPresence(By locator, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return false;
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
            Actions actions = new Actions(driver);
            actions.click(webElement).build().perform();
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
            Actions actions = new Actions(driver);
            actions.click(webElement).build().perform();
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
    protected void doubleClickToElementVisible(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            Actions actions = new Actions(driver);
            actions.doubleClick(webElement).build().perform();
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
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).doubleClick(webElement).build().perform();
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
    protected void doubleClickToElementClickable(WebElement webElement, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).doubleClick(webElement).build().perform();
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
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).doubleClick(webElement).build().perform();
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
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).click(webElement).build().perform();
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
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).click(webElement).build().perform();
            logger.info("WebElement clicked");
        }else{
            logger.error("The Web Element is not clickable");
            throw new NoSuchElementException("Element not clickable");
        }
    }

    /**
     * Method used to click an element and if there is an "ElementClickInterceptedException" it will click again
     *
     * @author J.Ruano
     * @param wElement contains the Element to do click
     * @return returns true if the click was done successfully
     * @throws Exception
     */
    protected boolean clickMethod(WebElement wElement) throws Exception {
        boolean statusOperation = scrollToElementByCoordinates(wElement);
        if (!statusOperation) {
            statusOperation = scrollMethodToWebElement(wElement);
            if (!statusOperation) {
                statusOperation = scrollMethodToWebElementByActions(wElement);
                if (!statusOperation) {
                    //logger.info("NONE OF THE METHODS DID WORK TO SCROLL DOWN USING PIXELS");
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
     * @author J.Ruano
     * @param wElement It contains the WebElement
     * @throws Exception
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
     * @author J.Ruano
     * @param scrollDirection can be Top or Bottom of the page
     * @param pixels Is an integer that contains the amount of pixels to scroll up or down when "up" or "down" word are use in the "scrollDirection"
     * @throws Exception
     */
    protected boolean scrollMethodByPixels(String scrollDirection,int pixels) throws Exception {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String strPixels = String.valueOf(pixels);
        boolean statusOperation = false;
        double startPositionY = (double) jsExecutor.executeScript("return window.pageYOffset;");
        double endPositionY = 0;
        try{
            switch (scrollDirection.toLowerCase().trim()) {
                case "up":
                    //logger.info("USING " + usedMethod + " TO SCROLL DOWN USING PIXELS");
                    jsExecutor.executeScript("window.scrollTo(0,"+ strPixels +")");
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
            }else{
                statusOperation = false;
            }
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return false;
        }
        return statusOperation;
    }

    /**
     * @author J.Ruano
     * @param wElement contains the Element to get the coordinates X,Y and scroll base on coordinates
     * @throws Exception
     */
    protected boolean scrollToElementByCoordinates(WebElement wElement) throws Exception {
        Point point = wElement.getLocation();
        int x_coordinate = point.getX();
        int y_coordinate = point.getY();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(" + x_coordinate + ", " + y_coordinate + ");");
        return waitForElementVisibility(wElement,10);
    }

    /**
     * This method contains all the methods to scroll to TOP or to BOTTOM of the page
     *
     * @author J.Ruano
	 * @param topBottom it requires to put "top" or "bottom" to scroll to those directions
     * @throws Exception
     */
    protected boolean scrollMethodTopBottom(String topBottom) throws Exception {
        //===========================================================================
        boolean statusOperation = false;
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        double startPositionY = (double) jsExecutor.executeScript("return window.pageYOffset;");
        double endPositionY = 0;
        try{
            switch (topBottom.toLowerCase().trim()) {
                case "top":
                    //logger.info("USING " + usedMethod + " TO SCROLL DOWN USING PIXELS");
                    jsExecutor.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
                    endPositionY = (double) jsExecutor.executeScript("return window.pageYOffset;");
                    break;
                case "bottom":
                    //logger.info("USING " + usedMethod + " TO SCROLL UP USING PIXELS");
                    jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                    endPositionY = (double) jsExecutor.executeScript("return window.pageYOffset;");
                    break;
            }
            if (endPositionY != startPositionY) {
                statusOperation = true;
            }else{
                statusOperation = false;
            }
        }catch (NoSuchElementException | StaleElementReferenceException e){
                return false;
        }
        return statusOperation;
    }
    /**
     * This method is used to move to an element by Action Class
     *
     * @author J.Ruano
	 * @param wElement it contains the WebElement To Move
     * @throws Exception
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
     * Click to an element with the Actions Class
     *
     * @author J.Ruano
     * @param wElement contains the Element to do click
     * @return returns true if the click was done successfully
     * @throws Exception
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
     * @author J.Ruano
     * @param wElement contains the Element to do click
     * @return returns true if the click was done successfully
     * @throws Exception
     */
    protected boolean clickElementJS(WebElement wElement) throws Exception {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", wElement);
        return true;
    }
    /**
     * Method used to sendkeys, move and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void sendkeysAndMoveToElementVisible(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).sendKeys(webElement,text).build().perform();
            logger.info("The keys were sent");
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
    /**
     * Method used to sendkeys, move and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void sendkeysAndMoveToElementClickable(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).sendKeys(webElement,text).build().perform();
            logger.info("The keys were sent");
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to sendkeys and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void sendkeysElementVisible(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementVisibility(webElement, waitTime)){
            Actions actions = new Actions(driver);
            actions.sendKeys(webElement,text).build().perform();
            logger.info("The keys were sent");
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }

    /**
     * Method used to sendkeys and wait for a visible WebElement
     *
     * @author Alejandro Hernandez
     * @param webElement contains the Element to select
     * @param waitTime time to wait for a WebElement
     * @throws Exception
     */
    protected void sendkeysElementClickable(WebElement webElement, String text, int waitTime) throws Exception {
        if(waitForElementClickable(webElement, waitTime)){
            Actions actions = new Actions(driver);
            actions.sendKeys(webElement,text).build().perform();
            logger.info("The keys were sent");
        }else{
            logger.error("The Web Element was not found or it is not an input type");
            throw new NoSuchElementException("Element not valid");
        }
    }
}