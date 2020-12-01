package stepDefinition;

import base.DriverFactory;
import base.SharedDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before
    public void Initialize(Scenario scenario) throws Exception {
        SharedDriver df = new SharedDriver();
        DriverFactory.getDriver().manage().window().maximize();
    }

    @After
    public void CloseDriver(Scenario scenario){
        if(scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "");
        }
        DriverFactory.getDriver().quit();
        DriverFactory.removeDriver();
    }
}
