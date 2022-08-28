package threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CachedThreadPool {


    public static void main(final String[] arguments) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        /*
        The queue size will always be zero because internally a SynchronousQueue instance is used.
        In a SynchronousQueue, pairs of insert and remove operations always occur simultaneously. So, the queue never actually contains anything.
         */

        /*
        The SynchronousQueue only has two supported operations: take() and put(), and both of them are blocking.
        For example, when we want to add an element to the queue, we need to call the put() method. That method will block until some other thread calls the take() method, signaling that it is ready to take an element.
        Although the SynchronousQueue has an interface of a queue, we should think about it as an exchange point for a single element between two threads, in which one thread is handing off an element, and another thread is taking that element.
         */


        //Stats before tasks execution
        System.out.println("Core threads: " + executor.getCorePoolSize());
        System.out.println("Largest executions: " + executor.getLargestPoolSize());
        System.out.println("Maximum allowed threads: " + executor.getMaximumPoolSize());
        System.out.println("Current threads in pool: " + executor.getPoolSize());
        System.out.println("Currently executing threads: " + executor.getActiveCount());
        System.out.println("Total number of threads(ever scheduled): " + executor.getTaskCount());

        executor.submit(new Task());
        executor.submit(new Task());

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

        executor.submit(new Task());


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
