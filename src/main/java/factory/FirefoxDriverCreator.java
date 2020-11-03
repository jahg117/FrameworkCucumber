package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverCreator extends WebDriverCreator {

    @Override
    public WebDriver createWebDriver() {
        System.setProperty("webdriver.gecko.driver","resource/geckodriver.exe");
        return new FirefoxDriver();
    }
}
