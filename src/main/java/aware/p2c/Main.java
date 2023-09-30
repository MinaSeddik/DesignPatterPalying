package aware.p2c;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static aware.p2c.FingerPrintPosition.*;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {

//        String sourceDir = "C:\\Users\\Administrator\\Desktop\\ARSENEAULT_JENNIFER";
//        String sourceDir = "C:\\Users\\Administrator\\Desktop\\Christina_Hindriksen";
//        String sourceDir = "C:\\Users\\Administrator\\Desktop\\MURPHU_THERESA";
//        String sourceDir = "C:\\Users\\Administrator\\Desktop\\VELIZ_NELVI";
//        String sourceDir = "C:\\Users\\Administrator\\Desktop\\TATRO_PAUL_ARTHUR";
//        String sourceDir = "C:\\Users\\Administrator\\Desktop\\Roxana_Ramirez";
//        String sourceDir = "C:\\Users\\Administrator\\Desktop\\Ngan_Ngo";

        List<String> dirs = Arrays.asList(
                "C:\\Users\\Administrator\\Desktop\\ARSENEAULT_JENNIFER",
                "C:\\Users\\Administrator\\Desktop\\Christina_Hindriksen",
                "C:\\Users\\Administrator\\Desktop\\MURPHU_THERESA",
                "C:\\Users\\Administrator\\Desktop\\VELIZ_NELVI",
                "C:\\Users\\Administrator\\Desktop\\TATRO_PAUL_ARTHUR",
                "C:\\Users\\Administrator\\Desktop\\Roxana_Ramirez",
                "C:\\Users\\Administrator\\Desktop\\Ngan_Ngo"
                );

        dirs.stream().forEach(dir -> generateFd258(dir, new File(dir, "fd-258_OUT.bmp").getAbsolutePath()));



    }

    private static void generateFd258(String sourceDir, String outputFile) {
        Instant start = Instant.now();

        DataParser data = new DataParser(new File(sourceDir, "data.json").getAbsolutePath());


        Fd258Card fd258Card = Fd258Card.newCard();


        fd258Card.setTextField(Fd258Field.APPLICANT_NAME,
                        String.format("%s, %s %s", data.getLastName(), data.getFirstName(),
                                data.getMiddleName() == null ? "" : data.getMiddleName()))
                .setTextField(Fd258Field.ORI,data.getORI())
                .setTextField(Fd258Field.DATE_OF_BIRTH,data.getDateOfBirth())
                .setTextField(Fd258Field.RESIDENCE_OF_PERSON_FINGERPRINTED,data.getAddress())
                .setTextField(Fd258Field.COMPLETED_DATE,data.getCompilationDate())
                .setTextField(Fd258Field.REASON_FINGERPRINTED,data.getReasonFingerPrinted())
                .setTextField(Fd258Field.CITIZENSHIP,data.getCitizenShip())
                .setTextField(Fd258Field.OCA,data.getOCA())
                .setTextField(Fd258Field.SSN,data.getSSN())
                .setTextField(Fd258Field.SEX,data.getSex())
                .setTextField(Fd258Field.RACE,data.getRace())
                .setTextField(Fd258Field.HEIGHT,data.getHeight())
                .setTextField(Fd258Field.WIGHT,data.getWight())
                .setTextField(Fd258Field.EYES,data.getEyes())
                .setTextField(Fd258Field.HAIR,data.getHair())
                .setTextField(Fd258Field.PLACE_OF_BIRTH,data.getPlaceOfBirth())

                .renderFingerPrint(FPI_01, new File(sourceDir, "1.bmp").getAbsolutePath())
                .renderFingerPrint(FPI_02, new File(sourceDir, "2.bmp").getAbsolutePath())
                .renderFingerPrint(FPI_03, new File(sourceDir, "3.bmp").getAbsolutePath())
                .renderFingerPrint(FPI_04, new File(sourceDir, "4.bmp").getAbsolutePath())
                .renderFingerPrint(FPI_05, new File(sourceDir, "5.bmp").getAbsolutePath())
                .renderFingerPrint(FPI_06, new File(sourceDir, "6.bmp").getAbsolutePath())
                .renderFingerPrint(FPI_07, new File(sourceDir, "7.bmp").getAbsolutePath())
                .renderFingerPrint(FPI_08, new File(sourceDir, "8.bmp").getAbsolutePath())
                .renderFingerPrint(FPI_09, new File(sourceDir, "9.bmp").getAbsolutePath())
                .renderFingerPrint(FPI_10, new File(sourceDir, "10.bmp").getAbsolutePath())
                .renderFingerPrint(FPI_11, new File(sourceDir, "11.bmp").getAbsolutePath())
                .renderFingerPrint(FPI_12, new File(sourceDir, "12.bmp").getAbsolutePath())
                .renderFingerPrint(FPI_13, new File(sourceDir, "13.bmp").getAbsolutePath())
                .renderFingerPrint(FPI_14, new File(sourceDir, "14.bmp").getAbsolutePath())

                .renderSignature(new File(sourceDir, "signature.png").getAbsolutePath())

//                .saveAsBitmap(outputFile);
                .saveAsBitmap2(outputFile);

        Instant finish = Instant.now();

        System.out.println("Time elapsed: " + Duration.between(start, finish).toMillis() + " milli-seconds");
    }
}
