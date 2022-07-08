package Cryptographic;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.*;
import java.util.*;
import java.util.stream.Collectors;

public class CertificateValidation {


    public static void main(String[] args) throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, CertPathBuilderException, CertPathValidatorException {


        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        InputStream input = classLoader.getResourceAsStream("server.crt");
//        InputStream input = classLoader.getResourceAsStream("google.cer");
        X509Certificate certificateToCheck = (X509Certificate) certificateFactory.generateCertificate(input);
        System.out.println("***  Subject:" + certificateToCheck.getSubjectDN());
        System.out.println("***  Issuer:" + certificateToCheck.getIssuerDN());


        InputStream input2 = classLoader.getResourceAsStream("intermidiateCA.crt");
        X509Certificate intermidiateToCheck2 = (X509Certificate) certificateFactory.generateCertificate(input2);
        System.out.println("***  Subject:" + intermidiateToCheck2.getSubjectDN());
        System.out.println("***  Issuer:" + intermidiateToCheck2.getIssuerDN());

//        InputStream input2 = classLoader.getResourceAsStream("g_init1.cer");
//        X509Certificate intermidiateToCheck2 = (X509Certificate) certificateFactory.generateCertificate(input2);
//
//        InputStream input3 = classLoader.getResourceAsStream("g_init2.cer");
//        X509Certificate intermidiateToCheck3 = (X509Certificate) certificateFactory.generateCertificate(input3);


        KeyStore trustStore = KeyStore.getInstance("JKS");
        InputStream keyStoreStream = classLoader.getResourceAsStream("rootCA_only.jks");
//        InputStream keyStoreStream = classLoader.getResourceAsStream("intermidiate_only.jks");
//        InputStream keyStoreStream = classLoader.getResourceAsStream("root_and_intermidiate.jks");
//        InputStream keyStoreStream = classLoader.getResourceAsStream("all_chain1.jks");

//        InputStream keyStoreStream = classLoader.getResourceAsStream("google_root.jks");
        trustStore.load(keyStoreStream, "Mina1234".toCharArray());

        System.out.println(trustStore.size());
        System.out.println("List of all the alias present");
        Enumeration<String> e = trustStore.aliases();
        while (e.hasMoreElements()) {
            System.out.print(e.nextElement() + "");
            System.out.println();
        }


        List<X509Certificate> chain = Arrays.asList(certificateToCheck, intermidiateToCheck2);
//        List<X509Certificate> chain = Arrays.asList(certificateToCheck, intermidiateToCheck2, intermidiateToCheck3);
        CertificateFactory certificateFactory1 = CertificateFactory.getInstance("X.509");
        CertPath certPath1 = certificateFactory1.generateCertPath(chain);
        CertPathValidator certPathValidator = CertPathValidator.getInstance("PKIX");


        PKIXParameters p = new PKIXParameters(trustStore);
        p.setRevocationEnabled(false);

        CertPathValidatorResult certPathValidatorResult = certPathValidator.validate(certPath1, p);



    }


}
