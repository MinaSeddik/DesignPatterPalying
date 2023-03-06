package aware.p2c;

import javax.imageio.*;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;

public class BmpStorageUtil {

    private static final double INCH_2_CM = 2.54;

    public static void saveBufferedImageAsBitmap600Dpi(BufferedImage bufferedImage, String outputFile) {

//        String formatName = "bmp";
        String formatName = "png";

        System.out.println("I'm here .....");
        System.out.println("I'm here ..... " + outputFile);



        for (Iterator<ImageWriter> iw = ImageIO.getImageWritersByFormatName(formatName); iw.hasNext(); ) {
            ImageWriter writer = iw.next();
            ImageWriteParam writeParam = writer.getDefaultWriteParam();
            ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_BYTE_GRAY);
//            ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);
            IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, writeParam);
            System.out.println("xxxxxxxx");
            if (metadata.isReadOnly() || !metadata.isStandardMetadataFormatSupported()) {
                continue;
            }
            System.out.println("yyyyyyyyy");
            try {
                setDPI(metadata);
            } catch (IIOInvalidTreeException e) {
                throw new RuntimeException(e);
            }

            try {
                ImageOutputStream stream = ImageIO.createImageOutputStream(outputFile);
                System.out.println("stream = " + stream);
                System.exit(0);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try (ImageOutputStream stream = ImageIO.createImageOutputStream(outputFile)) {
                writer.setOutput(stream);
                System.out.println(stream);
                writer.write(metadata, new IIOImage(bufferedImage, null, metadata), writeParam);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            break;
        }

    }

    private static void setDPI(IIOMetadata metadata) throws IIOInvalidTreeException {

        System.out.println("inside setDPI ....");

        // for PMG, it's dots per millimeter
        double dotsPerMilli = 1.0 * 600 / 10 / INCH_2_CM;

        IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
        horiz.setAttribute("value", Double.toString(dotsPerMilli));

        IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
        vert.setAttribute("value", Double.toString(dotsPerMilli));

        IIOMetadataNode dim = new IIOMetadataNode("Dimension");
        dim.appendChild(horiz);
        dim.appendChild(vert);

        IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
        root.appendChild(dim);

        metadata.mergeTree("javax_imageio_1.0", root);
    }
}
