package threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadManager {

    private static long number1 = 0;
    private static long number2 = 0;
    private static long number3 = 0;

    public static Logger logger = LoggerFactory.getLogger(ThreadManager.class);

    public static void main(String[] args) throws InterruptedException {


        ExecutorService executor = Executors.newFixedThreadPool(300);

        int i = 10000000;
        while (i > 0 ){
//            executor.execute(() -> System.out.println(Thread.currentThread().getName() + ": " + count()));
            executor.execute(() -> logger.info(Thread.currentThread().getName() + ": " + count()));
            i--;
        }

        System.out.println(executor.isTerminated());

        executor.shutdown();
        while (!executor.awaitTermination(1000, TimeUnit.SECONDS)){
//            System.err.println("Threads didn't finish in 60000 seconds!");
            System.out.println("Threads didn't finish in 1000 seconds!");
        }

//        executor.awaitTermination(80, TimeUnit.MICROSECONDS);

//        System.out.println(executor.isTerminated());

//
        System.out.println("Final Number1 : " + number1);
        System.out.println("Final Number2 : " + number2);
        System.out.println("Final Number3 : " + number3);

//        logger.info("Final Number1 : {}", number1);
//        logger.info("Final Number2 : " + number2);
//        logger.info("Final Number3 : " + number3);
//        executor.shutdown();


    }


    public static synchronized long count() {

        long x = number1++;
        Thread.yield();
        long y = number3++;
        Thread.yield();
        long z = number2++;

//        System.out.println(Thread.currentThread().getName() + " yielding ...");
//        Thread.yield();
//        System.out.println(Thread.currentThread().getName() + " back ...");


        return x;
    }


}
