package benchmarck_jmh;

import com.itextpdf.text.DocumentException;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import pdf.PdfMain2;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ThreadBenchmark {

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

//    @Benchmark
//    @BenchmarkMode(Mode.Throughput)
//    @OutputTimeUnit(TimeUnit.SECONDS)
//    public void pdfConversion_single_thread(Blackhole bh) throws DocumentException, IOException {
//        PdfMain2 pdfMain2 = new PdfMain2();
//        boolean t = pdfMain2.extracted();
//        bh.consume(t);
//    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Threads(30)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void pdfConversion_30_threads(Blackhole bh) throws DocumentException, IOException {
        PdfMain2 pdfMain2 = new PdfMain2();
        boolean t = pdfMain2.extracted();
        bh.consume(t);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Threads(40)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void pdfConversion_40_threads(Blackhole bh) throws DocumentException, IOException {
        PdfMain2 pdfMain2 = new PdfMain2();
        boolean t = pdfMain2.extracted();
        bh.consume(t);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Threads(50)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void pdfConversion_50_threads(Blackhole bh) throws DocumentException, IOException {
        PdfMain2 pdfMain2 = new PdfMain2();
        boolean t = pdfMain2.extracted();
        bh.consume(t);
    }

}
