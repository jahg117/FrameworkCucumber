package base.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
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

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1920x1080");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "latest");
        caps.setCapability("os", "Windows");
        caps.setCapability("project", scenarioDetails.split(",")[0]);
        caps.setCapability("name", scenarioDetails.split(",")[1]); // test name
        caps.setCapability("build", "PEP Automation"); // CI/CD job or build name
        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        return new RemoteWebDriver(new URL(URL), caps);
    }
}
