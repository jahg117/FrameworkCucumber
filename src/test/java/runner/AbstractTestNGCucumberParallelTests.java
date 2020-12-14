package runner;

import base.driverInitialize.DriverFactory;
import base.driverInitialize.SharedDriver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.*;
import utils.CucumberReport;
import utils.FileReading;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractTestNGCucumberParallelTests extends AbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;
    private FileReading fileReading = new FileReading();

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser","headless"})
    public void setUpDriver(String browser, boolean headless) throws Exception {
        SharedDriver df = new SharedDriver(browser, headless);
    }

    @Test(dataProvider = "features", priority = 0)
    public void runTests(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @DataProvider(name = "features", parallel = false)
    public Object[][] features() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        DriverFactory.getDriver().quit();
        DriverFactory.removeDriver();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        testNGCucumberRunner.finish();
    }

    @AfterSuite
    public void generateReport() {
        CucumberReport cucumberReport = new CucumberReport();
    }
}
