package assertion;

public class Main {

    public static void main(String[] args) {

        testMe(100);
        testMe(3);
        testMe(0);
        testMe(100100);
    }


    private static int testMe(int x){

        assert x != 0;

        x = x*9;

        return ++x;
    }

}
