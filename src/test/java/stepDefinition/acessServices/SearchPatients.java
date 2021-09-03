package stepDefinition.acessServices;

import io.cucumber.java.en.And;
import pageObject.ApplicationInstance;

public class SearchPatients extends ApplicationInstance {
    @And("^^I search the patient \"([^\"]*)\" from the search bar$$")
    public void clickMenuOption(String menuOption) throws Exception {
        accessServices.getAccessServicesHomePage().searchPatient(menuOption);
    }
}
