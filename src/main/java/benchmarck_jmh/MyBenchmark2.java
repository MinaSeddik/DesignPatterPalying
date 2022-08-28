package benchmarck_jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@State(Scope.Benchmark)
public class MyBenchmark2 {

    // note here the integer variable is static
    private static AtomicInteger integer = new AtomicInteger();

    private static volatile int sum = 0;


    @Benchmark
    public void testMethodAtomic(Blackhole bh) {
        int result = integer.incrementAndGet();
        bh.consume(result);
    }

    @Benchmark
    public void testMethodVolatile(Blackhole bh) {
        int result = incrementAndGet(sum);
        bh.consume(result);
    }

    private int incrementAndGet(int sum) {

        synchronized (this){
            ++sum;
        }

        return sum;
    }


}