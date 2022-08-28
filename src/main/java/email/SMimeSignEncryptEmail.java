package email;

import net.markenwerk.utils.mail.smime.SmimeKey;
import net.markenwerk.utils.mail.smime.SmimeKeyStore;
import net.markenwerk.utils.mail.smime.SmimeUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.InputStream;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Properties;


public class SMimeSignEncryptEmail {

    public static void main(String[] args) throws Exception {

        /*
            Reference:  https://github.com/simple-java-mail/java-utils-mail-smime
         */

        Security.addProvider(new BouncyCastleProvider());

        //configure Mailtrap's SMTP server details
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

        //create the Session object
        Session session = Session.getInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        session.setDebug(true);


        String from = "finra@certifixlivescan.com";                 // To be defined
        String to = "submission@ebtstest.sterlingidentity.com";
        String subject = "FINRA Ten-print Submission";
        File eftsFile = new File("/home/mina/Desktop/FINRA Ten-print Submission.txt");                                    // To be defined

        sendEftsAtttachmentMail(session, from, to, subject, eftsFile);
    }

    public static void sendEftsAtttachmentMail(Session session, String from, String to, String subject, File eftsFile) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setSentDate(new Date());
        message.setHeader("Content-Transfer-Encoding", "base64");

        MimeBodyPart mimeEftsAttachBodyPart = new MimeBodyPart();
        mimeEftsAttachBodyPart.attachFile(eftsFile);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeEftsAttachBodyPart);
        message.setContent(multipart);

        MimeMessage signedMessage = signMessage(session, message);
        MimeMessage encryptedSignedMessage = encryptMessage(session, signedMessage);

        Transport.send(signedMessage);
    }


    public void sendMail(Session session, String from, String to, String subject, String content) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setContent(content, "text/plain; charset=utf-8");
        MimeMessage encryptedSignedMessage = encryptMessage(session, signMessage(session, message));

        Transport.send(encryptedSignedMessage);
    }

    private static MimeMessage signMessage(Session session, MimeMessage message) throws Exception {
        SmimeKey smimeKey = getSmimeKeyForSender();
        return SmimeUtil.sign(session, message, smimeKey);
    }

    private static SmimeKey getSmimeKeyForSender() {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream pkcs12Stream = classLoader.getResourceAsStream("smime.p12");

        String storePass = "smime123";
        String keyPass = "smime123";

        SmimeKeyStore smimeKeyStore = new SmimeKeyStore(pkcs12Stream, storePass.toCharArray());
        SmimeKey smimeKey = smimeKeyStore.getPrivateKey("alias", keyPass.toCharArray());

        return smimeKey;
    }


    private static MimeMessage encryptMessage(Session session, MimeMessage message) throws Exception {
        X509Certificate certificate = getCertificateForRecipient();
        return SmimeUtil.encrypt(session, message, certificate);
    }

    private static X509Certificate getCertificateForRecipient() throws CertificateException, NoSuchProviderException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream pemStream = classLoader.getResourceAsStream("cert.pem");

        CertificateFactory factory = CertificateFactory.getInstance("X.509", "BC");
        X509Certificate certificate = (X509Certificate) factory.generateCertificate(pemStream);

        return certificate;
    }


}
