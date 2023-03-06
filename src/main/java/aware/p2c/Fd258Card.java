package aware.p2c;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class Fd258Card {

    private final static String FD_258_CARD = "FD-258_rev_05_15_2017_front.bmp";
    //    private final static String FD_258_CARD = "4800px4800px600dpi_blank.png";

    private final static String LINE_SEPARATOR = "\n";
    private final static String BITMAP_FORMAT = "bmp";

    private BufferedImage fd258BufferedImage;
    private Graphics2D graphics2D;
    private Fd258LayoutConfig fd258LayoutConfig;

    private Fd258Card() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            BufferedImage coloredBufferedImage = ImageIO.read(classLoader.getResourceAsStream(FD_258_CARD));
            fd258BufferedImage = new BufferedImage(coloredBufferedImage.getWidth(), coloredBufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

            // Convert to gray scale image
            Graphics g = fd258BufferedImage.getGraphics();
            g.drawImage(coloredBufferedImage, 0, 0, null);
            g.dispose();


            graphics2D = fd258BufferedImage.createGraphics();

            fd258LayoutConfig = new Fd258LayoutConfig();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static Fd258Card newCard() {
        return new Fd258Card();
    }

    public Fd258Card setTextField(String fieldName, String value) {

        if (Objects.isNull(value))
            return this;

        TextFieldConfig textFieldConfig = fd258LayoutConfig.getTextFieldConfig(fieldName);
        graphics2D.setFont(textFieldConfig.getFont());
        graphics2D.setColor(textFieldConfig.getColor());


        Box box = textFieldConfig.getBox();

        String[] lines = value.split(LINE_SEPARATOR);
        if (lines.length > 1) {
            graphics2D.drawString(lines[0], box.getTopLeftX(), box.getTopLeftY() + box.getHeight() / 2);
            graphics2D.drawString(lines[1], box.getTopLeftX(), box.getTopLeftY() + box.getHeight());
        } else {
            graphics2D.drawString(value, box.getTopLeftX(), box.getTopLeftY() + box.getHeight());
        }


        return this;
    }

    public Fd258Card renderFingerPrint(FingerPrintPosition fingerPrintPosition, String fingerPrintFileName) {

        ImageFieldConfig imageFieldConfig = fd258LayoutConfig.getImageFieldConfig(fingerPrintPosition);
        Box box = imageFieldConfig.getBox();

        try {
            File imageFile = new File(fingerPrintFileName);
            BufferedImage image = ImageIO.read(imageFile);

            if (!box.fits(image)) {
                image = scaleImageToFitTargetBox(image, box);
            }

            // center the image
            int offsetX = (box.getWidth() - image.getWidth()) / 2;
            int offsetY = (box.getHeight() - image.getHeight()) / 2;

            graphics2D.drawImage(image, box.getTopLeftX() + offsetX, box.getTopLeftY() + offsetY, image.getWidth(), image.getHeight(), null);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return this;
    }

    public Fd258Card renderSignature(String fingerPrintFileName) {

        ImageFieldConfig imageFieldConfig = fd258LayoutConfig.getImageFieldConfig(Fd258Field.SIGNATURE);
        Box box = imageFieldConfig.getBox();

        try {
            File imageFile = new File(fingerPrintFileName);
            BufferedImage image = ImageIO.read(imageFile);

            if (!box.fits(image)) {
                image = scaleImageToFitTargetBox(image, box);
            }

            // center the image
            int offsetX = (box.getWidth() - image.getWidth()) / 2;
            int offsetY = (box.getHeight() - image.getHeight()) / 2;

            graphics2D.drawImage(image, box.getTopLeftX() + offsetX, box.getTopLeftY() + offsetY, image.getWidth(), image.getHeight(), null);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return this;
    }

    private BufferedImage scaleImageToFitTargetBox(BufferedImage image, Box box) {
        Scalr.Mode resizeMode = box.getWidth() <= image.getWidth() ?
                Scalr.Mode.FIT_TO_WIDTH : Scalr.Mode.FIT_TO_HEIGHT;

        return Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, resizeMode,
                box.getWidth(), box.getHeight(), Scalr.OP_ANTIALIAS);
    }

    public void saveAsBitmap(File filePath) {
        saveAsBitmap(filePath.getAbsoluteFile());
    }

    public void saveAsBitmap(String filePath) {

        graphics2D.dispose();
        AwareFingerprintImageUtil.saveBufferedImageAsBitmap500Dpi(fd258BufferedImage, filePath);
    }

    public void saveAsBitmap2(String filePath) {

        graphics2D.dispose();
        BmpStorageUtil.saveBufferedImageAsBitmap600Dpi(fd258BufferedImage, filePath);
    }
    public InputStream bitmapAsInputStreamStream() {
        InputStream inputStream;

        graphics2D.dispose();
        try {
            Path tempFilePath = Files.createTempFile(null, null);
            AwareFingerprintImageUtil.saveBufferedImageAsBitmap500Dpi(fd258BufferedImage, tempFilePath.toString());
            inputStream = Files.newInputStream(tempFilePath, StandardOpenOption.DELETE_ON_CLOSE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return inputStream;
    }

    public void saveAsPdf(String filePath) {
        saveAsPdf(new File(filePath));
    }

    public void saveAsPdf(File filePath) {

//        try {
//            graphics2D.dispose();


        // TODO to be implemented
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public OutputStream pdfAsOutputStream() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        try {
//            graphics2D.dispose();
//            ImageIO.write(fd258BufferedImage, BITMAP_FORMAT, byteArrayOutputStream);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        return byteArrayOutputStream;
    }
}
