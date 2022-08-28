package threadpool.testinspecter;

import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkerThreadFactory implements ThreadFactory {
    private final AtomicInteger counter;
    private String prefix = "";

    public WorkerThreadFactory(String prefix) {
        this.prefix = prefix;
        this.counter = new AtomicInteger();
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, prefix + "-thread-" + counter.incrementAndGet());

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                LoggerFactory.getLogger(t.getName()).error(e.getMessage(), e);
            }
        });

        return thread;
    }
}