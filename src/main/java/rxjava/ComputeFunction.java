package rxjava;

import java.util.List;

public class ComputeFunction {
    public static void compute(Integer v) {
        try {
            System.out.println("compute integer v: " + v);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void compute2(List<Integer> list) {
        try {
            System.out.println("compute integer list: " + list);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}