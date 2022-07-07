package Cryptographic;

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

public class CertificateValidation {


    public static void main(String[] args) throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, CertPathBuilderException, CertPathValidatorException {


        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        InputStream input = classLoader.getResourceAsStream("server.crt");
//        InputStream input = classLoader.getResourceAsStream("google.cer");
        X509Certificate certificateToCheck = (X509Certificate) certificateFactory.generateCertificate(input);
        System.out.println("***  Subject:" + certificateToCheck.getSubjectDN());
        System.out.println("***  Issuer:" + certificateToCheck.getIssuerDN());

        KeyStore trustStore = KeyStore.getInstance("JKS");
        InputStream keyStoreStream = classLoader.getResourceAsStream("rootCA_only.jks");
//        InputStream keyStoreStream = classLoader.getResourceAsStream("intermidiate_only.jks");
//        InputStream keyStoreStream = classLoader.getResourceAsStream("root_and_intermidiate.jks");

//        InputStream keyStoreStream = classLoader.getResourceAsStream("google_test.jks");
        trustStore.load(keyStoreStream, "Mina1234".toCharArray());

        System.out.println(trustStore.size());
//        System.out.println("List of all the alias present");
//        Enumeration<String> e = trustStore.aliases();
//        while (e.hasMoreElements()) {
//            System.out.print(e.nextElement() + "");
//            System.out.println();
//        }

//        PKIXParameters params = new PKIXParameters(trustStore);
//        Set<TrustAnchor> trustAnchors = params.getTrustAnchors();
//        List<Certificate> certificates = trustAnchors.stream()
//                .map(TrustAnchor::getTrustedCert)
//                .collect(Collectors.toList());
//
//        System.out.println("trustAnchors size: " + trustAnchors.size());
//        System.out.println("certificates size: " + certificates.size());
//
//        for(Certificate crt : certificates){
//            X509Certificate current = (X509Certificate) crt;
//            System.out.println(">>>  Subject:" + current.getSubjectDN());
//            System.out.println(">>>  Issuer:" + current.getIssuerDN());
//            System.out.println("+++++++");
//        }


//        CertificateFactory certificateFactory1 = CertificateFactory.getInstance("X.509");
//        List<Certificate> certx = trustAnchors.stream()
//                .map(TrustAnchor::getTrustedCert)
//                .collect(Collectors.toList());
//        CertPath certPath = certificateFactory1.generateCertPath(certx);

        X509CertSelector certSelector = new X509CertSelector();
        certSelector.setCertificate(certificateToCheck);

        CertPathBuilder certPathBuilder = CertPathBuilder.getInstance("PKIX");
        CertPathParameters certPathParameters = new PKIXBuilderParameters(trustStore, certSelector);
        CertPathBuilderResult certPathBuilderResult = certPathBuilder.build(certPathParameters);
        CertPath certPath = certPathBuilderResult.getCertPath();

        CertPathValidator certPathValidator = CertPathValidator.getInstance("PKIX");
        PKIXParameters validationParameters = new PKIXParameters(trustStore);
        validationParameters.setRevocationEnabled(false); // if you want to check CRL   <----- check it again


//        X509CertSelector keyUsageSelector = new X509CertSelector();
//        keyUsageSelector.setKeyUsage(new boolean[]{true, false, true}); // to check digitalSignature and keyEncipherment bits
//        validationParameters.setTargetCertConstraints(keyUsageSelector);
//
//

        PKIXCertPathValidatorResult result = (PKIXCertPathValidatorResult) certPathValidator.validate(certPath, validationParameters);

        System.out.println(result);
    }


}
