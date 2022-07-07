package Cryptographic;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class ReadCertificate {


    public static void main(String[] args) throws CertificateException, NoSuchProviderException, IOException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {

        Security.addProvider(new BouncyCastleProvider());

        CertificateFactory certFactory= CertificateFactory.getInstance("X.509", "BC");
//        CertificateFactory certFactory= CertificateFactory.getInstance("X.509");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("google.cer");
        X509Certificate certificate = (X509Certificate) certFactory.generateCertificate(input);

        System.out.println("getVersion: " + certificate.getVersion());
        System.out.println("getSigAlgName: " + certificate.getSigAlgName());
        System.out.println("getIssuerDN: " + certificate.getIssuerDN());
        System.out.println("getNotBefore: " + certificate.getNotBefore());
        System.out.println("getNotAfter: " + certificate.getNotAfter());
        System.out.println("getIssuerAlternativeNames: " + certificate.getIssuerAlternativeNames());
        System.out.println("getSignature: " + certificate.getSignature());
        System.out.println("getPublicKey: " + certificate.getPublicKey());



        char[] keystorePassword = "password".toCharArray();
        char[] keyPassword = "password".toCharArray();

        KeyStore keystore = KeyStore.getInstance("PKCS12");

        InputStream input2 = classLoader.getResourceAsStream("Baeldung.p12");
        keystore.load(input2, keystorePassword);


        PrivateKey key = (PrivateKey) keystore.getKey("baeldung", keyPassword);
        System.out.println("PrivateKey: " + key);


    }
}
