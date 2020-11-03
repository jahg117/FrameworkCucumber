package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;

public class Browser {
    private WebDriver driver;

    public BrowserType Type;

    public Browser (WebDriver driver)
    {
        this.driver = driver;
    }


}
