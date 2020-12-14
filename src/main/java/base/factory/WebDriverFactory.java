package base.factory;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
    public WebDriver getDriver(String browser, boolean headless,String seleniumGridUrl) throws Exception {
        switch (browser){
            case "browserstack":
                BrowserstackDriverCreator browserstackDriverCreator = new BrowserstackDriverCreator();
                return browserstackDriverCreator.createWebDriver();
            case "chrome":
                ChromeDriverCreator chromeDriverCreator = new ChromeDriverCreator();
                return chromeDriverCreator.createWebDriver(headless);
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
