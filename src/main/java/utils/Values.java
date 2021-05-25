package utils;

import org.openqa.selenium.By;

public final class Values {
    //============STRING VALUES
    public static final String TXT_GLOBAL_PROPERTIES = "GlobalConfig.properties";
    public static final String TXT_RETRYMSG001 = "Invoking Again The Method =================================================> ";
    public static final String TXT_RETRYWHILE = "retryWhileExceptionTries";
    public static final String TXT_SWITCHDEFAULTMESSAGE = "This Value it is not supported or handdle please review the value";
    public static final String TXT_PRODUCTNOTDISPLAYMESSAGE = "The product enrollment page was not displayed";
    public static final String TXT_NOTAPPLY = "N_A";
    public static final String TXT_RANDOM = "RND";
    public static final String TXT_ACCOUNTID = "Account ID";
    public static final String TXT_HEALTHCAREACCOUNT = "Health Care Account";
    public static final String TXT_HEALTHCAREPROVIDER = "Health Care Provider";
    public static final String TXT_PATIENTCPC = "Consumer/Patient/Caregiver";
    public static final String TXT_EMPLOYEE = "Internal AZ";
    public static final String TXT_Y_VALUE = "Y";
    public static final String TXT_N_VALUE = "N";
    public static final String TXT_NOPI = "nopi";
    public static final String TXT_PMI = "pmi";
    public static final String TXT_PBM = "pbm";
    public static final String TXT_EXCREFLECTION = "There Was An Exception Reflection Executed";
    public static final String TXT_NOTREQUIREDMSG = "This Data Is Not Required ";
    public static final String TXT_ACCDATAIDENTIFIER = "AutSPP_";
    public static final String TXT_COM = ".com";
    public static final String TXT_SLASH = "/";
    public static final String TXT_UNDERSCORE = "_";
    public static final String TXT_ADMIN = "admin";
    public static final String TXT_HCA = "hca";
    public static final String TXT_HCP = "hcp";
    public static final String TXT_CPC = "cpc";
    public static final String TXT_EMP = "emp";
    public static final String TXT_HYPHEN = "-";
    public static final String TXT_DOT = ".";
    public static final String TXT_SEARCHFROMFILE = "SFF";
    public static final String TXT_EXTERNALID = "externalID";
    public static final String TXT_NAMEHCA = "nameHCA";
    public static final String TXT_EMAIL = "email";
    public static final String TXT_FIRSTNAME = "firstName";
    public static final String TXT_PHONEORFAX = "phoneOrFax";
    public static final String TXT_ADDRESSLINE1 = "addressLine1";
    public static final String TXT_STATECODE = "stateCode";
    public static final String TXT_ZIPCODE = "zipCode";
    public static final String TXT_DATEOFBIRTH = "dateOfBirth";
    public static final String TXT_CITY = "city";
    public static final String TXT_COUNTRY = "country";
    public static final String TXT_MIDDLENAME = "middleName";
    public static final String TXT_LASTNAME = "lastName";
    public static final String TXT_CAREGIVER = "careGiver";
    public static final String TXT_NPI = "npi";
    public static final String TXT_ACCOUNTTYPE = "accountType";
    public static final String REFLECTION_COMMONFUNCTIONSCLASSPATH = "base.functions.CommonFunctions";
    public static final String TXT_AZ = "AZ";
    public static final String TXT_DSI = "DSI";
    public static final String TXT_CONSENTTYPE = "consentType";
    public static final String TXT_1372FILENAME = "1372_EnrollmentProducts";
    public static final String TXT_MSGDOESNOTREQUIREDEXECUTE = "Does not required to be executed Since Flag Contains : ";
    public static final String TXT_MSGPATIENTNOTFOUND = "The patient account created was not found";
    public static final String TXT_ASTRAZENECA ="AstraZeneca";



    //============INTEGER VALUES
    public static int globalCounter = 0;

    //============FORMATS
    public static final String DATEFORMAT_MMM_DD_HH_MM = "MMM.dd.HH.mm";


    //============CHARACTERS
    public static final char CHAR_UNDERSCORE = '_';
    public static final char CHAR_AT = '@';
    public static final char CHAR_DOT = '.';

    //============KEYVALUES
    public static final String KEYVALUE_EMPLOYEERECORD = "EmployeeRecord";
    public static final String KEYVALUE_EXISTINGHCPRECORD = "ExistingHCP";
    public static final String KEYVALUE_HCARECORD = "HCARecord";
    public static final String KEYVALUE_CPCRECORD = "CPCRecord";
    public static final String KEYVALUE_HCPRECORD = "HCPRecord";


    public static final String KEYVALUE_FIRSTNAME = "firstName";
    public static final String KEYVALUE_LASTNAME = "lastName";
    public static final String KEYVALUE_MIDDLENAME = "middleName";

    //============REPLACE TO
    public static final String REPLACETO_EMPTY = "";


    //============VALUESTOSEARCH
    public static final String VALUESTOSEARCH_NONE = "--None--";

    //============BY Paths
    public static final By BYPATH_AZID = By.xpath("(//*[contains(text(),'Account ID')]//..)[1]");
    public static final By CAREGIVERLOCATOR = By.xpath("//*[contains(@id,'input')][@role='option']//*[@title])");

    //============ATTRIBUTE VALUE
    public static final String ATTRIBUTE_FALSE_VALUE = "false";
    public static final String ATTRIBUTE_ARIAEXPANDED_VALUE = "aria-expanded";
    public static final String ATTRIBUTE_DATAVALUE_VALUE = "data-value";

    //============REGEX RULES
    public static final String REGEX_COMMA = "[,]";
    public static final String REGEX_SEMICOLON = "[;]";
    public static final String REGEX_REPLACEINDEXLABEL = "P[\\d:]*(.):";
    public static final String REGEX_WITHESPACE = "[(\\s)]";
    public static final String REGEX_PIPE = "[|]";

    //============ARRAY VALUES USE FOR DATAPATIENT CREATION
    public static final String[] ARRAY_PHONEFAXVALUES = {"Home Fax", "Home Phone", "International", "Mobile", "Office Fax", "Office Phone"};
    public static final String[] ARRAY_EMAILDOMAINVALUES = {"astrazeneca", "testDomain", "anotherDomain", "mobileDomain", "yesImADomain", "notADomain"};
    public static final String[] ARRAY_FULLEMAILDOMAINVALUES = {"@sharklasers.com"};
    public static final String[] ARRAY_ZIPCODEVALUES = {"06019", "06023"};

    //============CONSENT TYPE VALUES
    public static final String TXT_DFM = "DSI Family Consent";
    public static final String TXT_DCC = "DSI Copay Consen";
    public static final String TXT_APC = "AZ Promotional Consent";
    public static final String TXT_DPC = "DSI Promotional Consent";
    public static final String TXT_AFP = "AZ Family Consent";
    public static final String TXT_ANP = "AZ Non-Promotional Consent";
    public static final String TXT_DNC = "DSI Non-Promotional Consent";
    public static final String TXT_ACC = "AZ Copay Consent";

    //============CTM RELATIONSHIP
    public static final String CTM_PRESCRIBINGPHYSICIAN = "Prescribing Physician";
    public static final String CTM_TREATINGPHYSICIAN = "Treating Physician";
    public static final String CTM_PHARMACIST = "Pharmacist";
    public static final String CTM_OFFICESTAFF = "Office Staff";
    public static final String CTM_OTHER = "Other";

    public static final String CTM_PRESCRIBINGFACILITY = "Prescribing Facility";
    public static final String CTM_TREATINGFACILITY = "Treating Facility";
    public static final String CTM_HCAPHARMACIST = "Pharmacy";
    public static final String CTM_HCAOTHERFACILITY = "Other Facility";
}
