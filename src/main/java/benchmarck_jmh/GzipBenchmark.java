package benchmarck_jmh;

import com.itextpdf.text.DocumentException;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import pdf.PdfMain2;
import zipper.GZipCompression;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@State(Scope.Benchmark)
public class GzipBenchmark {

//    @Param

    private byte[] data = new byte[100 * 1024 * 1024];

    @Setup(Level.Trial)
    public void doSetup() {

//        private byte[] data = new byte[100 * 1024 * 1024];
//        System.out.println("Do Setup");
        new Random().nextBytes(data);

    }


    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }


    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 2)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void gzipWithBuffer(Blackhole bh) throws DocumentException, IOException {
        GZipCompression gZipCompression = new GZipCompression();
        boolean t = gZipCompression.makeZipWithBuffer(data);
        bh.consume(t);
    }


    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 2)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void gzipWithoutBuffer(Blackhole bh) throws DocumentException, IOException {
        GZipCompression gZipCompression = new GZipCompression();
        boolean t = gZipCompression.makeZipWithoutBuffer(data);
        bh.consume(t);
    }

}
