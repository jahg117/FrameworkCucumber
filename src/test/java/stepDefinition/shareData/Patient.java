package stepDefinition.shareData;

import java.util.HashMap;

public class Patient {
    private String patient;
    private String address;
    private String city;
    private String phoneNumber;
    private String date;

    public Patient(HashMap <String, String> patientDetails){
        patient = patientDetails.get("firstName") + " " + patientDetails.get("lastName");
        address = patientDetails.get("address");
        city = patientDetails.get("city");
        phoneNumber = patientDetails.get("phoneNumber");
        date = patientDetails.get("date");
    }

    public String getPatientName() { return patient; }

    public String getAddress() { return address; }

    public String getCity() { return city; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getDate() { return date; }
}
