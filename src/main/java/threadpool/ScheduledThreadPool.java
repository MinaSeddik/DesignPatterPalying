package threadpool;

import java.util.concurrent.*;

public class ScheduledThreadPool {

    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        executor.schedule(() -> {
            System.out.println("Hello World!");
        }, 500, TimeUnit.MILLISECONDS);


        CountDownLatch lock = new CountDownLatch(3);

        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
            System.out.println("Hello World ...");
            lock.countDown();
        }, 500, 100, TimeUnit.MILLISECONDS);

        lock.await(1000, TimeUnit.MILLISECONDS);
        future.cancel(true);



        executor.awaitTermination(1, TimeUnit.HOURS);
    }

}
