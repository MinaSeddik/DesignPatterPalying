package rxjava;

import rx.Observable;
import rx.subscriptions.Subscriptions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class BeginnerMain4 {

    public static void main(String[] args) {

        UUID id = UUID.randomUUID();

        store(id);
        System.out.println("----------------------------");


        store2(id);
    }

    public static void store(UUID id) {
        upload(id).subscribe(
                bytes -> {
                    System.out.println(Thread.currentThread().getName() + " -> " + bytes + " uploaded ...");
                },
                e -> System.out.println("Error" + e),
                () -> rate(id)
        );

    }

    public static void store2(UUID id) {

        upload(id).flatMap(
                bytes -> Observable.just(bytes),
                e -> Observable.error(e),
                () -> rate(id)
        ).subscribe(
                rating -> {
                    System.out.println(Thread.currentThread().getName() + " -> Rating = " + rating);
                },
                e -> System.out.println("Error" + e)
        );
    }


    public static Observable<Integer> upload(UUID id) {
        Observable<Integer> observable = Observable.<Integer>create(
                subscriber -> {

//                    InputStream in = null;
//                    OutputStream out = null; // the place you want to ultimately store the file

//                    byte[] buffer = new byte[8192];
                    int bytesRead = 0, uploadedBytes = 0;
                    int totalSize = 100;
                    try {
//                        while ((bytesRead = in.read(buffer)) > 0) {
//                            out.write(buffer, 0, bytesRead);
//                            subscriber.onNext(bytesRead);
//                        }

                        // to simulate file uploading
                        while (uploadedBytes < totalSize) {
                            sleep(150, TimeUnit.MILLISECONDS);
                            uploadedBytes += 10;
                            subscriber.onNext(uploadedBytes);
                        }

//                        in.close();
//                        out.close();


                    } catch (Exception ex) {
                        subscriber.onError(ex);
                    } finally {
                        subscriber.onCompleted();
                    }

                    subscriber.add(Subscriptions.create(() -> System.out.println("Clear resources ...")));

                });

        return observable;
    }


    public static void upload() throws IOException {

        InputStream in = null;
        OutputStream out = null; // the place you want to ultimately store the file

        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) > 0) {
            out.write(buffer, 0, bytesRead);
        }

    }

    public static Observable<String> rate(UUID id) {

        Observable<String> observable = Observable.create(
                subscriber -> {
                    subscriber.onNext("Good");
                    sleep(500, TimeUnit.MILLISECONDS);
                    subscriber.onCompleted();
                }
        );

        return observable;
    }

    private static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (Exception e) {
        }
    }


}
