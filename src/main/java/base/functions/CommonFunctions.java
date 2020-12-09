package base.functions;

import base.driverInitialize.DriverFactory;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;														 
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;												
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.plugin.viewer.LifeCycleManager;										  
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
	
    /**
     * @author J.Ruano
     * @apiNote Return true if a WebElement is presence on the Dom not necessarily visible
     * @param locator it contains the locator (path) to search the element
     * @param timeOutInSeconds Seconds to wait for the WebElement.
     * @return
     */
    public boolean waitForElementPresence(By locator, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return false;
        }
    }

    /**
     * @author J.Ruano
     * @apiNote method use to click an element and if there is an "ElementClickInterceptedException" it will click again
     * with the Actions Class and if theres again the same exception it will use the JS method.
     * @param wElement contains the Element to do click
     * @return returns true if the click was done successfully
     * @throws Exception
     */
    public boolean clickMethod(WebElement wElement) throws Exception {
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
     * @author J.Ruano
     * @apiNote This method will scroll to the Element using the scroll into view at Top of the element With JS
     * @param wElement It contains the WebElement
     * @throws Exception
     */
    public boolean scrollMethodToWebElement(WebElement wElement) throws Exception {
        //Arguments to get into the middle of the WebElement, using as arguments in the java script
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        //===========================================================================
        JavascriptExecutor jsExecutor = (JavascriptExecutor)webDriver;
        try{
            //logger.info("USING " + usedMethod + " TO SCROLL TO AN ELEMENT");
            jsExecutor.executeScript(scrollElementIntoMiddle,wElement);
            return waitForElementVisibility(wElement, 10);
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return false;
        }
    }

    /**
     * @author J.Ruano
     * @apiNote use to scroll into the page Up or Down using amount of pixels
     * @param scrollDirection can be Top or Bottom of the page
     * @param pixels Is an integer that contains the amount of pixels to scroll up or down when "up" or "down" word are use in the "scrollDirection"
     * @throws Exception
     */
    public boolean scrollMethodByPixels(String scrollDirection,int pixels) throws Exception {
        JavascriptExecutor jsExecutor = (JavascriptExecutor)webDriver;
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
    public boolean scrollToElementByCoordinates(WebElement wElement) throws Exception {
        Point point = wElement.getLocation();
        int x_coordinate = point.getX();
        int y_coordinate = point.getY();
        JavascriptExecutor jsExecutor = (JavascriptExecutor)webDriver;
        jsExecutor.executeScript("window.scrollBy(" + x_coordinate + ", " + y_coordinate + ");");
        return waitForElementVisibility(wElement,10);
    }

    /**
     * @author J.Ruano
     * @apiNote This method contains all the methods to scroll to TOP or to BOTTOM of the page
     * @param topBottom it requires to put "top" or "bottom" to scroll to those directions
     * @throws Exception
     */
    public boolean scrollMethodTopBottom(String topBottom) throws Exception {
        //===========================================================================
        boolean statusOperation = false;
        JavascriptExecutor jsExecutor = (JavascriptExecutor)webDriver;
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
     * @author J.Ruano
     * @apiNote it used to move to an element by Action Class
     * @param wElement it contains the WebElement To Move
     * @throws Exception
     */
    public boolean scrollMethodToWebElementByActions(WebElement wElement) throws Exception {
        Actions actions = new Actions(webDriver);
        try {
            actions.moveToElement(wElement).build().perform();
            return waitForElementVisibility(wElement, 10);
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    /**
     * @author J.Ruano
     * @apiNote click to an element with the Actions Class
     * @param wElement contains the Element to do click
     * @return returns true if the click was done successfully
     * @throws Exception
     */
    public boolean clickElementActions(WebElement wElement) throws Exception {
        boolean statusOperation = false;
        Actions actions = new Actions(webDriver);
        try {
            actions.click(wElement).build().perform();
            statusOperation = true;
        } catch (ElementClickInterceptedException e) {
            statusOperation = clickElementJS(wElement);
        }
        return statusOperation;
    }

    /**
     * @author J.Ruano
     * @apiNote click to an element with JavaScript
     * @param wElement contains the Element to do click
     * @return returns true if the click was done successfully
     * @throws Exception
     */
    public boolean clickElementJS(WebElement wElement) throws Exception {
        JavascriptExecutor jsExecutor = (JavascriptExecutor)webDriver;
        jsExecutor.executeScript("arguments[0].click();", wElement);
        return true;
    }
}