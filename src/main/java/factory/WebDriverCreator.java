package factory;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public abstract class WebDriverCreator {

    public abstract WebDriver createWebDriver() throws MalformedURLException;
}
