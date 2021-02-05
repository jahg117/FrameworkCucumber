package stepDefinition.shareData;

import java.util.HashMap;

public class CareTeamMember {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
    private String city;
    private String state;
    private String zipcode;

    public CareTeamMember(HashMap<String, String> careTeamMemberDetails){
        firstName = careTeamMemberDetails.get("First Name");
        lastName = careTeamMemberDetails.get("Last Name");
        address = careTeamMemberDetails.get("Address");
        email = careTeamMemberDetails.get("Email");
        phone = careTeamMemberDetails.get("Phone");
        city = careTeamMemberDetails.get("City");
        state = careTeamMemberDetails.get("State");
        zipcode = careTeamMemberDetails.get("Zipcode");
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getAddress() { return address; }

    public String getEmail() { return email; }

    public String getPhone() { return phone; }

    public String getCity() { return city; }

    public String getState() { return state; }

    public String getZipcode() { return zipcode; }
}
