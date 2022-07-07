package threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager2 {

    public static Logger logger = LoggerFactory.getLogger(ThreadManager2.class);

    public static void main(String[] args) throws InterruptedException {


        ThreadManager2 sharedObject = new ThreadManager2();

        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.execute(() -> sharedObject.firstMethod());
        executor.execute(() -> sharedObject.firstMethod());
        executor.execute(() -> sharedObject.firstMethod());
        executor.execute(() -> sharedObject.firstMethod());
        executor.execute(() -> sharedObject.firstMethod());
        executor.execute(() -> sharedObject.firstMethod());
        executor.execute(() -> sharedObject.firstMethod());
        executor.execute(() -> sharedObject.firstMethod());
        executor.execute(() -> sharedObject.firstMethod());
        executor.execute(() -> sharedObject.firstMethod());
        executor.execute(() -> sharedObject.firstMethod());

        executor.shutdown();

    }


    public synchronized void firstMethod() {

        logger.info(Thread.currentThread().getName() + " inside firstMethod() before");

        secondMethod();

        logger.info(Thread.currentThread().getName() + " inside firstMethod() after");

    }


    private   void secondMethod() {

        logger.info(Thread.currentThread().getName() + " inside secondMethod()");


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thirdMethod();

    }

    private   void thirdMethod() {

        logger.info(Thread.currentThread().getName() + " inside thirdMethod()");


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
