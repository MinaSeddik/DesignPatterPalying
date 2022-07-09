package Cryptographic;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder;
import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipient;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipientInfoGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.OutputEncryptor;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;

public class SigningBouncyCastle {

    public static void main(String[] args) throws CertificateException, NoSuchProviderException, CMSException, IOException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {

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
        PublicKey publicKey = certificate.getPublicKey();




        System.out.println("Default Charset=" + Charset.defaultCharset());
        String message = "Hello from the other world!";
        System.out.println("Sign Original Message : " + message);
        String signature = sign(privateKey, message);
        System.out.println("Message Signature : " + signature);
        boolean isAuthenticatedMessage = verify(publicKey, message, signature);
        System.out.println("Message is Authenticated : " + isAuthenticatedMessage);

    }

    public static String sign(PrivateKey privateKey, String message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException, NoSuchProviderException {
        Signature rsaSign = Signature.getInstance("SHA256withRSA", "BC");
        rsaSign.initSign(privateKey);
        rsaSign.update(message.getBytes("UTF-8"));
        return new String(Base64.encodeBase64(rsaSign.sign()), "UTF-8");
    }

    public static boolean verify(PublicKey publicKey, String message, String signature) throws SignatureException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, UnsupportedEncodingException, NoSuchProviderException {
        Signature rsaVerify = Signature.getInstance("SHA256withRSA", "BC");
        rsaVerify.initVerify(publicKey);
        rsaVerify.update(message.getBytes("UTF-8"));
        return rsaVerify.verify(Base64.decodeBase64(signature.getBytes("UTF-8")));
    }


}
