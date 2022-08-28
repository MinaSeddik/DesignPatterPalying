
package email;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailWithAttachment {

    public static void main(String[] args) throws MessagingException, IOException {

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


        message.setSubject("Mail Subject");

//        String msg = "This is my first email using JavaMailer";
        String msgStyled = "This is my <b style='color:red;'>bold-red email</b> using JavaMailer";

        // (1) part-1 html body
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msgStyled, "text/html; charset=utf-8");

        // (2) part-2 attachment
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.attachFile(new File("/home/mina/Desktop/red.png"));

        // OR - This is another way to attach file - more verbose way
        // Part two is attachment
        MimeBodyPart messageBodyPart2 = new MimeBodyPart();
        String filename = "/home/mina/Desktop/red.png";
        DataSource source = new FileDataSource(filename);
        messageBodyPart2.setDataHandler(new DataHandler(source));
        messageBodyPart2.setFileName(filename);


        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        multipart.addBodyPart(attachmentBodyPart);
        multipart.addBodyPart(messageBodyPart2);

        message.setContent(multipart);

        Transport.send(message);


    }
}

