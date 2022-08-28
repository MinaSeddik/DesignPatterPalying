package zipper;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

public class GZipCompression {

    public static void main(String[] args) {

        byte[] data = new byte[100 * 1024 * 1024];
        new Random().nextBytes(data);


    }


    public boolean makeZipWithBuffer(byte[] data) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream zip = new GZIPOutputStream(baos);
        BufferedOutputStream bos = new BufferedOutputStream(zip);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(data);
        oos.close();
        zip.close();

        return true;
    }

    public boolean makeZipWithoutBuffer(byte[] data) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream zip = new GZIPOutputStream(baos);
//        BufferedOutputStream bos = new BufferedOutputStream(zip);
        ObjectOutputStream oos = new ObjectOutputStream(zip);
        oos.writeObject(data);
        oos.close();
        zip.close();

        return true;
    }
}
