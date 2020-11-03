package base;

public class BasePage extends Base{

    public <TPage extends BasePage> TPage As(Class<TPage> pageInstance)
    {
        try
        {
            return (TPage)this;
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
        return null;
    }
}
