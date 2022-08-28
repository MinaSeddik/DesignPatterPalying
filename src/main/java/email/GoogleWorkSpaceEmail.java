
package email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class GoogleWorkSpaceEmail {

    public static void main(String[] args) throws MessagingException {

        OAuth2GmailAuthenticator oAuth2GmailAuthenticator = new OAuth2GmailAuthenticator();

        String username = "danialpoitrras1258@gmail.com";
        String from = "danialpoitrras1258@gmail.com";


        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");          // required for Gmail
        properties.put("mail.smtp.auth.mechanisms", "XOAUTH2");


        // Get the default Session object.
        System.out.println("open session ...");
        Session session = Session.getInstance(properties);
        session.setDebug(true);


        // Create a default MimeMessage object.
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));

        // Params: addresslist – comma separated address strings
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse("mina.ibrahim.softxpert@gmail.com"));

        message.setSubject("JavaMail Subject");
        message.setSentDate(new Date());

        String msg = "<h1>This is my first email using JavaMailer</h1>";
        message.setContent(msg, "text/html; charset=utf-8");
        message.saveChanges();

        System.out.println("Get Gmail OAuth 2.0 access token ...");
        String accessToken = oAuth2GmailAuthenticator.getAccessToken();

        System.out.println("send email ...");
        Transport.send(message, message.getAllRecipients(), username, accessToken);

        System.out.println("email sent!");

    }
}
