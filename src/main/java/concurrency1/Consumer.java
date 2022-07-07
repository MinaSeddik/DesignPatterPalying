package concurrency1;

public class Consumer {

    private SqlBlockingQueueManager sqlBlockingQueueManager;

    public Consumer(SqlBlockingQueueManager sqlBlockingQueueManager) {
        this.sqlBlockingQueueManager = sqlBlockingQueueManager;
    }

    public void saveTransaction(){
        try {
            sqlBlockingQueueManager.persist();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
