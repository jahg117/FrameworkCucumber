package base.factory;

import org.openqa.selenium.WebDriver;
import utils.FileReading;

public class WebDriverFactory {
    public WebDriver getDriver(String browser, String scenarioDetails, String seleniumGridUrl) throws Exception {
        FileReading fileReading = new FileReading();
        fileReading.setFileName("GlobalConfig.properties");
        if(fileReading.getField("browserStack").equalsIgnoreCase("true")) {
            BrowserstackDriverCreator browserstackDriverCreator = new BrowserstackDriverCreator();
            return browserstackDriverCreator.createWebDriver(scenarioDetails);
        }else {
            switch (browser) {
                case "chrome":
                    ChromeDriverCreator chromeDriverCreator = new ChromeDriverCreator();
                    return chromeDriverCreator.createWebDriver();
                case "firefox":
                    FirefoxDriverCreator firefoxDriverCreator = new FirefoxDriverCreator();
                    return firefoxDriverCreator.createWebDriver();
                case "chrome-remote":
                    RemoteChromeDriverCreator remoteChromeDriverCreator = new RemoteChromeDriverCreator(seleniumGridUrl);
                    return remoteChromeDriverCreator.createWebDriver();
                case "firefox-remote":
                    RemoteFirefoxDriverCreator remoteFirefoxDriverCreator = new RemoteFirefoxDriverCreator(seleniumGridUrl);
                    return remoteFirefoxDriverCreator.createWebDriver();
                default:
                    throw new Exception("Browser " + browser + " don't supported");
            }
        }
    }
}
