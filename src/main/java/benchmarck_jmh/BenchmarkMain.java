package benchmarck_jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class BenchmarkMain {

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @Fork(value = 3, warmups = 2)
    //means that 5 forks will be run sequentially. The first two will be warmup runs which will be ignored, and the final 3 will be used for benchmarking.
    @Warmup(iterations = 5, time = 55, timeUnit = TimeUnit.MILLISECONDS)
    // means that there will be 5 warmup iterations within each fork. The timings from these runs will be ignored when producing the benchmark results.
    @Measurement(iterations = 4, time = 44, timeUnit = TimeUnit.MILLISECONDS)
    //means that your benchmark iterations will be run 4 times (after the 5 warmup iterations).
    public void testIntern(Blackhole bh) {
        for (int i = 0; i < 10000; i++) {
            String s = new String("String to intern " + i);
            String t = s.intern();
            bh.consume(t);
        }
    }


    @Benchmark
    public void testInternWithoutParameter(Blackhole bh) {
        for (int i = 0; i < 10000; i++) {
            String s = new String("String to intern " + i);
            String t = s.intern();
            bh.consume(t);
        }
    }



    @Benchmark
    @BenchmarkMode(Mode.All)
//    @Fork(jvmArgsAppend = {"-Xms2G", "-Xmx5G", "-XX:+UseG1GC", "-XX:+PrintGCDetails", "-XX:+PrintCommandLineFlags"}, value = 1, warmups = 2)
    @Fork(value = 1, warmups = 2)
    @Measurement(iterations = 3)
    @Warmup(iterations = 1)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void testIntern2(Blackhole bh) {
        for (int i = 0; i < 10000; i++) {
            String s = new String("String to intern " + i);
            String t = s.intern();
            bh.consume(t);
        }
    }

}
