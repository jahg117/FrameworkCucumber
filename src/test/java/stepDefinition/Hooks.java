package stepDefinition;

import factory.WebDriverFactory;
import factory.threadsafe.CurrentWebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.FileReading;

public class Hooks {
    @Before
    public void Initialize(Scenario scenario) throws Exception {
        FileReading file = new FileReading();
        CurrentWebDriver.getInstance().setWebDriver(WebDriverFactory.getDriver("chrome", ""));
        CurrentWebDriver.getInstance().getWebDriver().get(file.getField("URL"));
        CurrentWebDriver.getInstance().getWebDriver().manage().window().maximize();
    }

    @After
    public void CloseDriver(Scenario scenario){
        if(scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) CurrentWebDriver.getInstance().getWebDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "");
        }
        if(CurrentWebDriver.getInstance().getWebDriver() != null){
            CurrentWebDriver.getInstance().getWebDriver().quit();
            CurrentWebDriver.getInstance().removeWebDriver();
        }
    }
}
