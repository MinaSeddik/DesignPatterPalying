package benchmarck_jmh;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BenchmarkConfig {

    public static void main(String[] ignore) throws IOException, RunnerException {
        final String[] args = {
                ".*BenchmarkConfig.*",
                "-wi",
                "10",
                "-i",
                "20",
                "-f",
                "1"
        };
        Main.main(args);
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

    /*
    Benchmark                    Mode  Cnt    Score    Error  Units
BenchmarkConfig.testIntern  thrpt   12  386.451 ± 43.269  ops/s
     */
// cnt = 4*3 iteration*fork
}
