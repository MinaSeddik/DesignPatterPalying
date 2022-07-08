package Cryptographic;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class Signing {


    public static void main(String[] args) throws GeneralSecurityException, IOException {

        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("test.cer");
        X509Certificate certificate = (X509Certificate) certFactory.generateCertificate(input);
        PublicKey publicKey = certificate.getPublicKey();

        char[] keystorePassword = "password".toCharArray();
        char[] keyPassword = "password".toCharArray();
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        InputStream input2 = classLoader.getResourceAsStream("Baeldung.p12");
        keystore.load(input2, keystorePassword);
        PrivateKey privateKey = (PrivateKey) keystore.getKey("baeldung", keyPassword);


        System.out.println("Default Charset=" + Charset.defaultCharset());
        String secretMessage = "My password is 123456Seven";
        System.out.println("Original Message : " + secretMessage);
        String encryptedData = encrypt(secretMessage, publicKey);
        System.out.println("Encrypted Message : " + encryptedData);
        String decryptedMessage = decrypt(encryptedData, privateKey);
        System.out.println("Decrypted Message : " + decryptedMessage);


    }


    public static String encrypt(String rawText, PublicKey publicKey) throws IOException, GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedDate = cipher.doFinal(rawText.getBytes("UTF-8"));

        return Base64.encodeBase64String(encryptedDate);
    }

    public static String decrypt(String cipherText, PrivateKey privateKey) throws IOException, GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedDate = cipher.doFinal(Base64.decodeBase64(cipherText));

        return new String(decryptedDate, "UTF-8");
    }


}

