package factory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverCreator extends WebDriverCreator {

    @Override
    public WebDriver createWebDriver() {
        System.setProperty("webdriver.chrome.driver","resource/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation","load-extension"});

        return new ChromeDriver(options);
    }
}
