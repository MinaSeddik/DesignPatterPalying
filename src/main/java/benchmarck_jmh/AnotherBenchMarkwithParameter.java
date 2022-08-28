package benchmarck_jmh;

import org.openjdk.jmh.annotations.*;

import java.nio.ByteBuffer;

@State(Scope.Benchmark)
public class AnotherBenchMarkwithParameter {
    @Param({"128", "1024", "16384"})
    int size;


    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @Measurement(iterations = 3)
    @Fork(value = 1, warmups = 2)
    @Warmup(iterations = 3)
    @BenchmarkMode(Mode.Throughput)
    public ByteBuffer heap() {
        return ByteBuffer.allocate(size);
    }

    @Benchmark
    @Measurement(iterations = 3)
    @Fork(value = 1, warmups = 2)
    @Warmup(iterations = 3)
    @BenchmarkMode(Mode.Throughput)
    public ByteBuffer direct() {
        return ByteBuffer.allocateDirect(size);
    }
    
    
}

// ======================== OUTPUT =================
/*

Benchmark               (size)  Mode  Cnt     Score     Error  Units
AllocateBuffer1.direct     128  avgt    5  1022.137 ± 148.510  ns/op
AllocateBuffer1.heap       128  avgt    5    23.969 ±   0.051  ns/op

AllocateBuffer1.direct    1024  avgt    5  1228.785 ± 127.090  ns/op
AllocateBuffer1.heap      1024  avgt    5   179.350 ±   2.989  ns/op

AllocateBuffer1.direct   16384  avgt    5  3039.485 ± 111.714  ns/op
AllocateBuffer1.heap     16384  avgt    5  2620.722 ±   5.395  ns/op

 */