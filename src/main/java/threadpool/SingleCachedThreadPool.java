package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleCachedThreadPool {

    public static void main(final String[] arguments) throws InterruptedException {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        /*
        this ThreadPoolExecutor is decorated with an immutable wrapper, so it can't be reconfigured after creation. Note that this is also the reason we can't cast it to a ThreadPoolExecutor.
         */

        executor.submit(new SingleCachedThreadPool.Task());
        executor.submit(new SingleCachedThreadPool.Task());


        try {
            Long duration = (long) (Math.random() * 15);
            System.out.println("Running Task! Thread Name: " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(duration);
            System.out.println("Task Completed! Thread Name: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.submit(new SingleCachedThreadPool.Task());


        try {
//            Long duration = (long) (Math.random() * 15);
            System.out.println("Running Task! Thread Name: " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(65L);
            System.out.println("Task Completed! Thread Name: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }

    static class Task implements Runnable {

        public void run() {

            try {
                Long duration = (long) (Math.random() * 5);
                System.out.println("Running Task! Thread Name: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(duration);
                System.out.println("Task Completed! Thread Name: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


}
