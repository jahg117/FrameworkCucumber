package utils;

import org.openqa.selenium.By;

import java.io.File;
import java.nio.file.Paths;

public final class Values {
    //============STRING VALUES
    public static final String TXT_GLOBAL_PROPERTIES = "GlobalConfig.properties";
    public static final String TXT_RETRYMSG001 = "Invoking Again The Method =================================================> ";
    public static final String TXT_RETRYWHILE = "retryWhileExceptionTries";
    public static final String TXT_SWITCHDEFAULTMESSAGE = "This Value it is not supported or handdle please review the value";
    public static final String TXT_FILEDOESNOTEXIST = "does not exist or is not a directory";
    public static final String TXT_PRODUCTNOTDISPLAYMESSAGE = "The product enrollment page was not displayed";
    public static final String TXT_NOTAPPLY = "N_A";
    public static final String TXT_RANDOM = "RND";
    public static final String TXT_TODAY = "Today";
    public static final String TXT_SELF = "Self";
    public static final String TXT_LAR = "LAR";
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
    public static final String TXT_COLON = ":";
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
    public static final String TXT_MSGPATIENTFOUND = "The patient account created was found";
    public static final String TXT_ASTRAZENECA = "AstraZeneca";
    public static final String TXT_JSEXCACTIONSCLICK = "javascript error:";
    public static final String TXT_EXCINTERCEPTED = "element click intercepted";
    public static final String TXT_WEBELEMENTCLICK = "===============webElement.click()===============";
    public static final String TXT_JSCLICK = "================clickElementJS()================";
    public static final String TXT_VALTRUE = "true";
    public static final String TXT_VALFALSE = "false";
    public static final String TXT_ERRORCREATINGRECORD_DOB = "Unable to read SObject's field value[s]";
    public static final String TXT_TAGEMAILMESSAGE_01 = " With the following tag name the email will be send to: ";
    public static final String TXT_TAGEMAILMESSAGE_02 = "The following is the tag of the current scenarios to be executed: ";
    public static final String TXT_SENDTO = "sendTo";
    public static final String TXT_AT = "@";
    public static final String TXT_NOINSURANCE = "No Insurance Will Be Created";
    public static final String TXT_NOCONSENT = "No Consent will be crated";
    public static final String TXT_UAT = "UAT";
    public static final String TXT_INT = "INT";
    public static final String TXT_RNDCONSENT = "Consent will be created complety randomly";
    public static final String TXT_DRUG_USEFOR_PE = "The PE will be created with the following drug: ";
    public static final String TXT_EMPTY = "";
    public static final String TXT_NOPECREATED = "The PE was not created since the drug was not found on the array list, the drug was: ";
    public static final String TXT_NOPENEEDIT = "No PE will be created since it was configured as N_A ";
    public static final String TXT_NOCASENEEDIT = "No Cases will be created since it was configured as N_A ";
    public static final String TXT_CASENEEDIT = "The following type of case was created: ";
    public static final String TXT_NOINTERACTIONFORM = "The case form option was not displayed";
    public static final String TXT_NOCASETYPEFOUND = "The case type selected was not found in the array: ";
    public static final String TXT_VALUE_P = "P";
    public static final String TXT_NOTTACHMENT = "No files will be added to the consent: ";
    public static final String TXT_NOFILEWASFOUND = "No file was found with this name selecting random: ";
    public static final String TXT_FILEADDED = "File Added Successfully ";
    public static final String TXT_FILENOTADDED = "File Was NOT Added Successfully ";
    public static final String TXT_NOITEMSDISPLAY = "No items to display ";






    //============INTEGER VALUES
    public static int globalCounter = 0;

    //============FORMATS
    public static final String DATEFORMAT_MMM_DD_HH_MM = "MMM.dd.HH.mm";
    public static final String DOB_MM_DD_YYYY = "MM.dd.yyyy";
    public static final String DOB_DD_MM_YYYY = "dd.MM.yyyy";


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
    public static final By DATESCRIPFORMAT = By.xpath("//script[contains(text(),\"L : 'MM/DD/YYYY'\")]");
    public static final By ERRORDOB = By.xpath("//*[contains(text(),\"Unable to read SObject's field value[s]\")]");

    //script[contains(text(),"L : 'MM/DD/YYYY'")]

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
    public static final String REGEX_STARTSWITH = "^[A-Za-z]*_";//example AUT_TEST it will get AUT_


    //============ARRAY VALUES USE FOR DATAPATIENT CREATION
    public static final String[] ARRAY_PHONEFAXVALUES = {"Home Fax", "Home Phone", "International", "Mobile", "Office Fax", "Office Phone"};
    public static final String[] ARRAY_EMAILDOMAINVALUES = {"astrazeneca", "testDomain", "anotherDomain", "mobileDomain", "yesImADomain", "notADomain"};
    public static final String[] ARRAY_FULLEMAILDOMAINVALUES = {"@sharklasers.com", "@astrazeneca.com", "@testDomain.com"};
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
    public static final String CTM_NURSE = "Nurse";
    public static final String CTM_OFFICESTAFF = "Office Staff";
    public static final String CTM_OTHER = "Other";

    public static final String CTM_PRESCRIBINGFACILITY = "Prescribing Facility";
    public static final String CTM_TREATINGFACILITY = "Treating Facility";
    public static final String CTM_HCAPHARMACIST = "Pharmacy";
    public static final String CTM_HCAOTHERFACILITY = "Other Facility";

    //============EMAIL BODY CONSTANTS
    public static final String GlobalPathPdf = Paths.get("").toAbsolutePath().toString() +
            File.separator + "test output" + File.separator + "PdfReport" + "" +
            File.separator + "ExtentPdf.pdf";

    //============CC CODES AND MESSAGES
    public static final String TXT_CCCODE = "cc";
    public static final String TXT_CCCONTACTSCODE = "ccContactsCode";
    public static final String TXT_CCSPECIFICCODE = "ccSpecific";
    public static final String TXT_CCALLCONTACTSCODE = "ccAllContacts";
    public static final String TXT_CCAUTTEAMCODE = "ccAutTeam";
    public static final String TXT_CCTESTERSCODE = "ccTesters";

    public static final String TXT_SENDTOSELF = "self";
    public static final String TXT_SENDTOAUTOMATIONTEAM = "at";
    public static final String TXT_SENDTOTESTTEAM = "qa";
    public static final String TXT_SENDTONOTAPPLY = "na";

    public static final String MSG_USECCMESSAGE = "CC contacts will be use to send the Email";
    public static final String MSG_NOCCMESSAGE = "No CC will use to send the Email";
    public static final String MSG_CCSPECIFICMAIL = "The Specific email contact will receive the Results Report";
    public static final String MSG_CCALLCONTACTSEMAIL = "The All CC Contacts list will be receiving the Results Report";
    public static final String MSG_CCAUTTEAMEMAIL = "The CC Automation Team list will be receiving the Results Report";
    public static final String MSG_CCTESTERSEMAIL = "The CC Testers Team list will be receiving the Results Report";

    //============TO CODES AND MESSAGES
    public static final String TXT_SENDTOCONTACTSCODE = "toContactsCode";
    public static final String TXT_SENDTOSPECIFICCODE = "toSpecific";
    public static final String TXT_SENDTOALLCONTACTSCODE = "toAllContacts";
    public static final String TXT_SENDTOAUTTEAMCODE = "toAutTeam";
    public static final String TXT_SENDTOTESTERSCODE = "toTesters";

    public static final String TXT_SENDTOAUTOMATONTEAM = " to Automation Team";
    public static final String TXT_SENDTOSTAKEHOLDERS = " to Stake holders";
    public static final String TXT_SENDTOQA = " to QA team";

    public static final String[] TXT_SMOKESCENARIOS = {"@smokeScenarios", "@regressionScenarios","@createTesData","@1372_configurableConsents"};

    public static final String MSG_SENDTOSPECIFICMAIL = "The Specific email contact will receive the Results Report";
    public static final String MSG_SENDTOALLCONTACTSEMAIL = "The All CC Contacts list will be receiving the Results Report";
    public static final String MSG_SENDTOAUTTEAMEMAIL = "The CC Automation Team list will be receiving the Results Report";
    public static final String MSG_SENDTOTESTERSEMAIL = "The CC Testers Team list will be receiving the Results Report";
    public static final String MSG_EMAIL_SENDTO = "The email was send to: ";

    public static final String EMAIL_SUBJECT = "Results From Regression Executed On: ";
    public static final String EMAIL_SUBJECTTEST = "THIS IS A TEST PLEASE DO NOT REPLY. Executed On: ";
    public static final String EMAIL_SUCCESSMESSAGE = "EMAIL WAS SEND SUCCESSFULLY";
    public static final String EMAIL_EXCFILENOTFOUND = "The system cannot find the file specified";
    public static final String EMAIL_TESTBODY = "This is just a test executed by the Automation Team.";
    public static final String EMAIL_BODY = "Hello Team,\n" +
            "\nHere are the results from the last automatic execution of our automated test suite. This health check is automatically triggered after every new UAT release.\n" +
            "In case you have any doubt or comment please contact:\n\n" +
            "\t- juan.rincon@astrazeneca.com\n" +
            "\t- jonathanernesto.ruano@astrazeneca.com\n" +
            "\t- juanalejandro.hernandez@astrazeneca.com";

    public static final String EMAIL_FIELDNAME = "email";
    public static final String[] ARRAY_EMAILDATA = {"az_automation_gdl@hotmail.com,az_aut_gdl@", "MAILHERE@astrazeneca.net,PASSHERE"};
    public static final String[] ARRAY_SUITETAGNAMES = {"@smokeScenarios", "@regressionScenarios","@createTesData","@1372_configurableConsents"};
    public static final String EMAIL_PATHPDF = "/test output/PdfReport";
    public static final String EMAIL_EXTENTPDF = "ExtentPdf.pdf";

    //============EMAIL CONTACT LISTS
    public static final String EMAIL_CCALLLIST = "catherine.goodis2@astrazeneca.com,amy.hermann1@astrazeneca.com,anum.aziz@astrazeneca.com,alec.wojack@astrazeneca.com,fernando.morales1@astrazeneca.com,monica.cardenas@astrazeneca.com," +
            "alixdria.carreon@astrazeneca.com,adriana.ramirez@astrazeneca.com,laura.burgueno@astrazeneca.com,harsh.marwaha@astrazeneca.com,Timothy.Tyler@astrazeneca.com,Peggy.Metzger@astrazeneca.com," +
            "salvadorantonio.hernandez@astrazeneca.com,ana.garcia3@astrazeneca.com,adolfo.gutierrez@astrazeneca.com,hector.urena@astrazeneca.com,juan.marquez1@astrazeneca.com,pedro.flores@astrazeneca.com," +
            "viridiana.salinas@astrazeneca.com,alejandra.guiot@astrazeneca.com,cesar.magana@astrazeneca.com,isselsinai.ramirez@astrazeneca.com,roberto.campos@astrazeneca.com,daniela.salazar2@astrazeneca.com";
    public static final String EMAIL_AUTTEAMLIST = "juan.rincon@astrazeneca.com,jonathanernesto.ruano@astrazeneca.com,juanalejandro.hernandez@astrazeneca.com";
    public static final String EMAIL_TESTERSLIST = "adolfo.gutierrez@astrazeneca.com,hector.urena@astrazeneca.com,juan.marquez1@astrazeneca.com,pedro.flores@astrazeneca.com," +
            "viridiana.salinas@astrazeneca.com,alejandra.guiot@astrazeneca.com,cesar.magana@astrazeneca.com,isselsinai.ramirez@astrazeneca.com,roberto.campos@astrazeneca.com,daniela.salazar2@astrazeneca.com";

    //============CONSENT TYPE
    public static final String[] ARRAY_AZTYPES_CODES = {"APC","AFP","ANP","ACC"};
    public static final String[] ARRAY_DSITYPES_CODES = {"DFM","DCC","DPC","DNC"};

    //============DRUG LIST
    public static final String[] ARRAY_DRUGLIST = {"P0:N_A","P1:RND","P2:ACCOLATE","P3:ARIMIDEX","P4:ATACAND","P5:AZD1222","P6:AZD7442","P7:BEVESPI AEROSPHERE","P8:BRAZIKUMAB","P9:BREZTRI AEROSPHERE",
            "P10:BRILINTA","P11:BUDESONIDE","P12:BUDESONIDE AND FORMOTEROL AEROSOL","P13:BYDUREON","P14:BYDUREON BCISE","P15:BYDUREON PEN","P16:BYETTA","P17:CALQUENCE","P18:CANDESARTAN CILEXETIL","P19:CAPRELSA",
            "P20:CASODEX","P21:CRESTOR","P22:DALIRESP","P23:DUAKLIR PRESSAIR","P24:ENHERTU","P25:ENTOCORT EC","P26:EPANOVA","P27:ESOMEPRAZOLE MAGNESIUM","P28:ESOMEPRAZOLE STRONTIUM","P29:FARXIGA","P30:FASENRA",
            "P31:FASLODEX","P32:Faslodex/Ibrance Combo Therapy","P33:Faslodex/Kisqali Combo Therapy","P34:Faslodex/Verzenio Combo Therapy","P35:FLUMIST QUADRIVALENT","P36:FULVESTRANT","P37:HIFRENZO","P38:IMFINZI",
            "P39:INVESTIGATIONAL PRODUCT","P40:IRESSA","P41:KOMBIGLYZE XR","P42:Koselugo","P43:LOKELMA","P44:LUMOXITI","P45:LYNPARZA","P46:METOPROLOL SUCCINATE","P47:MOVANTIK","P48:NEXIUM","P49:NIRSEVIMAB",
            "P50:NO PRODUCT MENTIONED","P51:ONGLYZA","P52:PULMICORT FLEXHALER","P53:PULMICORT RESPULES","P54:QTERN","P55:QTERNMET XR","P56:QUETIAPINE FUMARATE XR","P57:SAPHNELO","P58:SEROQUEL","P59:SYMBICORT",
            "P60:SYMLIN","P61:SYNAGIS","P62:TAGRISSO","P63:TENORETIC","P64:TENORMIN","P65:TEZEPELUMAB","P66:TOPROL-XL","P67:TREMELIMUMAB","P68:TUDORZA PRESSAIR","P69:UNSPECIFIED - CARDIOVASCULAR","P70:XIGDUO XR",
            "P71:ZESTORETIC","P72:ZESTRIL","P73:ZOLADEX","P74:ZOLMITRIPTAN","P75:ZOMIG"};

    //============CASE TYPES LIST
    public static final String[] ARRAY_CASETYPELIST = {"P0:N_A","P1:RND","P2:Interaction","P3:Asset Request","P4:Benefit Investigation","P5:Claims Support","P6:Denied Patient Savings",
            "P7:Free Limited Supply Program","P8:General Inquiry","P9:In-Home Nurse Support","P10:Insurance Authorization","P11:Marketing Opt-In","P12:Medical Inquiry","P13:Nurse Support",
            "P14:Patient Savings Program","P15:Pharmacy Coordination","P16:Referral"};

    //============FILE FOR CONSENT ATTACHMENT FILES
    public static final String[] ARRAY_FILESATTACHMENTNAMES = {"P0:N_A","P1:RND","P2:Aut_PNG001","P3:Aut_PDF001","P4:Aut_WORD001","P5:Aut_TXT001"};

    //============INDEXES VALUES
    public static final String IDX_VAL_P0 = "P0";
    public static final String IDX_VAL_P1 = "P1";
    public static final String IDX_VAL_P2 = "P2";


    //ENVIRONMENT VARIABLES
    public static String ENVIRONMENT ="";

    //============ATTRIBUTES NAMES
    public static final String ATR_TITLE = "title";
    public static final String ATR_DATAITEMID = "data-itemid";
    public static final String ATR_DATALABEL = "data-label";

}
