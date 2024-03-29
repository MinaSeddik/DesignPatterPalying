#!/bin/bash


for i in 1 2 4 8 16
do
    java -jar ../target/benchmarks.jar benchmarck_jmh.BenchmarkMain.testIntern$ --jvmArgs -Djava.util.concurrent.ForkJoinPool.common.parallelism=${i} -p corePoolSize=${i} |& tee out.${i}
done

