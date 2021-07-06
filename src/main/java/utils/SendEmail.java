package utils;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;

/**
 * //============Function Purpose:
 * Sent an email with the regression results.
 *
 * //============Configuration:
 * dataEmail:   contains an array of two types of emails external email and internal email (Astrazeneca email) this data
 *              this data comes from the class "src\\main\\java\\utils\\Values.java" in the constant "ARRAY_EMAILDATA".
 *              To select which type of email to use the option can be selected from the file "src\\main\\java\\config\\GlobalConfig.properties" at the
 *              variable "email" e.g. email = int or email = ext
 * EMAIL_TOEMAILLIST:   it contains the list of the contacts that will receive the email. It can be found in the class
 *                      "src\\main\\java\\utils\\Values.java"
 * EMAIL_SUBJECT: contains the Subject of the Email.It can be found in the class "src\\main\\java\\utils\\Values.java"
 * EMAIL_BODY: contains the body of what contains the email.It can be found in the class "src\\main\\java\\utils\\Values.java"
 * EMAIL_ATTACHPDFFILE contains the path of the file with the results to be attach to the email.It can be found in the class "src\\main\\java\\utils\\Values.java"
 *
 * NOTE:
 * To acivate/deactivate the sent of the email it can be done changing the value of the variable "sendReport" that can be found
 * in the file "src\\main\\java\\config\\GlobalConfig.properties" e.g. sendReport = true or sendReport = false.
 *
 */
public class SendEmail {
    private Logger logger = Logger.getLogger(CommonFunctions.class);
    CommonFunctions commonFunctions = new CommonFunctions();
    List<String> dataEmail = new ArrayList<>();

    public void emailAttachment() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(dataEmail.get(0), dataEmail.get(1));
            }
        });

        //Start our mail message to Whom and the subject
        MimeMessage msg = new MimeMessage(session);
        try {
            dataEmail = commonFunctions.getEmailData();
            msg.setFrom(new InternetAddress(dataEmail.get(0)));
            msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(Values.EMAIL_TOEMAILLIST));
            msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(Values.EMAIL_TOCCEMAILLIST));
            msg.setSubject(Values.EMAIL_SUBJECT + commonFunctions.getCalendarDate());

            Multipart emailContent = new MimeMultipart();

            //It contain the text body
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(Values.EMAIL_BODY);

            //It will Attach the result report document.
            MimeBodyPart pdfAttachment = new MimeBodyPart();
            File relativePath = new File(Values.EMAIL_ATTACHPDFFILE);
            pdfAttachment.attachFile(relativePath.getAbsolutePath());

            //Attached the body and the attachment document
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(pdfAttachment);

            //Attach multipart to message
            msg.setContent(emailContent);
            Transport.send(msg);
            logger.info("EMAIL SENT SUCCESSFULLY");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}