package base;

import factory.threadsafe.CurrentWebDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.functions.Wrapper;

public class Base extends Wrapper {
    public static BasePage CurrentPage;

    public Base() {
        super(CurrentWebDriver.getInstance().getWebDriver());
    }

    public static <TPage extends BasePage> TPage GetInstance(Class<TPage> page)
    {
        Object obj = PageFactory.initElements(CurrentWebDriver.getInstance().getWebDriver(), page);
        return page.cast(obj);
    }
}
