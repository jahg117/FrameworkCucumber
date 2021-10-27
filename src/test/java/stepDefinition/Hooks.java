package stepDefinition;

import base.driverInitialize.DriverFactory;
import base.driverInitialize.SharedDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import runner.AbstractTestNGCucumberParallelTests;
import utils.FileReading;

public class Hooks {
    private WebDriver driver = null;
    private Logger logger = Logger.getLogger(Hooks.class);
    private String passed;
    private String failed;

    public Hooks() {
        FileReading fileReading = new FileReading();
        fileReading.setLog4jFile();
    }

    @Before
    public void Initialize(Scenario scenario) throws Exception {
        String featureName = getFeatureFileNameFromScenarioId(scenario);
        String browser = AbstractTestNGCucumberParallelTests.browser;
        FileReading fileReading = new FileReading();
        fileReading.setFileName("GlobalConfig.properties");
        passed = fileReading.getField("screenshotPass");
        failed = fileReading.getField("screenshotFail");
        SharedDriver df = new SharedDriver(browser, featureName + "," + scenario.getName());
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        logger.info("Scenario started: " + scenario.getName());
    }

    @AfterStep
    public void takeScreenShot(Scenario scenario) {
        if (!scenario.isFailed() && passed.equalsIgnoreCase("true") || scenario.isFailed() && failed.equalsIgnoreCase("true")) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }

    @After
    public void CloseDriver(Scenario scenario) {
        logger.info("Scenario completed: " + scenario.getName());
        DriverFactory.getDriver().quit();
        DriverFactory.removeDriver();
    }

    private String getFeatureFileNameFromScenarioId(Scenario scenario) {
        String[] rawFeatureName = scenario.getId().split("/");
        String featureName = rawFeatureName[rawFeatureName.length - 1].split(":")[0].replace("feature", "");
        return "Test suite: " + featureName;
    }
}
