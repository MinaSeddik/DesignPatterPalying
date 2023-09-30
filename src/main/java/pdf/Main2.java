package pdf;


import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;

public class Main2 {
//    public static final String PDF_FILE = "/home/mina/Desktop/Spring Security in Action (Laurentiu Spilca) (z-lib.org).pdf";
//    public static final String PDF_FILE = "/home/mina/Desktop/fd-258_Thomas-Leonard.pdf";
    public static final String PDF_FILE = "/home/mina/Desktop/image_2_pdf.pdf";

    public static void main(String[] args) throws Exception {

        PdfReader pdfReader = new PdfReader(PDF_FILE);
        int pagesCount = pdfReader.getNumberOfPages();
        System.out.println("PDF Page Count: " + pagesCount);

        Rectangle pageSize = pdfReader.getPageSize(1);
        System.out.println(String.format(
                "Page Size: %f x %f", pageSize.getWidth(), pageSize.getHeight()));


    }
}