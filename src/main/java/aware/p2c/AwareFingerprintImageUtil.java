package aware.p2c;

import com.aware.WSQ1000.IWSQ1000;
import com.aware.WSQ1000.ImageFormat;
import com.aware.WSQ1000.WSQ1000Exception;
import com.aware.WSQ1000.WSQ1000JNI;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AwareFingerprintImageUtil {

    public static byte[] convert(byte[] wsqData) {
        IWSQ1000 wsq1000 = null;

        byte[] outBytes = null;
        try {

            wsq1000 = new WSQ1000JNI();

            Path tempFile = Files.createTempFile(null, null);
            Path tempOutFile = Files.createTempFile(null, null);

            Files.write(tempFile, wsqData);
            wsq1000.readInputImage(ImageFormat.WSQ, tempFile.toString());

            wsq1000.setOutputResolution(500d);


            wsq1000.writeOutputImage(ImageFormat.BMP, tempOutFile.toString());

            outBytes = Files.readAllBytes(Paths.get(tempOutFile.toUri()));

        } catch (WSQ1000Exception ex) {
            System.out.println("Exception : " + ex);
        } catch (IOException ex) {
            System.out.println("Exception : " + ex);
            throw new RuntimeException(ex);
        } finally {
            if (wsq1000 != null)
                // AwWsq_Destroy - Page 15
                wsq1000.destroy();
        }

        return outBytes;
    }

    public static void saveBufferedImageAsBitmap500Dpi(BufferedImage bufferedImage, String outputFile) {
        IWSQ1000 wsq1000 = null;
        Path tempFile = null;

        try {

            wsq1000 = new WSQ1000JNI();

            tempFile = Files.createTempFile(null, null);
            byte[] pixels = (byte[]) bufferedImage.getData().getDataElements(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);

            wsq1000.setInputImageRaw(pixels, bufferedImage.getWidth(), bufferedImage.getHeight(), 500d /* ppi resolution - pixel per inch*/, 8 /*bpp - Bits Per Pixel*/);
            wsq1000.setOutputResolution(500d);
            wsq1000.writeOutputImage(ImageFormat.BMP, outputFile);

        } catch (WSQ1000Exception | IOException ex) {
            System.out.println("Exception : " + ex);
        } finally {
            if (wsq1000 != null)
                wsq1000.destroy();
            if (tempFile != null)
                tempFile.toFile().delete();

        }

    }
}
