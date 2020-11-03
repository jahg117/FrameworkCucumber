package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteFirefoxDriverCreator extends WebDriverCreator {

    private String url;

    public RemoteFirefoxDriverCreator(String url){
        this.url = url;
    }

    @Override
    public WebDriver createWebDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL(url),new FirefoxOptions());
    }
}
