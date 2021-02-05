package stepDefinition.acessServices.accessServices;

import io.cucumber.java.en.And;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.CareTeamMember;
import stepDefinition.shareData.CommonData;

import java.util.HashMap;

public class SearchCareTeamMember extends ApplicationInstance {

    private CommonData commonData;
    public SearchCareTeamMember(CommonData commonData){ this.commonData = commonData; }

    @And("^I click on new care team member")
    public void clickNewCareTeamMember() throws Exception {
        accessServices.getProductEnrollmentPage().clickNewCareTeamMember();
    }

    @And("^I search a care team member by name")
    public void searchByName() throws Exception {
        accessServices.getCustomerLookupPage().searchRandomFirstName();
        HashMap<String, String> careTeamMemberDetails = accessServices.getCustomerLookupPage().getAndSelectCareTeamMemberDetails();
        commonData.careTeamMember = new CareTeamMember(careTeamMemberDetails);
    }

    @And("^I select a relationship dropdown option")
    public void selectRelationShip() {
        accessServices.getCustomerLookupPage().selectRelationShipOption();
    }

    @And("^I click on create care team member")
    public void clickCreateCareTeamMember() throws Exception {
        accessServices.getCustomerLookupPage().clickCreateCareTeamMember();
    }
}
