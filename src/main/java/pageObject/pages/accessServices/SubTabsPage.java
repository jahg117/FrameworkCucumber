package pageObject.pages.accessServices;

import base.functions.CommonFunctions;

public class SubTabsPage extends CommonFunctions {

    public void switchSubTab(int tab) throws Exception {
        switchSubTabByIndexSF(tab, 10);
    }

    public void closeSubTab(int tab) throws Exception {
        closeSubTabByIndexSF(tab, 10);
    }

    public void closeLastSubTab() throws Exception {
        closeLastSubTabSF(10);
    }
}
