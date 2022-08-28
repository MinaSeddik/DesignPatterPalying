
package email;

import net.markenwerk.utils.mail.smime.SmimeKey;
import net.markenwerk.utils.mail.smime.SmimeKeyStore;
import net.markenwerk.utils.mail.smime.SmimeUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Properties;

public class GoogleWorkSpaceWithSMimeSignEncryptEmail {

    public static void main(String[] args) throws Exception {

        Security.addProvider(new BouncyCastleProvider());
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
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));

        // Params: addresslist – comma separated address strings
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse("mina.ibrahim.softxpert@gmail.com"));

        message.setSubject("JavaMail Subject Encrypted");
        message.setSentDate(new Date());

        String msg = "<h1>This is a signing email</h1>";
        message.setContent(msg, "text/html; charset=utf-8");
        message.saveChanges();

        System.out.println("Get Gmail OAuth 2.0 access token ...");
        String accessToken = oAuth2GmailAuthenticator.getAccessToken();


        System.out.println("Signing the email ...");
        MimeMessage signedMessage = signMessage(session, message);
        MimeMessage encryptedSignedMessage = encryptMessage(session, signedMessage);

        System.out.println("send email ...");
        Transport.send(encryptedSignedMessage, encryptedSignedMessage.getAllRecipients(), username, accessToken);

        System.out.println("email sent!");

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
