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
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation","load-extension"});
        options.addArguments("start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
        //options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
        //options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
        //options.addArguments("--headless"); // only if you are ACTUALLY running headless
        //options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770 Use to disable "Chrome is being controlled by automated test software"
        //options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
        if(System.getProperty("os.name").equalsIgnoreCase("linux")) {
            options.setHeadless(true);
        } else {
            options.setHeadless(Boolean.parseBoolean(fileReading.getField("headless")));
        }
        return new ChromeDriver(options);
    }
}
