package stepDefinition.shareData;

import java.util.HashMap;

public class Interaction {
    private String channel;
    private String caseStatus;

    public Interaction(HashMap<String, String> interactionDetails) {
        channel = interactionDetails.get("Channel");
        caseStatus = interactionDetails.get("CaseStatus");
    }

    public String getCaseStatus() { return caseStatus; }
    public String getChannel() { return channel; }
}