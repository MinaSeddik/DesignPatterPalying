package stream;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;

public class FileStream {


    public static void main(String[] args) throws IOException, URISyntaxException, ExecutionException, InterruptedException {

        String fileName = "big-file.txt";
        URI fileUri = FileStream.class.getClassLoader().getResource(fileName).toURI();
        Objects.requireNonNull(fileUri, "Can't load file " + fileName + " from classpath.");


        Stream<String> stream = Files.lines(Paths.get(fileUri), StandardCharsets.UTF_8)
                .skip(1L).parallel();


        int n = 26;

        // run inside common ForkJoinPool.commonPool()
//        stream.map(s -> Thread.currentThread().getName() + " -> " + s.substring(0, Math.min(s.length(), n)) + (s.length() > n ? "..." : ""))
//                .forEach(System.out::println);

        // Custom thread pool in Java 8 parallel stream
        ForkJoinPool executor = null;
//        ThreadPoolExecutor executor = null;
        try {
            executor = new ForkJoinPool();

            // note: this won't work with streams
            // you should use fork-join pool

//            executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);
            executor.submit(() ->
                    // Parallel task here, for example
                    stream.map(s -> Thread.currentThread().getName() + " -> " + s.substring(0, Math.min(s.length(), n)) + (s.length() > n ? "..." : ""))
                            .forEach(System.out::println)
            ).get();

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            if (executor != null) {
                executor.shutdown();
            }
        }
    }
}
