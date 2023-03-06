package aware.wsq1000;

import aware.p2c.Box;
import com.aware.WSQ1000.IWSQ1000;
import com.aware.WSQ1000.ImageFormat;
import com.aware.WSQ1000.WSQ1000Exception;
import com.aware.WSQ1000.WSQ1000JNI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSegmenter {

    public static void main(String[] args) throws IOException {

        String inputImage = "C:\\Users\\Administrator\\Desktop\\13.bmp";
        String outputBmp = "C:\\Users\\Administrator\\Desktop\\segmented.13.bmp";
//        String outputBmp = "C:\\Users\\Administrator\\Desktop\\segmented.13.bmp";

        ImageSegmenter segmenter = new ImageSegmenter();
        Box box = segmenter.getBitMapCoreSegment(inputImage);

        // test it by drawing Rectangle around the core segment
        File imageFile = new File(inputImage);
        BufferedImage image = ImageIO.read(imageFile);
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setColor(Color.BLUE);
        graphics2D.drawRect(box.getTopLeftX(), box.getTopLeftY(), box.getWidth(), box.getHeight());
        graphics2D.dispose();


        File outputfile = new File(outputBmp);
        try {
            ImageIO.write(image, "bmp", outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Box getBitMapCoreSegment(String inputImage) {

        IWSQ1000 wsq1000 = null;
        Box box = null;

        try {

            wsq1000 = new WSQ1000JNI();
            wsq1000.readInputImage(ImageFormat.BMP, inputImage);

            int imageWidth = wsq1000.getInputImageWidth();
            int imageHeight = wsq1000.getInputImageHeight();


            int top = wsq1000.getSegmentedTop();
            int left = wsq1000.getSegmentedLeft();
            int right = wsq1000.getSegmentedRight();
            int bottom = wsq1000.getSegmentedBottom();

            System.out.println("top: " + top);
            System.out.println("left: " + left);
            System.out.println("right: " + right);
            System.out.println("bottom: " + bottom);

            box = new Box(left, top, imageWidth - left - right, imageHeight - top - bottom);

        } catch (WSQ1000Exception ex) {
            System.out.println("Exception : " + ex);
        } finally {
            if (wsq1000 != null)
                wsq1000.destroy();
        }

        return box;
    }
}
