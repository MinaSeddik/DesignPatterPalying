package filelock;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.StandardOpenOption;

public class FileLockMain {

    public static void main(String[] args) throws FileNotFoundException {

        // Method 1
        try (FileOutputStream fileOutputStream = new FileOutputStream("/tmp/testfile.txt")) {
            FileChannel channel = fileOutputStream.getChannel();

            FileLock lock1 = channel.lock();
            FileLock lock2 = channel.lock(10, 20, true);


            // write to the channel
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Method 2
//        FileInputStream fileInputStream = new FileInputStream("/tmp/testfile.txt");
//        try (FileChannel channel = FileChannel.open("/path", StandardOpenOption.APPEND)) {
//            // write to channel
//        }

        // Method 3
        try (FileInputStream fileInputStream2 = new FileInputStream("/tmp/testfile.txt");
             FileChannel channel = fileInputStream2.getChannel();
             FileLock lock = channel.lock(0, Long.MAX_VALUE, true)) {
            // read from the channel
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
