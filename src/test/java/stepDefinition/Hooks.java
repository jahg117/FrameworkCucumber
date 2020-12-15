package stepDefinition;

import base.driverInitialize.DriverFactory;
import base.driverInitialize.SharedDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import runner.AbstractTestNGCucumberParallelTests;
import utils.FileReading;

public class Hooks {
    private FileReading fileReading = new FileReading();
    private WebDriver driver = DriverFactory.getDriver();

    private Logger logger = Logger.getLogger(Hooks.class);

    public Hooks() {
        fileReading.setLog4jFile();
    }

    @Before
    public void Initialize(Scenario scenario) throws Exception {
        String featureName = getFeatureFileNameFromScenarioId(scenario);
        String browser = AbstractTestNGCucumberParallelTests.browser;
        SharedDriver df = new SharedDriver(browser,featureName+","+scenario.getName());
        logger.info("Scenario started: "+scenario.getName());
    }

    private String getFeatureFileNameFromScenarioId(Scenario scenario) {
        String []rawFeatureName = scenario.getId().split("/");
        String featureName = rawFeatureName[rawFeatureName.length-1].split(":")[0].replace("feature","");
        return "Test suite: "+featureName;
    }

    @After
    public void CloseDriver(Scenario scenario){
        if(scenario.isFailed()) {
            logger.error("Scenario failed: "+scenario.getName());
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "");
        }
        logger.info("Scenario completed: "+scenario.getName());
        DriverFactory.getDriver().quit();
        DriverFactory.removeDriver();
    }
}
