package Cryptographic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class KeyStoreReader {


    public static void main(String[] args) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException {

//        readCacertsTrustStore();
//        readBaedungKeyStore();
//        readMyTrustStoreP12();
        readMyTrustStorePKS();



    }

    private static void readBaedungKeyStore() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException {

        KeyStore keystore = KeyStore.getInstance("PKCS12");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("Baeldung.p12");
        keystore.load(input, "password".toCharArray());

        Enumeration<String> e = keystore.aliases();

        // display the result
        System.out.println("List of all the alias present");
        while (e.hasMoreElements()) {
            System.out.print(e.nextElement() + "");
            System.out.println();
        }


        PKIXParameters params = new PKIXParameters(keystore);

        Set<TrustAnchor> trustAnchors = params.getTrustAnchors();
        List<Certificate> certificates = trustAnchors.stream()
                .map(TrustAnchor::getTrustedCert)
                .collect(Collectors.toList());

        System.out.println("trustAnchors size: " + trustAnchors.size());
        System.out.println("certificates size: " + certificates.size());
    }


    private static void readCacertsTrustStore() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException {

        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());

        String relativeCacertsPath = "/lib/security/cacerts".replace("/", File.separator);
        String filename = System.getProperty("java.home") + relativeCacertsPath;

        System.out.println(filename);

        FileInputStream is = new FileInputStream(filename);

        trustStore.load(is, "changeit".toCharArray());

        Enumeration<String> e = trustStore.aliases();

        // display the result
        System.out.println("List of all the alias present");
        while (e.hasMoreElements()) {
            System.out.print(e.nextElement() + "");
            System.out.println();
        }

        PKIXParameters params = new PKIXParameters(trustStore);

        Set<TrustAnchor> trustAnchors = params.getTrustAnchors();
        List<Certificate> certificates = trustAnchors.stream()
                .map(TrustAnchor::getTrustedCert)
                .collect(Collectors.toList());

        System.out.println("trustAnchors size: " + trustAnchors.size());
        System.out.println("certificates size: " + certificates.size());


    }

    private static void readMyTrustStoreP12() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException {

        KeyStore trustStore = KeyStore.getInstance("PKCS12");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream keyStoreStream = classLoader.getResourceAsStream("root_and_intermidiate.p12");
        trustStore.load(keyStoreStream, "Mina1234".toCharArray());

        Enumeration<String> e = trustStore.aliases();

        // display the result
        System.out.println("List of all the alias present");
        while (e.hasMoreElements()) {
            System.out.print(e.nextElement() + "");
            System.out.println();
        }

        PKIXParameters params = new PKIXParameters(trustStore);

        Set<TrustAnchor> trustAnchors = params.getTrustAnchors();
        List<Certificate> certificates = trustAnchors.stream()
                .map(TrustAnchor::getTrustedCert)
                .collect(Collectors.toList());

        System.out.println("trustAnchors size: " + trustAnchors.size());
        System.out.println("certificates size: " + certificates.size());


    }
    private static void readMyTrustStorePKS() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException {

        KeyStore trustStore = KeyStore.getInstance("JKS");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream keyStoreStream = classLoader.getResourceAsStream("rootCA_only.jks");
        trustStore.load(keyStoreStream, "Mina1234".toCharArray());

        Enumeration<String> e = trustStore.aliases();

        // display the result
        System.out.println("List of all the alias present");
        while (e.hasMoreElements()) {
            System.out.print(e.nextElement() + "");
            System.out.println();
        }

        PKIXParameters params = new PKIXParameters(trustStore);

        Set<TrustAnchor> trustAnchors = params.getTrustAnchors();
        List<Certificate> certificates = trustAnchors.stream()
                .map(TrustAnchor::getTrustedCert)
                .collect(Collectors.toList());

        System.out.println("trustAnchors size: " + trustAnchors.size());
        System.out.println("certificates size: " + certificates.size());


    }
}
