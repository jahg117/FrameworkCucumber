package base.factory;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.FileReading;

public class ChromeDriverCreator extends WebDriverCreator{

    @Override
    public WebDriver createWebDriver() {
        FileReading fileReading = new FileReading();
        fileReading.setFileName("GlobalConfig.properties");
        WebDriverManager.chromedriver().driverVersion("89.0.4389.114").setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation","load-extension"});
        options.addArguments("start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        //options.addArguments("--remote-debugging-port=9222");
        options.setHeadless(true);
        return new ChromeDriver(options);
    }
}
