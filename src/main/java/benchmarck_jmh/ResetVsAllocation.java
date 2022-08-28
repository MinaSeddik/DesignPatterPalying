package benchmarck_jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class ResetVsAllocation {

    private static int w = 128;
    private static int h = 65;

    private static double [][] gMatrix = new double[w][h];

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 3, warmups = 2)
    @Warmup(iterations = 5, timeUnit = TimeUnit.NANOSECONDS)
    @Measurement(iterations = 4, timeUnit = TimeUnit.NANOSECONDS)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void allocation(Blackhole bh) {
        double [][] matrix = new double[w][h];

        bh.consume(matrix);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 3, warmups = 2)
    @Warmup(iterations = 5, timeUnit = TimeUnit.NANOSECONDS)
    @Measurement(iterations = 4, timeUnit = TimeUnit.NANOSECONDS)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void reset(Blackhole bh) {
        for(int i=0;i<w;i++){
            for (int j=0;j<h;j++)
                gMatrix[i][j] = 0d;
        }

        bh.consume(gMatrix);
    }
}
