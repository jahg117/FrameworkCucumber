package runner;

import base.functions.CommonFunctions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.*;
import utils.CucumberReport;
import utils.FileReading;
import utils.SendEmail;
import utils.Values;

import javax.mail.MessagingException;

public abstract class AbstractTestNGCucumberParallelTests extends AbstractTestNGCucumberTests {
    int reportCounter = 0;
    private TestNGCucumberRunner testNGCucumberRunner;
    private FileReading fileReading = new FileReading();
    private SendEmail sendEmail = new SendEmail();
    private CommonFunctions commonFunctions = new CommonFunctions();
    public static String browser = "";

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUpDriver(String browser) throws Exception {
        this.browser = browser;
    }

    @Test(dataProvider = "features", priority = 0)
    public void runTests(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @DataProvider(name = "features", parallel = false)
    public Object[][] features() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        testNGCucumberRunner.finish();
    }

    @AfterSuite
    public void generateReport() throws Exception {
        CucumberReport cucumberReport = new CucumberReport();
        fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);
        if (commonFunctions.searchAFile(Values.EMAIL_PATHPDF, Values.EMAIL_EXTENTPDF) &&
                fileReading.getField("sendReport").trim().equalsIgnoreCase(Values.TXT_VALTRUE) && reportCounter == 0) {
            sendEmail.emailAttachment(Values.EMAIL_PATHPDF, Values.EMAIL_EXTENTPDF);
            reportCounter++;
        }
    }
}
