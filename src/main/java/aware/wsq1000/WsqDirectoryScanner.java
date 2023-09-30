package aware.wsq1000;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.math3.util.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class WsqDirectoryScanner {

    public static void main(String[] args) throws IOException {

//        List<String> dirs = Arrays.asList(
//                "C:\\Users\\Administrator\\Desktop\\ARSENEAULT_JENNIFER",
//                "C:\\Users\\Administrator\\Desktop\\Christina_Hindriksen",
//                "C:\\Users\\Administrator\\Desktop\\MURPHU_THERESA",
//                "C:\\Users\\Administrator\\Desktop\\VELIZ_NELVI",
//                "C:\\Users\\Administrator\\Desktop\\TATRO_PAUL_ARTHUR",
//                "C:\\Users\\Administrator\\Desktop\\Roxana_Ramirez",
//                "C:\\Users\\Administrator\\Desktop\\Ngan_Ngo"
//        );

        List<String> dirs = Arrays.asList(
                "C:\\Users\\Administrator\\Desktop\\finger-prints\\10336",
                "C:\\Users\\Administrator\\Desktop\\finger-prints\\10432",
                "C:\\Users\\Administrator\\Desktop\\finger-prints\\10444",
                "C:\\Users\\Administrator\\Desktop\\finger-prints\\10526",
                "C:\\Users\\Administrator\\Desktop\\finger-prints\\11682"

        );

        dirs.stream().forEach(dir -> {
            try {
                Files.list(Paths.get(dir))
                        .filter(file -> !Files.isDirectory(file) && FilenameUtils.getExtension(file.getFileName().toString()).equals("wsq"))
                        .forEach(file -> convertToBmp(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void convertToBmp(Path file) {

        String inputFile = file.toAbsolutePath().toString();
        String fileName = file.toFile().getName();

        File out = new File(file.getParent().toString(), FilenameUtils.removeExtension(fileName) + ".bmp");
        String outFile = out.getAbsolutePath();

        File scoreFile = new File(file.getParent().toString(), "scores.txt");
        String scoreFilePath = scoreFile.getAbsolutePath();


        System.out.println("Input file: " + inputFile);
        System.out.println("Output file: " + outFile);
        System.out.println("Score file: " + scoreFilePath);

        System.out.println("fileName: " + fileName);

        WsqConverter wsqConverter = new WsqConverter();
        Pair<Integer, Integer> scores = wsqConverter.wsq2Bmp(inputFile, outFile);

        String record = fileName + ", " + scores.getFirst() + ", " + scores.getSecond() + "\n";
        try {
            Files.write(Paths.get(scoreFilePath), record.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
            System.out.println("Exception: " + e);
        }

    }


}
