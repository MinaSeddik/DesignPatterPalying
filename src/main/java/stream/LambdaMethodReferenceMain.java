package stream;

import java.io.File;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaMethodReferenceMain {


    public static void main(String[] args) {

        // this is lambda method reference
        Function<String, Integer> function1 = String::length;

        // this is lambda expression
        Function<String, Integer> function2 = s -> s.length();

        Function<Double, Double> function3 = Math::floor;
        Function<String, String> function4 = String::new;

        File file = new File("dir", "filename");
        BiFunction<String, String, File> biFunction1 = File::new;

        // usage
        int out1 = function1.apply("test");
        int out2 = function2.apply("test");
        double out3 = function3.apply(2.0369d);
        String out4 = function4.apply("test");


        Supplier<String> supplier = String::new;

        String out5 = supplier.get();


        System.out.println("R4: "+ out4);
        System.out.println("R5: "+ out5);


    }
}
