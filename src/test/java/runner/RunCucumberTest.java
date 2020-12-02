package runner;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/java/features"},
        glue = {"stepDefinition"},
        plugin = {"json:target/cucumber/cucumber.json",
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/rerun.txt"},
        publish = true)

public class RunCucumberTest extends AbstractTestNGCucumberParallelTests {

}