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
            myClass = Class.forName("base.functions" + "." + "CommonFunctions");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean switchSubTab(int tab) throws Exception {
        boolean statusOperation = false;
        try {
            statusOperation = switchSubTabByIndexSF(tab, mediumWait());
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("switchSubTab")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "switchSubTab");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), tab);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    public boolean closeSubTab(int tab) throws Exception {
        boolean statusOperation = false;
        try {
            statusOperation = closeSubTabByIndexSF(tab, mediumWait());
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("closeSubTab")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "closeSubTab");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), tab);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }

    public boolean closeLastSubTab() throws Exception {
        boolean statusOperation = false;
        try {
            statusOperation = closeLastSubTabSF(mediumWait());
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("closeLastSubTab")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "closeLastSubTab");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }
}
