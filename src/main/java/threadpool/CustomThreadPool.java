package threadpool;

import threadpool.testinspecter.WorkerThreadFactory;

import java.util.concurrent.*;

public class CustomThreadPool {

    public static void main(final String[] arguments) throws InterruptedException {

        int corePoolSize = 5;
        int maximumPoolSize = 15;
        long keepAliveTime = 30L;
        // The optional capacity bound constructor argument serves as a way to prevent excessive queue expansion. The capacity, if unspecified, is equal to Integer.MAX_VALUE. Linked nodes are dynamically created upon each insertion unless this would bring the queue above capacity.
        BlockingQueue boundedQueue = new LinkedBlockingQueue(30);


        ThreadFactory threadFactory = new WorkerThreadFactory("test");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, boundedQueue, threadFactory);

        //Stats before tasks execution
        System.out.println("Core threads: " + executor.getCorePoolSize());
        System.out.println("Largest executions: " + executor.getLargestPoolSize());
        System.out.println("Maximum allowed threads: " + executor.getMaximumPoolSize());
        System.out.println("Current threads in pool: " + executor.getPoolSize());
        System.out.println("Currently executing threads: " + executor.getActiveCount());
        System.out.println("Total number of threads(ever scheduled): " + executor.getTaskCount());

        executor.submit(new CustomThreadPool.Task());
        executor.submit(new CustomThreadPool.Task());
        executor.submit(new CustomThreadPool.Task());
        executor.submit(new CustomThreadPool.Task());
        executor.submit(new CustomThreadPool.Task());
        executor.submit(new CustomThreadPool.Task());
        executor.submit(new CustomThreadPool.Task());

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

        executor.submit(new CustomThreadPool.Task());


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
