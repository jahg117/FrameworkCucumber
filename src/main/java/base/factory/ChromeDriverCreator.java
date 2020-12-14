package base.factory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverCreator {

    public WebDriver createWebDriver(boolean headless) {
        System.setProperty("webdriver.chrome.driver","resource/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation","load-extension"});
        options.addArguments("start-maximized");
        options.setHeadless(headless);
        return new ChromeDriver(options);
    }
}
