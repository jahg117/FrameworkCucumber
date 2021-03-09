package stepDefinition.shareData;

import java.util.HashMap;

public class Case {
    private String channel;
    private String caseStatus;
    private String caseSubType;
    private String discussTopic;
    private String cardNumber;

    public Case(HashMap<String, String> caseDetails){
        channel = caseDetails.get("Channel");
        caseStatus = caseDetails.get("CaseStatus");
        caseSubType = caseDetails.get("CaseSubType");
        discussTopic = caseDetails.get("DiscussTopic");
        cardNumber = caseDetails.get("CardNumber");
    }

    public String getChannel() { return channel; }
    public String getCaseStatus() { return caseStatus; }
    public String getCaseSubType() { return caseSubType; }
    public String getDiscussTopic() { return discussTopic; }
    public String getCardNumber() { return cardNumber; }
}
