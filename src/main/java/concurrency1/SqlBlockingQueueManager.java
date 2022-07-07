package concurrency1;

import java.util.concurrent.Semaphore;

public class SqlBlockingQueueManager {

    private volatile int capacity;

    private Semaphore semaphoreReport;
    private Semaphore semaphorePersist;

    //    private Vector<Integer> vector = new Vector<>();
    StringBuilder stringBuilder = new StringBuilder();
    int batchSize = 0;

    public SqlBlockingQueueManager(int capacity) {
        this.capacity = capacity;

        semaphoreReport = new Semaphore(capacity);
        semaphorePersist = new Semaphore(0);

    }

    public void report(Integer value) throws InterruptedException {
        semaphoreReport.acquire();
//        doReport(value);

        synchronized (this) {
            System.out.println(String.format(">>> %s --- Report %d", Thread.currentThread().getName(), value));
            stringBuilder.append(value + ", ");
            batchSize++;
        }

        semaphorePersist.release();
    }

    public void persist() throws InterruptedException {
        semaphorePersist.acquire(capacity);
//        doPersist();

        String data;
        synchronized (this) {
            data = stringBuilder.toString();
            System.out.println(String.format(">>> %s --- Batch Processing %d - Values = [%s]", Thread.currentThread().getName(), batchSize, data));
            stringBuilder.setLength(0);
            batchSize = 0;
        }


        semaphoreReport.release(capacity);


        doExpensiveOperation(data);
    }

    private synchronized void doReport(Integer value) {
        System.out.println(String.format(">>> %s --- Report %d", Thread.currentThread().getName(), value));
        stringBuilder.append(value + ", ");
        batchSize++;
    }

    private void doPersist() {
        String data;
        synchronized (this) {
            data = stringBuilder.toString();
            System.out.println(String.format(">>> %s --- Batch Processing %d - Values = [%s]", Thread.currentThread().getName(), batchSize, data));
            stringBuilder.setLength(0);
            batchSize = 0;
        }
        doExpensiveOperation(data);
    }

    private void doExpensiveOperation(String data) {

        data = data.trim();
        if (data.endsWith(",")) {
            data = data.substring(0, data.length() - 1);
        }

        try {
            Thread.sleep(1000);

            System.out.println(String.format(">>>>>>>>>>>>>>>>>>>>>>>>  %s ---                     *** [%s] ***", Thread.currentThread().getName(), data));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
