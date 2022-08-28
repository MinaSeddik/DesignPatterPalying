package threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class WorkStealingPool {

    public static void main(final String[] arguments) throws InterruptedException {
        ForkJoinPool executor = (ForkJoinPool) Executors.newWorkStealingPool();


        executor.submit(new WorkStealingPool.Task());
        executor.submit(new WorkStealingPool.Task());


        try {
            Long duration = (long) (Math.random() * 15);
            System.out.println("Running Task! Thread Name: " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(duration);
            System.out.println("Task Completed! Thread Name: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        executor.submit(new WorkStealingPool.Task());


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
