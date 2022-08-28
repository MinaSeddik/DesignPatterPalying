package threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FixedThreadPool {

    public static void main(final String[] arguments) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);


        //Stats before tasks execution
        System.out.println("Core threads: " + executor.getCorePoolSize());
        System.out.println("Largest executions: " + executor.getLargestPoolSize());
        System.out.println("Maximum allowed threads: " + executor.getMaximumPoolSize());
        System.out.println("Current threads in pool: " + executor.getPoolSize());
        System.out.println("Currently executing threads: " + executor.getActiveCount());
        System.out.println("Total number of threads(ever scheduled): " + executor.getTaskCount());

        executor.submit(new FixedThreadPool.Task());
        executor.submit(new FixedThreadPool.Task());

        //Stats after tasks execution
        System.out.println("Core threads: " + executor.getCorePoolSize());
        System.out.println("Largest executions: " + executor.getLargestPoolSize());
        System.out.println("Maximum allowed threads: " + executor.getMaximumPoolSize());
        System.out.println("Current threads in pool: " + executor.getPoolSize());
        System.out.println("Currently executing threads: " + executor.getActiveCount());
        System.out.println("Total number of threads(ever scheduled): " + executor.getTaskCount());


        try {
            Long duration = (long) (Math.random() * 15);
            System.out.println("Running Task! Thread Name: " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(duration);
            System.out.println("Task Completed! Thread Name: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Stats after tasks execution
        System.out.println("Core threads: " + executor.getCorePoolSize());
        System.out.println("Largest executions: " + executor.getLargestPoolSize());
        System.out.println("Maximum allowed threads: " + executor.getMaximumPoolSize());
        System.out.println("Current threads in pool: " + executor.getPoolSize());
        System.out.println("Currently executing threads: " + executor.getActiveCount());
        System.out.println("Total number of threads(ever scheduled): " + executor.getTaskCount());

        executor.submit(new FixedThreadPool.Task());


        try {
//            Long duration = (long) (Math.random() * 15);
            System.out.println("Running Task! Thread Name: " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(65L);
            System.out.println("Task Completed! Thread Name: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Stats after tasks execution
        System.out.println("Core threads: " + executor.getCorePoolSize());
        System.out.println("Largest executions: " + executor.getLargestPoolSize());
        System.out.println("Maximum allowed threads: " + executor.getMaximumPoolSize());
        System.out.println("Current threads in pool: " + executor.getPoolSize());
        System.out.println("Currently executing threads: " + executor.getActiveCount());
        System.out.println("Total number of threads(ever scheduled): " + executor.getTaskCount());

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
