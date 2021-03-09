package stepDefinition.shareData;

import java.util.HashMap;

public class GlobalShareData {
    private String executionFlag;
    private String randomSelectionFlag;

    public GlobalShareData(HashMap<String, String> globalShareDataDetails) {
        executionFlag = globalShareDataDetails.get("executionFlag");
        randomSelectionFlag = globalShareDataDetails.get("randomSelectionFlag");
    }

    public String getExecutionFlag() {
        return executionFlag;
    }

    public String getRandomSelectionFlag() { return randomSelectionFlag; }
}





