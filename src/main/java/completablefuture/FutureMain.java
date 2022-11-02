package completablefuture;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        System.out.println("Run FutureTask ...");
        FutureTask<Integer> futureTask = new FutureTask<>(() -> doLongTask());

        futureTask.run();
        System.out.println("Waiting for the task to be completed ...");
        int val = futureTask.get();

        System.out.println("Result :" + val);
    }

    private static int doLongTask() {

        System.out.println(Thread.currentThread().getName() + "::doLongTask ");

        return 32;
    }


}
