package runner;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/java/features"},
        glue = {"stepDefinition"},
        plugin = {"json:target/cucumber/cucumber.json",
                "json:target/cucumber-json.json", "summary",
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/rerun.txt"},
        publish = true,
        tags = "@smokeScenarios")

public class RunCucumberTest extends AbstractTestNGCucumberParallelTests {

}