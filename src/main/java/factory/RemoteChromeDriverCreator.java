package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
        return new RemoteWebDriver(new URL(url),new ChromeOptions());
    }
}
