package stepDefinition.shareData;

import java.util.HashMap;

public class Interaction {
    private String channel;
    private String caseStatus;
    private String interactionNumber;

    public Interaction(HashMap<String, String> interactionDetails) {
        channel = interactionDetails.get("Channel");
        caseStatus = interactionDetails.get("CaseStatus");
    }

    public Interaction(String interactionNumber){
        this.interactionNumber = interactionNumber;
    }

    public String getCaseStatus() { return caseStatus; }
    public String getChannel() { return channel; }
    public String getInteractionNumber() { return interactionNumber; }
}