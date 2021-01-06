package stepDefinition.acessServices;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.application.AccessServices;

public class NewAccountSteps {
    AccessServices accessServices = new AccessServices();

    @Given("^I click on new Account$")
    public void clickNewAccount() {
        accessServices.getAccessServicesHomePage().isAccessServicesTitleVisible();
    }

    @When("^I click on new and I select \"([^\"]*)\" account$")
    public void selectAccountType(String accountType) {

    }

    @Then("^I fill the mandatory fields from the account form$")
    public void mandatoryFieldsAccountForm() {

    }
}
