package base.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.FileReading;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriverCreator {

    public WebDriver createWebDriver(String scenarioDetails) throws MalformedURLException {
        FileReading fileReading = new FileReading();
        fileReading.setFileName("GlobalConfig.properties");
        String URL = "https://" + fileReading.getField("AUTOMATE_USERNAME") + ":" + fileReading.getField("AUTOMATE_ACCESS_KEY") + "@hub-cloud.browserstack.com/wd/hub";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1920x1080");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "latest");
        caps.setCapability("os", "Windows");
        caps.setCapability("name", scenarioDetails.split(",")[1]); // test name
        caps.setCapability("build", scenarioDetails.split(",")[0]); // CI/CD job or build name

        return new RemoteWebDriver(new URL(URL), caps);
    }
}
