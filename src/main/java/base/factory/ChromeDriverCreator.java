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
        WebDriverManager.chromedriver().driverVersion("89.0.4389.23").setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation","load-extension"});
        options.addArguments("start-maximized");
        options.addArguments("--disable-notifications");
        options.setHeadless(Boolean.parseBoolean(fileReading.getField("headless")));
        return new ChromeDriver(options);
    }
}
