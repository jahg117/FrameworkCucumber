package utils;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;

/**
 * //============Function Purpose:
 * Sent an email with the regression results.
 * <p>
 * //============Configuration:
 * dataEmail:   contains an array of two types of emails external email and internal email (AstraZeneca email)
 * this data comes from the class "src\\main\\java\\utils\\Values.java" in the constant "ARRAY_EMAILDATA".
 * To select which type of email to use the option can be selected from the file "src\\main\\java\\config\\GlobalConfig.properties" at the
 * variable "email" e.g. email = int or email = ext
 * EMAIL_TOEMAILLIST:   it contains the list of the contacts that will receive the email. It can be found in the class
 * "src\\main\\java\\utils\\Values.java"
 * EMAIL_SUBJECT: contains the Subject of the Email.It can be found in the class "src\\main\\java\\utils\\Values.java"
 * EMAIL_BODY: contains the body of what contains the email.It can be found in the class "src\\main\\java\\utils\\Values.java"
 * EMAIL_ATTACHPDFFILE contains the path of the file with the results to be attach to the email.It can be found in the class "src\\main\\java\\utils\\Values.java"
 * <p>
 * NOTE:
 * To activate/deactivate the sent of the email it can be done changing the value of the variable "sendReport" that can be found
 * in the file "src\\main\\java\\config\\GlobalConfig.properties" e.g. sendReport = true or sendReport = false.
 */
public class SendEmail {
    private Logger logger = Logger.getLogger(CommonFunctions.class);
    CommonFunctions commonFunctions = new CommonFunctions();
    List<String> dataEmail = new ArrayList<>();

    /**
     * use to send the email of the Regression Results Report to specific contacts after each deploy on UAT
     *
     * @param filePath contains the file path from where the attach files is located
     * @param fileName contains the name of file that will be send has a Attach in the email
     * @throws Exception
     * @author J.Ruano
     */
    public void emailAttachment(String filePath, String fileName) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", "587");
        dataEmail = commonFunctions.getEmailData();
        String sentoSelfBackUp = "";
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(dataEmail.get(0), dataEmail.get(1));
            }
        });
        FileReading fileReading = new FileReading();
        fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);
        String sendTo = fileReading.getField(Values.TXT_SENDTO);

        //Start our mail message to Whom and the subject
        MimeMessage msg = new MimeMessage(session);
        MimeBodyPart textBodyPart = new MimeBodyPart();
        MimeBodyPart pdfAttachment = new MimeBodyPart();
        Multipart emailContent = new MimeMultipart();
        try {
            if (sendTo.contains(Values.TXT_AT)) {
                sentoSelfBackUp = sendTo;
                sendTo = Values.TXT_SENDTOSELF;
            }
            msg.setFrom(new InternetAddress(dataEmail.get(0)));
            switch (sendTo) {
                case Values.TXT_SENDTOSELF:
                    msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(sentoSelfBackUp));
                    logger.info(Values.MSG_EMAIL_SENDTO + sendTo);
                    break;
                case Values.TXT_SENDTOAUTOMATIONTEAM:
                    msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(Values.EMAIL_AUTTEAMLIST));
                    msg.setSubject(Values.EMAIL_SUBJECTTEST + commonFunctions.getCalendarDate());
                    textBodyPart.setText(Values.EMAIL_TESTBODY);
                    logger.info(Values.MSG_EMAIL_SENDTO + sendTo + Values.TXT_SENDTOAUTOMATONTEAM);
                    break;
                case Values.TXT_SENDTOTESTTEAM:
                    msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(Values.EMAIL_AUTTEAMLIST));
                    msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(Values.EMAIL_TESTERSLIST));
                    msg.setSubject(Values.EMAIL_SUBJECT + commonFunctions.getCalendarDate());
                    textBodyPart.setText(Values.EMAIL_BODY);
                    logger.info(Values.MSG_EMAIL_SENDTO + Values.TXT_SENDTOQA);
                    break;
                default:
                    msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(Values.EMAIL_AUTTEAMLIST));
                    msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(Values.EMAIL_CCALLLIST));
                    msg.setSubject(Values.EMAIL_SUBJECT + commonFunctions.getCalendarDate());
                    textBodyPart.setText(Values.EMAIL_BODY);
                    logger.info(Values.MSG_EMAIL_SENDTO + sendTo);
                    break;
            }
            Path path = Paths.get(System.getProperty("user.dir") + filePath + File.separator + fileName);
            File pathCreated = new File(String.valueOf(path));

            //It will Attach the result report document.
            pdfAttachment.attachFile(pathCreated);

            //Attached the body and the attachment document
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(pdfAttachment);

            //Attach multipart to message
            msg.setContent(emailContent);
            Transport.send(msg);
            logger.info(Values.EMAIL_SUCCESSMESSAGE);
        } catch (MessagingException e) {
            e.printStackTrace();
            if (e.getCause().toString().contains(Values.EMAIL_EXCFILENOTFOUND)) {
                logger.error(e.getMessage());
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}