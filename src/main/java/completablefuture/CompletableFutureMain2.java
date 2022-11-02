package completablefuture;

import com.google.common.collect.Streams;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureMain2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFutureMain2 c = new CompletableFutureMain2();
        CompletableFuture<String> result = c.calculateAsync()
                .thenApply(s -> manipulateString(s))
                .thenApply(s -> manipulateString(s))
                .thenApply(s -> manipulateString(s))
                .thenApply(s -> manipulateString(s))
                .thenApply(s -> manipulateString(s))
                .thenApply(s -> manipulateString(s))
                .thenApply(s -> manipulateString(s))
//                .thenApply(s -> manipulateStringError(s))
                .thenApply(s -> manipulateString(s))
                .thenApply(s -> manipulateString(s))
                .thenApply(s -> manipulateString(s))
                .thenApply(s -> manipulateString(s))
                .thenApply(s -> manipulateString(s))
                .thenApply(s -> manipulateString(s))
                .thenApply(s -> manipulateString(s))
//                .orTimeout(1, TimeUnit.NANOSECONDS)
                .completeOnTimeout("Hi User", 1, TimeUnit.NANOSECONDS)
                .handle((s, th) -> s != null ? "***" + s + "***" : "Hello, Stranger!" + " [ " + th + " ]");

        System.out.println("Default executor: " + result.defaultExecutor());

//        CompletableFuture<Void> x = result.thenAccept(System.out::println)
//                .thenRun(() -> System.out.println(Thread.currentThread().getName() + " Computation finished."));

        System.out.println(Thread.currentThread().getName() + " Result = " + result.get());
        System.out.println("Default executor: " + result.defaultExecutor());

        System.out.println("-------------------------------");

        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));

        System.out.println("completableFuture = " + completableFuture.get());

        System.out.println("-------------------------------");

        CompletableFuture<String> completableFuture2
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);

        System.out.println("completableFuture2 = " + completableFuture2.get());

        System.out.println("-------------------------------");

        CompletableFuture<List<String>> completableFuture3
                = CompletableFuture.supplyAsync(() -> Arrays.asList("Hello ", "Good ", "All "))
                .thenCombine(CompletableFuture.supplyAsync(() -> Arrays.asList("World!", "Morning", "the best")),
                        (l1, l2) -> Streams.zip(l1.stream(), l2.stream(), (s1, s2) -> s1.concat(s2)).collect(Collectors.toList()));

        System.out.println("completableFuture3 = " + completableFuture3.get());


        TimeUnit.HOURS.sleep(5);
    }

    private static String manipulateString(String s) {
        System.out.println(Thread.currentThread().getName() + ", Executing manipulateString ...");

        return "_" + s + "_";
    }

    private static String manipulateStringError(String s) {
        throw new RuntimeException("Some error has taken place!");
    }

    public CompletableFuture<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            System.out.println(Thread.currentThread().getName() + ", Executing completableFuture ...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            completableFuture.complete("Hello");
//            return null;
        });

        return completableFuture;
    }

}
