package generics;

public class GenMain {


    public static void main(String[] args) {

        Number n = maximum(1,3,23.56);
        System.out.println(n);


        Box<Integer, String> box = new Box<Integer, String>();
    }



    public static <U, T extends Number> int maximum(U x, U y, T z){

        return 5;
    }


    public static <T extends Number> T test1(T z){

        return z;
    }

    public static int test2(int z){

        return z;
    }
}
