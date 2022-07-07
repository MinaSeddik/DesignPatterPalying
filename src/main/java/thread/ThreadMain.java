package thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadMain {


    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> doSomthing();
//        Callable<Integer> callable = () -> doSomthing2();

        Thread thread = new Thread(runnable);
        thread.setName("Runnable");



        try {
            thread.start();
            Thread.sleep(3000);
            System.out.println("Thread Interrupted: " + thread.isInterrupted());

            thread.interrupt();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

//        FutureTask<Integer> futureTask = new FutureTask<>(callable);
//        Thread thread2 = new Thread(futureTask);
//        thread2.setName("Callable");
//
//        try {
//            thread2.start();
//            futureTask.get();
////            Thread.sleep(100);
////            System.out.println("Thread Interrupted: " + thread2.isInterrupted());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        while (true) ;
//        thread.interrupt();
    }

    private static void doSomthing() {

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(1);



        int x = 0;
        try {
//            System.out.println("Inside here!");
//            if (x == 0) {
//                processtask();
//            }

//            System.out.println("put 2");
//            queue.put(2);
//
//            System.out.println("put 3");
//            queue.put(3);
//
//            System.out.println("put 4");
//            queue.put(4);


            Thread.sleep(555555);

        } catch (InterruptedException ex) {
            // restore interrupted status
            System.out.println("*************** Ex: " + ex.getMessage());
//            Thread.currentThread().interrupt();
        }

    }

    private static int processtask() throws InterruptedException {
        int i = 0;
//        while (i++ < 100000) {
//            System.out.println("+++");
//            throw new InterruptedException("ff");
//        }

        return 5/i;
    }

    private static int doSomthing2() throws Exception{

        int x = 0;

        if (x ==0 )
        throw new Exception("jsadghfgdsfsdffgasdfasdgfjk");
//        try {
//            System.out.println("Inside here!");
//            if (x == 0) {
//                processtask();
//            }
//        } catch (InterruptedException ex) {
//            System.out.println("*************** Ex: " + ex.getMessage());
//        }

        return 5/x;
    }



}
