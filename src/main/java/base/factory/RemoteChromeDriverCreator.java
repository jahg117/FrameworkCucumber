package base.factory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteChromeDriverCreator extends WebDriverCreator {

    private String url;

    public RemoteChromeDriverCreator(String url){
        this.url = url;
    }

    @Override
    public WebDriver createWebDriver() throws MalformedURLException {
        url = "http://localhost/127.0.0.1:2030";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.LINUX);
        return new RemoteWebDriver(new URL(url), capabilities);
    }
}
