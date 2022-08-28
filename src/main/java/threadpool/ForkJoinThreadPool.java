package threadpool;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ForkJoinThreadPool {


    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        ForkJoinPool forkJoinPool2 = new ForkJoinPool(4);

        System.out.println(commonPool);
        System.out.println(forkJoinPool2);

        System.out.println("getPoolSize: " + commonPool.getPoolSize());
        System.out.println("getActiveThreadCount: " + commonPool.getActiveThreadCount());
        System.out.println("getParallelism: " + commonPool.getParallelism());

        int N = 3;
        for (int i = 0; i < N; i++) {
            final int batch = i;
            commonPool.execute(() -> printMsg(batch));
//            forkJoinPool2.execute(() -> printMsg(batch));
        }
/*
commonPool.execute(() -> printMsg());
        commonPool.execute(() -> printMsg());
        */


        commonPool.awaitTermination(1, TimeUnit.HOURS);
        forkJoinPool2.awaitTermination(1, TimeUnit.HOURS);

    }


    public static void printMsg(int batchID) {

        List<Integer> list = IntStream.range(0, 10).boxed().collect(Collectors.<Integer>toUnmodifiableList());

        try {
            int i = 0;
            while (i < 10000000) {
                Long duration = (long) (Math.random() * 15);
                System.out.println("Running " + Thread.currentThread().getName() + " ,batchID: " + batchID + " ,Value: " + i++);
                TimeUnit.SECONDS.sleep(duration);


                list.stream().forEach(n -> {
                    try {
                        Thread.sleep(5);
                        System.out.println("Loop 1 : " + Thread.currentThread().getName() + "   " + n);
                    } catch (InterruptedException e) {

                    }
                });

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

