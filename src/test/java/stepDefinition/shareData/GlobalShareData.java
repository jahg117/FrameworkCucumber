package stepDefinition.shareData;

import java.util.HashMap;

public class GlobalShareData {
    private String executionFlag;
    private String recordData;
    private String randomSelectionFlag;

    public GlobalShareData(HashMap<String, String> globalShareDataDetails) {
        executionFlag = globalShareDataDetails.get("executionFlag");
        recordData = globalShareDataDetails.get("recordData");
        randomSelectionFlag = globalShareDataDetails.get("randomSelectionFlag");
    }

    public String getExecutionFlag() {
        return executionFlag;
    }
    public String getRandomSelectionFlag() { return randomSelectionFlag; }
    public GlobalShareData(String recordData) {
        this.recordData = recordData;
    }
    public String getRecordData() { return recordData; }

}

