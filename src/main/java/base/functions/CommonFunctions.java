package base.functions;

import base.driverInitialize.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FileReading;

import java.time.Duration;

public class CommonFunctions {

    private WebDriver webDriver = DriverFactory.getDriver();

    public FileReading fileReading = new FileReading();

    private FluentWait<WebDriver> getWebDriverWait(int timeOutInSeconds, int pollingEvery){
        FluentWait<WebDriver> wait = new FluentWait<>(webDriver);
        wait.withTimeout(Duration.ofSeconds(timeOutInSeconds));
        wait.pollingEvery(Duration.ofMillis(250));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(TimeoutException.class);
        wait.ignoring(StaleElementReferenceException.class);
        return wait;
    }

    public boolean waitForElementClickable(WebElement element, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return false;
        }

    }

    public boolean waitForElementVisibility(WebElement element, int timeOutInSeconds){
        try{
            WebDriverWait wait= new WebDriverWait(webDriver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return false;
        }

    }

    public void waitForPageToLoad(int timeOutInSeconds){
        WebDriverWait wait= new WebDriverWait(webDriver, 30);
        JavascriptExecutor jsExecutor = (JavascriptExecutor)webDriver;

        ExpectedCondition<Boolean> jsLoad = webDriver ->  ((JavascriptExecutor)webDriver)
                .executeScript("return document.readyState").toString().equals("complete");

        //Get JS Ready
        boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");

        if(!jsReady)
            wait.until(jsLoad);
        //else
        //Settings.Logs.Write("Page is ready !");

    }
}
