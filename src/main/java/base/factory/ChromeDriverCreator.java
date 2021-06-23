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
        WebDriverManager.chromedriver().driverVersion("91.0.4472.114").setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation","load-extension"});
        options.addArguments("start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        if(System.getProperty("os.name").equalsIgnoreCase("linux")) {
            options.setHeadless(true);
        } else {
            options.setHeadless(Boolean.parseBoolean(fileReading.getField("headless")));
        }
        return new ChromeDriver(options);
    }
}
