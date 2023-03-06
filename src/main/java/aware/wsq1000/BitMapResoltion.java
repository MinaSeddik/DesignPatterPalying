package aware.wsq1000;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BitMapResoltion {


    public static void main(String[] args) throws FileNotFoundException {


        File output = new File("C:\\Users\\Administrator\\Desktop\\ARSENEAULT_JENNIFER\\fd-258_OUT.bmp");
//        File output = new File("C:\\Users\\Administrator\\Desktop\\FD-258_rev_05_15_2017_front.bmp");
//        File output = new File("C:\\Users\\Administrator\\Desktop\\fd-258_OUT.bmp");

        try (RandomAccessFile f = new RandomAccessFile(output, "rw")) {
            f.seek(38);
//            System.out.println(f.read());
            f.write(0);
            f.seek(42);
//            System.out.println(f.read());
            f.write(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
