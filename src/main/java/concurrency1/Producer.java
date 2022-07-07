package concurrency1;

public class Producer {

    private SqlBlockingQueueManager sqlBlockingQueueManager;

    public Producer(SqlBlockingQueueManager sqlBlockingQueueManager) {
        this.sqlBlockingQueueManager = sqlBlockingQueueManager;
    }

    public void reportTransaction(Integer item){
        try {
            sqlBlockingQueueManager.report(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
