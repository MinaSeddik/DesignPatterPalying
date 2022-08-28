package concurrency1;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ConMain {


    public static void main(String[] args) {

        SqlBlockingQueueManager sqlBlockingQueueManager = new SqlBlockingQueueManager(10);

        int nCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of Processors: " + nCores);
        int numOfThreads = nCores*2;
        Executor executor = Executors.newFixedThreadPool(2);


        Producer producer = new Producer(sqlBlockingQueueManager);
        Consumer consumer = new Consumer(sqlBlockingQueueManager);

        for(int i=0;i<1;i++){
            executor.execute(() -> saveTransactions(consumer));
        }

        for(int i=0;i<1;i++){
            executor.execute(() -> reportTransactions(producer));
        }

    }

    private static void saveTransactions(Consumer consumer) {
        while (true) {
            consumer.saveTransaction();
        }
    }

    private static void reportTransactions(Producer producer) {
        Random random = new Random();

        while (true) {
            producer.reportTransaction(random.nextInt(1000));
        }
    }
}
