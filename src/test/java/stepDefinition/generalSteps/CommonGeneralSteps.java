package stepDefinition.generalSteps;

import base.functions.CommonFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.log4j.Logger;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.CommonData;
import stepDefinition.shareData.GlobalShareData;
import stepDefinition.shareData.Patient;
import stepDefinition.shareData.ProductEnrollment;
import utils.Values;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonGeneralSteps extends ApplicationInstance {
    private Logger logger = Logger.getLogger(CommonFunctions.class);
    private CommonData commonData;
    private String product;

    public CommonGeneralSteps(CommonData commonData) {
        this.commonData = commonData;
    }

    HashMap<String, String> globalShareDataList = new HashMap<>();

    /**
     * Used to assign the value of stepsToExecute to the executionFlag that will help to know if it is necessary to execute or not any step defined at Step definition
     *
     * @param stepsToExecute contains the value, that will be used as boolean, i.e. if it is come "", empty or with the value og "N_A" it will not execute an specific step
     */
    @Given("{string} it selects which steps will be execute using it from CommonGeneralSteps")
    public void selectStepsToExecute(String stepsToExecute) {
        globalShareDataList.put("executionFlag", stepsToExecute);
        commonData.globalShareData = new GlobalShareData(globalShareDataList);
    }

    @Given("{string} I verify if random selection is required using it from CommonGeneralSteps")
    public void itRequireRNDSelection(String randomSelection) {
        globalShareDataList.put("randomSelectionFlag", randomSelection);
        commonData.globalShareData = new GlobalShareData(globalShareDataList);
    }

    @Given("The {string} i select the View to filter using it from CommonGeneralSteps")
    public void selectProductView(String filterView) throws Exception {
        try{
            if (commonData.globalShareData.getExecutionFlag()!=null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    accessServices.getProductsPage().selectProductView(filterView);
                } else {
                    logger.info(Values.TXT_MSGDOESNOTREQUIREDEXECUTE + commonData.globalShareData.getExecutionFlag().trim());
                }
            }
        }catch (InvocationTargetException |NullPointerException e) {
            accessServices.getProductsPage().selectProductView(filterView);
        }
    }
}
