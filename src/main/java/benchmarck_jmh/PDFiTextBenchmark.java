package benchmarck_jmh;

import com.itextpdf.text.DocumentException;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import pdf.PdfMain2;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PDFiTextBenchmark {

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }


    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 2)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testIntern2(Blackhole bh) throws DocumentException, IOException {
        PdfMain2 pdfMain2 = new PdfMain2();
        boolean t = pdfMain2.extracted();
        bh.consume(t);
    }
}
