package utils;

import org.openqa.selenium.By;

public final class Values {
    //============STRING VALUES
    public static final String TXT_GLOBAL_PROPERTIES = "GlobalConfig.properties";
    public static final String TXT_RETRYMSG001 = "Invoking Again The Method =================================================> ";
    public static final String TXT_RETRYWHILE = "retryWhileExceptionTries";
    public static final String TXT_SWITCHDEFAULTMESSAGE = "This Value it is not supported or handdle please review the value";
    public static final String TXT_NOTAPPLY = "N_A";
    public static final String TXT_RANDOM = "RND";
    public static final String TXT_ACCOUNTID = "Account ID";
    public static final String TXT_HEALTHCAREACCOUNT = "Health Care Account";
    public static final String TXT_HEALTHCAREPROVIDER = "Health Care Provider";
    public static final String TXT_CPC = "Consumer/Patient/Caregiver";
    public static final String TXT_EMPLOYEE = "Internal AZ";
    public static final String TXT_Y_VALUE = "Y";
    public static final String TXT_N_VALUE = "N";
    public static final String TXT_NOPI = "nopi";
    public static final String TXT_PMI = "pmi";
    public static final String TXT_PBM = "pbm";
    public static final String TXT_EXCREFLECTION = "There Was An Exception Reflection Executed";




    //============INTEGER VALUES
    public static int globalCounter = 0;

    //============FORMATS
    public static final String DATEFORMAT_MMM_DD_HH_MM = "MMM.dd.HH.mm";

    //============CHARACTERS
    public static final char CHAR_UNDERSCORE = '_';

    //============KEYVALUES
    public static final String KEYVALUE_EMPLOYEERECORD = "EmployeeRecord";
    public static final String KEYVALUE_FIRSTNAME = "firstName";
    public static final String KEYVALUE_LASTNAME = "lastName";
    public static final String KEYVALUE_MIDDLENAME = "middleName";

    //============REPLACE TO
    public static final String REPLACETO_EMPTY = "";

    //============VALUESTOSEARCH
    public static final String VALUESTOSEARCH_NONE = "--None--";

    //============BY Paths
    public static final By BYPATH_AZID = By.xpath("(//*[contains(text(),'Account ID')]//..)[1]");

    //============ATTRIBUTE VALUE
    public static final String ATTRIBUTE_FALSE_VALUE = "false";
    public static final String ATTRIBUTE_ARIAEXPANDED_VALUE = "aria-expanded";
    public static final String ATTRIBUTE_DATAVALUE_VALUE = "data-value";







}
