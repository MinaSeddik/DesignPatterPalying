package pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class PdfMain2 {


    public static void main(String[] args) throws IOException, DocumentException {

        PdfMain2 pdfMain2 = new PdfMain2();
        pdfMain2.extracted();

    }

    public boolean extracted() throws IOException, DocumentException {
        String userHome = System.getProperty("user.home");
        File desktopDir = new File(userHome, "Desktop");
        String inputFilename = "fd-258-110120.pdf";
        File inputPdfFile = new File(desktopDir, inputFilename);


        File tempDir = new File(desktopDir, "temp");
        if (! tempDir.exists()){
            tempDir.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }


//        String src = "C://Users//mina//Desktop//fd-258-110120.pdf";

//        String src = "C://Users//mina//Desktop//fd-258-x.pdf";
//        String src = "C://Users//mina//Desktop//fd-258-y.pdf";
        PdfReader reader = new PdfReader(inputPdfFile.getAbsolutePath());

//        PRAcroForm prAcroForm = reader.getAcroForm();
//        ArrayList<PRAcroForm.FieldInformation> list = prAcroForm.getFields();
//        for (PRAcroForm.FieldInformation item : list) {
//            System.out.println(item.getName());
//        }
//        System.out.println("<<-------------------------------------------------->>" + list.size());
//
//
//        AcroFields form = reader.getAcroFields();
//        Map<String, AcroFields.Item> fields = form.getFields();
//        String key;
//        for (Iterator i = fields.keySet().iterator(); i.hasNext(); ) {
//            key = (String) i.next();
//            System.out.println(key);
//            System.out.println(form.getFieldType(key));
//            System.out.println();
//        }

        Random random = new Random();

//        String outputFilename = "fd-258-110120_test" + random.nextInt();
//        String dest = "C://Users//mina//Desktop//" + filename + ".pdf";

        String outputFilename = "fd-258-110120_test" + random.nextInt() + ".pdf";
        File outputPdfFile = new File(tempDir, outputFilename);

        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputPdfFile.getAbsoluteFile()));
        AcroFields acroFields = stamper.getAcroFields();


//        acroFields.setField("undefined", "XXXXXXXXX");
//        acroFields.setField("Row1", "YYYYYYYYYYY");
//        acroFields.setField("3 ARCH", "ZZZZZZZZZZZZ");


//        String imageFile = "C://Users//mina//Desktop//Sample.JPG";
//        String imageFile = "C://Users//mina//Desktop//red.jpg";

        String imgFilename = "red.png";
        File imgFile = new File(desktopDir, imgFilename);
        String imageFile = imgFile.getAbsolutePath();

        float ROLL_IMAGE_WIDTH = 105f;
        float ROLL_IMAGE_HEIGHT = 95f;

        float FLAT_FOUR_IMAGE_WIDTH = 105f;
        float FLAT_FOUR_IMAGE_HEIGHT = 220f;

        float FLAT_THUMB_IMAGE_WIDTH = 105f;
        float FLAT_THUMB_IMAGE_HEIGHT = 45f;


        // (1) Right thumb image
        float R_Thumb_X = 5f, R_Thumb_Y = 265f;
        addImage(stamper, imageFile, R_Thumb_X, R_Thumb_Y, ROLL_IMAGE_WIDTH, ROLL_IMAGE_HEIGHT);

        // (2) Right index image
        float R_Index_X = 120f, R_Index_Y = 265f;
        addImage(stamper, imageFile, R_Index_X, R_Index_Y, ROLL_IMAGE_WIDTH, ROLL_IMAGE_HEIGHT);

        // (3) Right middle image
        float R_Middle_X = 235f, R_Middle_Y = 265f;
        addImage(stamper, imageFile, R_Middle_X, R_Middle_Y, ROLL_IMAGE_WIDTH, ROLL_IMAGE_HEIGHT);

        // (4) Right ring image
        float R_Ring_X = 350f, R_Ring_Y = 265f;
        addImage(stamper, imageFile, R_Ring_X, R_Ring_Y, ROLL_IMAGE_WIDTH, ROLL_IMAGE_HEIGHT);

        // (5) Right little image
        float R_Little_X = 465f, R_Little_Y = 265f;
        addImage(stamper, imageFile, R_Little_X, R_Little_Y, ROLL_IMAGE_WIDTH, ROLL_IMAGE_HEIGHT);

        // (6) Left thumb image
        float L_Thumb_X = 5f, L_Thumb_Y = 155f;
        addImage(stamper, imageFile, L_Thumb_X, L_Thumb_Y, ROLL_IMAGE_WIDTH, ROLL_IMAGE_HEIGHT);

        // (7) Left index image
        float L_Index_X = 120f, L_Index_Y = 155f;
        addImage(stamper, imageFile, L_Index_X, L_Index_Y, ROLL_IMAGE_WIDTH, ROLL_IMAGE_HEIGHT);

        // (8) Left middle image
        float L_Middle_X = 235f, L_Middle_Y = 155f;
        addImage(stamper, imageFile, L_Middle_X, L_Middle_Y, ROLL_IMAGE_WIDTH, ROLL_IMAGE_HEIGHT);

        // (9) Left ring image
        float L_Ring_X = 350f, L_Ring_Y = 155f;
        addImage(stamper, imageFile, L_Ring_X, L_Ring_Y, ROLL_IMAGE_WIDTH, ROLL_IMAGE_HEIGHT);

        // (10) Left little image
        float L_Little_X = 465f, L_Little_Y = 155f;
        addImage(stamper, imageFile, L_Little_X, L_Little_Y, ROLL_IMAGE_WIDTH, ROLL_IMAGE_HEIGHT);

        // (11) Left little image
        float L_FOUR_FLAT_X = 5f, L_FOUR_FLAT_Y = 20f;
        addImage(stamper, imageFile, L_FOUR_FLAT_X, L_FOUR_FLAT_Y, FLAT_FOUR_IMAGE_WIDTH, FLAT_FOUR_IMAGE_HEIGHT);

        // (12) Left thumb image
        float L_THUMB_FLAT_X = 235f, L_THUMB_FLAT_Y = 20f;
        addImage(stamper, imageFile, L_THUMB_FLAT_X, L_THUMB_FLAT_Y, FLAT_THUMB_IMAGE_WIDTH, FLAT_THUMB_IMAGE_HEIGHT);

        // (13) Right thumb image
        float R_THUMB_FLAT_X = 292f, R_THUMB_FLAT_Y = 20f;
        addImage(stamper, imageFile, R_THUMB_FLAT_X, R_THUMB_FLAT_Y, FLAT_THUMB_IMAGE_WIDTH, FLAT_THUMB_IMAGE_HEIGHT);

        // (14) Right little image
        float R_FOUR_FLAT_X = 350f, R_FOUR_FLAT_Y = 20f;
        addImage(stamper, imageFile, R_FOUR_FLAT_X, R_FOUR_FLAT_Y, FLAT_FOUR_IMAGE_WIDTH, FLAT_FOUR_IMAGE_HEIGHT);


        // Add text
        addText(stamper, "Your Text Goes here!! ", 5, 20, 150, 50);

        stamper.close();
        reader.close();

        return true;
    }


    private void addImage(PdfStamper stamper, String imageFile, float xPos, float yPos, float width, float height) throws IOException, DocumentException {

        Rectangle rect = new Rectangle(0, 0, width, height);

        Image image = Image.getInstance(imageFile);
        image.scaleToFit(rect.getWidth(), rect.getHeight());
        image.setAbsolutePosition(xPos, yPos);
        PdfContentByte pdfContentByte = stamper.getOverContent(1);
        pdfContentByte.addImage(image);
        stamper.setFormFlattening(true);
    }

    private void addText(PdfStamper stamper, String text, float xPos, float yPos, float width, float height) throws DocumentException {

        PdfContentByte canvas = stamper.getOverContent(1);

        Rectangle rect = new Rectangle(xPos, yPos, width, height);
        rect.setBorder(Rectangle.BOX);
//        rect.setBorderWidth(1);
//        rect.setBackgroundColor(BaseColor.WHITE);
//        rect.setBorderColor(BaseColor.BLACK);
        canvas.rectangle(rect);

        ColumnText ct = new ColumnText(canvas);
        ct.setSimpleColumn(rect);
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        ct.addElement(new Paragraph(text, catFont));
        ct.go();
    }


}
