package threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinThreadPool_RecurssiveTask {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        ForkJoinPool forkJoinPool2 = new ForkJoinPool(4);

        int num = 50;
        FibonacciRecursiveTask task = new FibonacciRecursiveTask(num);
        ForkJoinTask<Long> fiboTask = forkJoinPool2.submit(task);
        long value = fiboTask.get();
        System.out.println(String.format("Fibo(%d) = %d", num, value));

        commonPool.awaitTermination(1, TimeUnit.HOURS);
        forkJoinPool2.awaitTermination(1, TimeUnit.HOURS);

    }

}
