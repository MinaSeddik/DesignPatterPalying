package threadpool;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FibonacciRecursiveTask extends RecursiveTask<Long> {

    private final static long THRESHOLD = 10;
    private volatile long number;

    public FibonacciRecursiveTask(long number) {
        this.number = number;
    }

    /* optional */
    public long getNumber() {
        return number;
    }

    @Override
    protected Long compute() {
        long n = number;
        if (n <= THRESHOLD) {
            number = fibo(n);
        } else {
            FibonacciRecursiveTask f1 = new FibonacciRecursiveTask(n - 1);
            FibonacciRecursiveTask f2 = new FibonacciRecursiveTask(n - 2);

            // (1) way-1
            ForkJoinTask.invokeAll(f1, f2);
            number = f1.number + f2.number;

            // (2) way-2
//            f1.fork();
//            f2.fork();
//            number = f1.join() + f2.join();

        }

        return number;
    }

    private long fibo(long n) {
//        System.out.println(Thread.currentThread().getName());
        if (n <= 1) return n;
        else return fibo(n - 1) + fibo(n - 2);
    }

}
