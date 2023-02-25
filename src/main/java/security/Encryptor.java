package security;

import org.apache.commons.codec.binary.Hex;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class Encryptor {

    public static void main(String[] args) {

        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrypt = "HELLO";

        System.out.println("Plain text: " + valueToEncrypt);

        // uses cipher block chaining (CBC) 256-bit
//        BytesEncryptor e = Encryptors.standard(password, salt);

        // uses Galois/Counter Mode (GCM) 256-bit
        BytesEncryptor e = Encryptors.stronger(password, salt);

//        It uses 256-byte AES encryption to encrypt input.
        byte [] encrypted = e.encrypt(valueToEncrypt.getBytes());
        System.out.println("Encrypted data - Hexa: " + Hex.encodeHexString(encrypted));

        byte [] decrypted = e.decrypt(encrypted);
        System.out.println("Decrypted data: " + new String(decrypted));

        System.out.println("______________________________");

        valueToEncrypt = "HELLO";
        System.out.println("Plain text: " + valueToEncrypt);

        TextEncryptor te = Encryptors.noOpText();
        String encrypted2 = te.encrypt(valueToEncrypt);
        System.out.println("Encrypted data: " + encrypted2);

        System.out.println("______________________________");


//        For Encryptors.text() and Encryptors.delux(), the encrypt() method
//        called on the same input repeatedly generates different outputs.

        String saltx = KeyGenerators.string().generateKey();
        String passwordx = "secret";
        String valueToEncryptx = "HELLO";

        System.out.println("Plain text: " + valueToEncryptx);
        TextEncryptor textEncryptor = Encryptors.text(passwordx, saltx);
        String encryptedx = textEncryptor.encrypt(valueToEncryptx);
        String encryptedx1 = textEncryptor.encrypt(valueToEncryptx);

        System.out.println("Encrypted data 1 : " + encryptedx);
        System.out.println("Encrypted data 2 : " + encryptedx1);

        String decryptedx = textEncryptor.decrypt(encryptedx);
        System.out.println("Decrypted data: " + decryptedx);

        System.out.println("______________________________");



        String salty = KeyGenerators.string().generateKey();
        String passwordy = "secret";
        String valueToEncrypty = "HELLO";

        System.out.println("Plain text: " + valueToEncrypty);
        TextEncryptor textEncryptor2 = Encryptors.queryableText(passwordy, salty);
        String encryptedy = textEncryptor2.encrypt(valueToEncrypty);
        String encryptedy1 = textEncryptor2.encrypt(valueToEncrypty);

        System.out.println("Encrypted data 1 : " + encryptedy);
        System.out.println("Encrypted data 2 : " + encryptedy1);

        String decryptedy = textEncryptor2.decrypt(encryptedy);
        System.out.println("Decrypted data: " + decryptedy);

    }
}
