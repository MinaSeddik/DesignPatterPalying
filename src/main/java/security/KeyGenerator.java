package security;

import org.apache.commons.codec.binary.Hex;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

public class KeyGenerator {

    public static void main(String[] args) {

        StringKeyGenerator keyGenerator = KeyGenerators.string();
        String salt = keyGenerator.generateKey();
        System.out.println(salt);
        System.out.println("______________________________");

        BytesKeyGenerator keyGenerator2 = KeyGenerators.secureRandom(16);
        byte[] key = keyGenerator2.generateKey();
        int keyLength = keyGenerator2.getKeyLength();
        byte[] salt2 = keyGenerator2.generateKey();
        System.out.println(Hex.encodeHexString(salt2));
        System.out.println("______________________________");

        // Generate unique key for each call of the generateKey()
        //  key1 and key2 have the same value
        BytesKeyGenerator keyGenerator3 = KeyGenerators.shared(16);
        byte [] key1 = keyGenerator3.generateKey();
        byte [] key2 = keyGenerator3.generateKey();
        System.out.println(Hex.encodeHexString(key1));
        System.out.println(Hex.encodeHexString(key2));


    }
}
