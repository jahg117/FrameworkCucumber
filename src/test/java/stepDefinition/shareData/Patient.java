package stepDefinition.shareData;

import java.util.HashMap;

public class Patient {
    private String patient;
    private String address;
    private String city;
    private String phoneNumber;
    private String date;
    private String pepID;

    private String patientPDC;
    private String addressPDC;
    private String cityPDC;
    private String firstPhoneFax;
    private String secondPhoneFax;
    private String datePDC;

    public Patient(HashMap <String, String> patientDetails){
        patient = patientDetails.get("firstName") + " " + patientDetails.get("lastName");
        address = patientDetails.get("address");
        city = patientDetails.get("city");
        phoneNumber = patientDetails.get("phoneNumber");
        date = patientDetails.get("date");
        patientPDC = patientDetails.get("fName") + " " + patientDetails.get("lName");
        addressPDC = patientDetails.get("address");
        cityPDC = patientDetails.get("city");
        firstPhoneFax = patientDetails.get("phoneNumber");
        secondPhoneFax = patientDetails.get("faxNumber");
        datePDC = patientDetails.get("date");
    }

    public Patient(String pepID) {
        this.pepID = pepID;
    }

    public String getPatientName() { return patient; }

    public String getAddress() { return address; }

    public String getCity() { return city; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getDate() { return date; }

    public String getPatientNamePDC() { return patientPDC; }

    public String getAddressPDC() { return addressPDC; }

    public String getCityPDC() { return cityPDC; }

    public String getFirstPhoneFax() { return firstPhoneFax; }

    public String getSecondPhoneFaxPhoneFax() { return secondPhoneFax; }

    public String getDatePDC() { return datePDC; }





    public String getPepID() { return pepID; }
}
