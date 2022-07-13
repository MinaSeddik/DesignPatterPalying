package pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.imgscalr.Scalr;
import org.jnbis.api.Jnbis;
import org.jnbis.api.model.Bitmap;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// http://www.cognaxon.com/index.php?page=wsqlib_sunjavanotes
public class PdfMain {

    private static final int[] MASKS = {0x000000ff, 0x000000ff, 0x000000ff};

    public static void main(String[] args) throws IOException, DocumentException {

        String IMG = "C://Users//mina//Desktop//Sample.wsq";
//        String IMG = "C://Users//mina//Desktop//prueba1.wsq";


        String src = "C://Users//mina//Desktop//fd-258-110120.pdf";
        String dest = "C://Users//mina//Desktop//fd-258-110120_test.pdf";

//        String src = "C://Users//mina//Desktop//standard-fingerprint-form-fd-258-1.pdf";
//        String dest = "C://Users//mina//Desktop//standard-fingerprint-form-fd-258-1_test.pdf";
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        AcroFields form = stamper.getAcroFields();

//        form.getFields().keySet().stream().forEach(System.out::println);

        form.setField("First Name", "Mina");
        form.setField("Last Name", "Ibrahim");
        form.setField("PLACE OF BIRTH POB", "EGYPT");

        Bitmap bitmap = Jnbis.wsq()
                .decode(IMG)
                .asBitmap();

        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();

        DataBuffer buffer = new DataBufferByte(bitmap.getPixels(), bitmap.getLength());
        WritableRaster writableRaster = Raster.createPackedRaster(buffer, width, height, width, MASKS, null);
        BufferedImage originalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        BufferedImage originalImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        originalImage.setData(writableRaster);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(originalImage, "BMP", outputStream);
//            byte[] out = outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        InputStream bitMapImage = Jnbis.wsq()
//                .decode(IMG)
//                .toPng()
//                .asInputStream();

//        BufferedImage originalImage = ImageIO.read(bitMapImage);
        BufferedImage resizedImage = Scalr.resize(originalImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, 100, 100);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "BMP", byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();


//        byte[] bytes2 = Jnbis.wsq()
//                .decode(IMG)
//                .toJpg()
//                .asByteArray();


        Image img = Image.getInstance(bytes);
        float x = 1;
        float y = 255;
        float w = img.getScaledWidth();
        float h = img.getScaledHeight();
        System.out.println(w);
        System.out.println(h);

        img.scaleToFit(826, 1100);

        // another way to scale the image
        //------------------------------------------------------------
        //if you would have a chapter indentation
        int indentation = 0;

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("result.pdf"));
        document.open();
        float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                - document.rightMargin() - indentation) / img.getWidth()) * 100;

        img.scalePercent(scaler);
        //------------------------------------------------------------


        img.scaleAbsolute(200, 200);   // not tested
        img.setAbsolutePosition(x, y);

        stamper.getOverContent(1).addImage(img);
        Rectangle linkLocation = new Rectangle(x, y, x + w, y + h);

        PdfDestination destination = new PdfDestination(PdfDestination.FIT);
//        PdfDestination dest = new PdfDestination(PdfDestination.XYZ, 36, 802, 0);


        PdfAnnotation link = PdfAnnotation.createLink(stamper.getWriter(),
                linkLocation, PdfAnnotation.HIGHLIGHT_INVERT,
                reader.getNumberOfPages(), destination);
        link.setBorder(new PdfBorderArray(0, 0, 0));


        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();


    }
}
