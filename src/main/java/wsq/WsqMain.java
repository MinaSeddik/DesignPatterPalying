package wsq;

import org.apache.commons.codec.binary.Hex;
import org.jnbis.api.Jnbis;
import org.jnbis.api.model.Nist;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class WsqMain {


    public static void main(String[] args) throws IOException {

//        String input = "C://Users//mina//Desktop//Sample.wsq";
        String input = "C://Users//mina//Desktop//prueba1.wsq";
        String output = "C://Users//mina//Desktop//wsq_converted2.jpg";

        String encodedString = "/9j/4BCSTlYShABAQEAYABjCCF";

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        System.out.println( Hex.encodeHexString( decodedBytes ) );

        Path path = Paths.get(input);
        byte[] data = Files.readAllBytes(path);
        System.out.println( Hex.encodeHexString( data ) );

        File png = Jnbis.wsq()
                .decode(decodedBytes)
                .toJpg()
                .asFile(output);

        Nist nist = Jnbis.nist().decode(new File("/path/to/nist/file"));


    }


}
