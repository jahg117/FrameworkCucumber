package base.factory;

import base.functions.CommonFunctions;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.FileReading;
import utils.Values;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BrowserstackDriverCreator {

    public WebDriver createWebDriver(String scenarioDetails) throws MalformedURLException {
        List<String> browserStackUserCredentials = new ArrayList<>();
        String browserUser = "";
        FileReading fileReading = new FileReading();
        fileReading.setFileName("GlobalConfig.properties");
        browserUser = fileReading.getField("browserUser");
        if (browserUser.isEmpty()){
            browserUser = Values.BROWSERSTACK_DEFAULTUSER;
        }
        browserStackUserCredentials = getBrowserStackLogin(fileReading.getField("browserUser"));
        String URL = "https://" + browserStackUserCredentials.get(1) + ":" + browserStackUserCredentials.get(2) + "@hub-cloud.browserstack.com/wd/hub";
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

    public List<String> getBrowserStackLogin(String browserUser) {
        List<String> browserStackCredentias = new ArrayList<>();
        for (int i = 0; i < Values.ARRAY_BROWSERSTACKACCOUNTS.length; i++) {
            if (Values.ARRAY_BROWSERSTACKACCOUNTS[i].contains(browserUser.trim())) {
                browserStackCredentias = List.of(Values.ARRAY_BROWSERSTACKACCOUNTS[i].split(Values.REGEX_COMMA));
                break;
            }
        }
        return browserStackCredentias;
    }
}
