package email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class HtmlEmail {

    public static void main(String[] args) throws MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.mailtrap.io");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        properties.setProperty("mail.user", "5d8af446cdaa8e");
        properties.setProperty("mail.password", "8c954566014d92");


        String username = "5d8af446cdaa8e";
        String password = "8c954566014d92";

        // Get the default Session object.
//        Session session = Session.getDefaultInstance(properties);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create a default MimeMessage object.
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("from@gmail.com"));


        // Params: addresslist – comma separated address strings
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse("to@gmail.com"));

        // Params: addresslist – comma separated address strings
        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("to@gmail.com"));


        message.setSubject("HTML Mail Subject");

//        String msg = "This is my first email using JavaMailer";
        String msgStyled = "This is my <b style='color:red;'>bold-red email</b> using JavaMailer";

        String msg = "<h1>This is my first email using JavaMailer</h1>";

        /*
            WATCH OUT, there is 2 ways to set email body
            As per the Javadoc, the MimeMessage#setText() sets a default mime type of text/plain,
            while you need text/html. Rather use MimeMessage#setContent() instead.
         */
        //message.setText("This is actual message");
        message.setContent(msg, "text/html; charset=utf-8");

        // second method
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msgStyled, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

        Transport.send(message);


    }
}
