package threadpool.testinspecter;

import java.util.concurrent.*;
import java.util.function.Consumer;

public class CompletableFutureMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> printMsg("x"), executor);
//        CompletableFuture<String> completableFuture2 = CompletableFuture
//                .supplyAsync(() -> printMsg("y"), executor)
//                .thenApply(s1 -> printMsg(s1))
//                .thenApply(s1 -> printMsg(s1))
//                .thenApply(s1 -> printMsg(s1))
//                .thenApply(s1 -> printMsg(s1))
//                .thenApplyAsync(s1 -> printMsg(s1+"****"))
//                .thenApply(s1 -> printMsg(s1))
//                .thenApply(s1 -> printMsg(s1))
//                .thenApplyAsync(s1 -> printMsg(s1+"****"), executor)
//                .thenApply(s1 -> printMsg(s1))
//                .thenApply(s1 -> printMsg(s1))
//                .thenApply(s1 -> printMsg(s1));
////                .thenAccept( acceptMsg("----------------------"));

//        System.out.println("Running here " + Thread.currentThread().getName());
        CompletableFuture<String> completableFuture3 = CompletableFuture
                .supplyAsync(() -> printMsg("z"), executor)
                .thenApply(s1 -> printMsg(s1))
                .thenApplyAsync(s1 -> printMsg(s1))
                .thenApplyAsync(s1 -> printMsg(s1))
                .thenApplyAsync(s1 -> printMsg(s1))
                .thenApplyAsync(s1 -> printMsg(s1))
                .thenApplyAsync(s1 -> printMsg(s1));
//                .completeAsync(() -> acceptMsg());
//        completableFuture3.complete("asfdafsdafsdafasdfsdaf");
        completableFuture3.completeAsync(() -> acceptMsg());
        System.out.println("XXXXXXXXXXXXXXXXXXXXX");
        System.out.println("--------->>" + completableFuture3.get());

//        completableFuture3.complete("zzzzzzzzz");




//        CompletableFuture<String> future = completableFuture.thenApply(s -> printMsg(s + " World"));
//
//        CompletableFuture<String> completableFutureA = CompletableFuture
//                .supplyAsync(() -> printMsg("A"))
//                .thenApply(s1 -> printMsg(s1))
//                .thenApplyAsync(s1 -> printMsg(s1))
//                .thenApply(s1 -> printMsg(s1))
//                .thenApplyAsync(s1 -> printMsg(s1))
//                .thenApplyAsync(s1 -> printMsg(s1))
//                .thenApplyAsync(s1 -> printMsg(s1));
//
//        CompletableFuture<String> completableFutureB = CompletableFuture
//                .supplyAsync(() -> printMsg("B"))
//                .thenApply(s1 -> printMsg(s1))
//                .thenApplyAsync(s1 -> printMsg(s1))
//                .thenApply(s1 -> printMsg(s1))
//                .thenApplyAsync(s1 -> printMsg(s1))
//                .thenApplyAsync(s1 -> printMsg(s1))
//                .thenApplyAsync(s1 -> printMsg(s1));
//
//        CompletableFuture<String> completableFutureC = CompletableFuture
//                .supplyAsync(() -> printMsg("C"))
//                .thenApply(s1 -> printMsg(s1))
//                .thenApplyAsync(s1 -> printMsg(s1))
//                .thenApply(s1 -> printMsg(s1))
//                .thenApplyAsync(s1 -> printMsg(s1))
//                .thenApplyAsync(s1 -> printMsg(s1))
//                .thenApplyAsync(s1 -> printMsg(s1));
        while (true) ;

    }

    private static String acceptMsg() {

        System.out.println("Running acceptMsg/Complete " + Thread.currentThread().getName());
        return "asfsadf";
    }

    public static String printMsg(String str) {


        try {
            Long duration = 1L;
            System.out.println("Running " + str + " - " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(duration);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return str + " out";
    }
}
