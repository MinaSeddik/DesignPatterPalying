package Cryptographic;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.pkcs.ContentInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SigningBouncyCastle_PKCS7 {

    public static void main(String[] args) throws CertificateException, NoSuchProviderException, CMSException, IOException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, OperatorCreationException {

        Security.addProvider(new BouncyCastleProvider());

        // (1) get Private key to sign a message
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("myPrivateKeyStore.p12");
        keystore.load(input, "Mina1234".toCharArray());
        PrivateKey privateKey = (PrivateKey) keystore.getKey("myKey", "Mina1234".toCharArray());

        // (2) get the public key to verify the message
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        input = classLoader.getResourceAsStream("mySelfSignedCertificate.crt");
        X509Certificate certificate = (X509Certificate) certFactory.generateCertificate(input);


        System.out.println("Default Charset=" + Charset.defaultCharset());
        String message = "Hello from the other world!";
        System.out.println("Sign Original Message : " + message);
        byte[] signedData = signDataPKCS7(message.getBytes(), certificate, privateKey);
        System.out.println("Signed Message : " );
        System.out.println(new String(signedData));

        // save to a file
        try (FileOutputStream fos = new FileOutputStream("/home/mina/Desktop/signedData.p7m")) {
            fos.write(signedData);
        }
        // to verify and extract content
        // openssl smime -verify -CAfile mySelfSignedCertificate.crt -in signedData.p7m -inform DER -out content

        // extract content without verification
        //openssl smime -verify -noverify -in signedData.p7m -inform DER -out content


        boolean verified = verifySignDataPKCS7(signedData);
        System.out.println("Message is Verified : " + verified);

    }

    public static byte[] signDataPKCS7(byte[] data, final X509Certificate signingCertificate, final PrivateKey signingKey) throws CertificateEncodingException, OperatorCreationException, CMSException, IOException, CertificateEncodingException {
        byte[] signedMessage;
        List<X509Certificate> certList = new ArrayList<>();
        CMSTypedData cmsData = new CMSProcessableByteArray(data);
        certList.add(signingCertificate);
        Store certs = new JcaCertStore(certList);
        CMSSignedDataGenerator cmsGenerator = new CMSSignedDataGenerator();
        ContentSigner contentSigner = new JcaContentSignerBuilder("SHA256withRSA").build(signingKey);
        cmsGenerator.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider("BC").build()).build(contentSigner, signingCertificate));
        cmsGenerator.addCertificates(certs);
        CMSSignedData cms = cmsGenerator.generate(cmsData, true);
        signedMessage = cms.getEncoded();
        return signedMessage;
    }

    public static boolean verifySignDataPKCS7(final byte[] signedData) throws CMSException, IOException, OperatorCreationException, CertificateException {
        ByteArrayInputStream bIn = new ByteArrayInputStream(signedData);
        ASN1InputStream aIn = new ASN1InputStream(bIn);
        CMSSignedData cmsSignedData = new CMSSignedData(org.bouncycastle.asn1.cms.ContentInfo.getInstance(ContentInfo.getInstance(aIn.readObject())));
        aIn.close();
        bIn.close();
        Store certs = cmsSignedData.getCertificates();

//        byte[] content = (byte[]) cmsSignedData.getSignedContent().getContent();
//        System.out.println("**********************************");
//        System.out.println(new String(content));

        SignerInformationStore signers = cmsSignedData.getSignerInfos();
        Collection<SignerInformation> c = signers.getSigners();
        SignerInformation signer = c.iterator().next();
        Collection<X509CertificateHolder> certCollection = certs.getMatches(signer.getSID());
        Iterator<X509CertificateHolder> certIt = certCollection.iterator();
        X509CertificateHolder certHolder = certIt.next();
        boolean verifyResult = signer.verify(new JcaSimpleSignerInfoVerifierBuilder().build(certHolder));

        return verifyResult;
    }


}
