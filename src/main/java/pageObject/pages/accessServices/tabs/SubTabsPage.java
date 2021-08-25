package pageObject.pages.accessServices.tabs;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import utils.FileReading;
import utils.Values;

import java.lang.reflect.Method;


public class SubTabsPage extends CommonFunctions {

    protected FileReading fileReading = new FileReading();
    private final Logger logger = Logger.getLogger(CommonFunctions.class);
    public static int maxNumberOfTries = 0;

    Class<?> myClass;

    {
        try {
            fileReading.setLog4jFile();
            fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);
            maxNumberOfTries = Integer.parseInt(fileReading.getField(Values.TXT_RETRYWHILE));
            myClass = Class.forName(Values.REFLECTION_COMMONFUNCTIONSCLASSPATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void switchSubTab(int tab) throws Exception {
        switchSubTabByIndexSF(tab, mediumWait());
    }


    public void closeSubTab(int tab) throws Exception {
        closeSubTabByIndexSF(tab, mediumWait());
    }

    public void closeLastSubTab() throws Exception {
        closeLastSubTabSF(5);
    }
}
