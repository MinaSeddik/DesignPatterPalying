package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CompletableFutureMain {



    public static void main(String[] args) throws ExecutionException, InterruptedException {


        System.out.println("Run FutureTask ...");
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> doLongTask());
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> doLongTask());


        System.out.println("Waiting for the task to be completed ...");
        CompletableFuture<String> completableFuture = completableFuture1.thenCombine(completableFuture2, (x, y) -> x+y);
        String val = completableFuture.get();

        System.out.println("Result :" + val);

    }

    private static String doLongTask() {

        System.out.println(Thread.currentThread().getName() + "::doLongTask ");

        return "Hi ";
    }


}
