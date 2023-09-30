package pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main3 {

    public static void main(String[] args) throws IOException, DocumentException {

        Document document = new Document(PageSize.A4, 0.0f, 0.0f, 0.0f, 0.0f);
        String desPath = "/home/mina/Desktop/image_2_pdf.pdf";
        String imagePath = "/home/mina/Desktop/10336.bmp";

        File file = new File(desPath);

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        PdfWriter pdfWriter = PdfWriter.getInstance(document, fileOutputStream);
        document.open();

        System.out.println("CONVERTER START.....");


        Image image = Image.getInstance(imagePath);
        document.setPageSize(image);
        document.newPage();
        image.setAbsolutePosition(0, 0);
        document.add(image);

        document.close();
        System.out.println("CONVERTER STOPTED.....");
    }
}
